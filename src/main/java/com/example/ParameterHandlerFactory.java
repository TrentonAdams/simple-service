package com.example;

import com.example.parameters.ParameterEnum;
import com.example.parameters.ParameterHandler;
import com.example.parameters.SampleParameters;
import org.glassfish.hk2.api.Factory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA.
 * <p/>
 * Created :  18/02/16 3:23 PM MST
 * <p/>
 * Modified : $Date$ UTC
 * <p/>
 * Revision : $Revision$
 *
 * @author Trenton D. Adams
 */
public class ParameterHandlerFactory
    implements Factory<ParameterHandler<SampleParameters>>
{

    private final HttpServletRequest request;

    @Inject
    public ParameterHandlerFactory(final HttpServletRequest request)
    {
        this.request = request;
    }

    @Override
    public ParameterHandler<SampleParameters> provide()
    {
        return new ParameterHandler<SampleParameters>(request,
            SampleParameters.class);
    }

    @Override
    public void dispose(
        final ParameterHandler<SampleParameters> parameterEnumParameterHandler)
    {

    }

}
