package com.example.parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.RemoteException;
import java.util.Enumeration;

/**
 * Handles parameters from the request object, including retrieval from the
 * request or session, retrieval of parameter names, setting as request or
 * session attributes.
 * <p/>
 * Created : 2014-01-08T12:36 MST
 *
 * @author trenta
 */
@SuppressWarnings(
    {"PublicMethodNotExposedInInterface", "ClassWithoutNoArgConstructor"})
public class ParameterHandler<TEnum extends ParameterEnum>
{ // BEGIN ParameterHandler class
    private final String[] names;
    private final HttpServletRequest request;

    /**
     * Automatically sets up the error list, and prepares the parameter handler
     * to be used.
     *
     * @param request   the servlet request object
     * @param enumClass the {@link ParameterEnum} implementation
     */
    public ParameterHandler(final HttpServletRequest request,
        final Class enumClass)
    {
        this.request = request;
        if (!ParameterEnum.class.isAssignableFrom(enumClass))
        {
            throw new IllegalArgumentException(
                "enumClass must be derived from " +
                    ParameterEnum.class.getCanonicalName());
        }
        final Enum[] enums = (Enum[]) enumClass.getEnumConstants();
        names = new String[enums.length];
        for (int i = 0; i < enums.length; i++)
        {
            names[i] = enums[i].name();
        }
    }

    /**
     * Retrieves the first value of the given parameter from the request. if it
     * does not exist it attempts to retrieve it from the session.  The
     * implementation of retrieving the parameter is actually implemented in
     * {@link #getParameter(String)}
     *
     * @param enumValue the enum instance
     *
     * @return the parameter value, or null if it does not exist
     */
    public String getParameter(final TEnum enumValue)
    {
        String parameter = getParameter(enumValue.name());
        if (parameter != null)
        {
            if (enumValue.getMaxLength() > 0)
            {
                parameter = strip(parameter, enumValue.getMaxLength());
            }
        }
        return parameter;
    }

    /**
     * Strips the string of leading/trailing spaces and truncates to specified
     * length.
     *
     * @param parameter the parameter value
     * @param maxLength the max length.
     *
     * @return the modified string.
     */
    private static String strip(final String parameter, final int maxLength)
    {
        if (parameter == null)
        {   // null, do nothing
            return null;
        }
        else
        {   // trim string and chop off length.
            final String s = ((String) parameter).trim();
            return s.length() <= maxLength ? s : s.substring(0, maxLength);
        }
    }

    /**
     * Retrieves the first value of the given parameter from the request. if it
     * does not exist it attempts to retrieve it from the session.
     *
     * @param name name of the parameter
     *
     * @return the parameter value, or null if it does not exist
     */
    public String getParameter(final String name)
    {
        final HttpSession session = request.getSession();
        String parameter = request.getParameter(name);
        if (parameter == null)
        {
            parameter = (String) session.getAttribute(name);
        }
        return parameter;
    }

    public boolean getBoolean(final TEnum enumValue)
    {
        return Boolean.valueOf(getParameter(enumValue));
    }

    /**
     * Retrieves all the names of the enum class given in the constructor, in an
     * array of Strings. It is assumed that the class is in fact an enum, and it
     * is up to the developer to ensure this.
     *
     * @return the array of enum names.
     */
    public String[] getNames()
    {
        return names;
    }

    /**
     * A useful helper method which will take all paramaters from the
     * HTTPSevletRequest object and set them as attributes.
     */
    public void setAllParametersAsAttributes()
    {
        final Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements())
        {
            final String currentParameter = (String) enumeration.nextElement();
            // logger.info(currentParameter);
            request.setAttribute(currentParameter, request.getParameter(
                currentParameter));
        }
    } //end setParametersAsAttributes


    /**
     * Set the specified parameters as request attributes.
     * <p/>
     * This will not work for multi-valued parameters.
     */
    public void setParametersAsAttributes()
    {
        for (final String name : names)
        {
            request.setAttribute(name, request.getParameter(name));
        }
    }

    /**
     * Set the specified parameters as session attributes.
     * <p/>
     * This will not work for multi-valued parameters.
     *
     * @param storeNull store the attributes even if the parameters are null?
     *                  true is the previous default prior to the existence of
     *                  this parameter.
     */
    public void setParametersAsSessionAttributes(final boolean storeNull)
    {
        final HttpSession session = request.getSession();
        for (final String name : names)
        {
            if (request.getParameter(name) != null || storeNull)
            {
                session.setAttribute(name, request.getParameter(name));
            }
        }
    }

    /**
     * A useful helper method which will take all attributes in the
     * HTTPServletRequest ojbect and remove them.
     */
    public void removeAllAttributes()
    {

        final Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements())
        {
            final String currentParameter = (String) enumeration.nextElement();
            request.removeAttribute(currentParameter);
        }
    }//end removeAllAttributes

    /**
     * Checks for the existence of a session attribute, and if it does not
     * exist, calls the object factory to create it, then stores it in the
     * session using the given name.
     *
     * @param name          the attribute name to store the new instance as
     * @param objectFactory the object factory to create the new instance
     *
     * @return the new instance
     *
     * @throws RemoteException if an RMI communication error occurs.
     */
    protected Object getSessionAttribute(final String name,
        final ObjectFactory objectFactory) throws RemoteException
    {
        final HttpSession session = request.getSession();

        Object variable = session.getAttribute(name);
        if (variable == null)
        {
            variable = objectFactory.createInstance();
            session.setAttribute(name, variable);
        }

        return variable;
    }

    public void setParameterAsReqAttribute(
        final ParameterEnum parameterEnum, final Object value)
    {
        request.setAttribute(parameterEnum.name(), value);
    }

    /**
     * Creates loggable version of the parameters and their values.  Currently
     * only supports one value per parameter.
     *
     * Override this if you'd like to to prefix it with something such as a
     * user id.
     *
     * @return the string to log.
     *
     */
    public String logParameters()
    {
        final StringBuilder buffer = new StringBuilder();
        for (final String name : names)
        {
            buffer.append(name);
            buffer.append(" = \"".intern());
            buffer.append(getParameter(name));
            buffer.append("\", ".intern());
        }
        return buffer.toString();
    }

    /**
     * An interface for implementing a simple object factory.  This object
     * factory should return a new instance of the desired class.  This is
     * mainly for the purpose of simplifying creation and storage of the
     * instance in a session.  This is currently to be used in conjunction with
     * {@link #getSessionAttribute(String, ParameterHandler.ObjectFactory)}
     */
    public static interface ObjectFactory
    {
        Object createInstance() throws RemoteException;
    }
} // END ParameterHandler class
