package com.example.parameters;

/**
 * Generic class for enums that are to be used as web parameters.  Currently
 * the only added functionality is the max length.  Technically the name()
 * method already exists in all enums, but we wanted it exposed as part
 * of an interface.
 * <p/>
 * Created :  2013-12-12T11:25 MST
 *
 * @author trenta
 */
public interface ParameterEnum
{ // BEGIN ParameterEnum interface

    /**
     * Retrieves the maximum length for the value of the parameter coming in
     * from the web.
     *
     * @return the max length.
     */
    int getMaxLength();

    /**
     * The enum value's name.
     *
     * @return the name of the enum.
     */
    String name();

    /**
     * The display name for things like errors.
     *
     * @return the name to display for errors.
     */
    String getDisplayName();
} // END ParameterEnum interface
