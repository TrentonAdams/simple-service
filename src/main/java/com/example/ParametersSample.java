package com.example;

import com.example.mvc.PageModel;
import com.example.parameters.ParameterHandler;
import com.example.parameters.SampleParameters;
import org.glassfish.jersey.server.mvc.Template;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by IntelliJ IDEA.
 * <p/>
 * Created :  15/03/16 7:20 PM MST
 * <p/>
 * Modified : $Date$ UTC
 * <p/>
 * Revision : $Revision$
 *
 * @author Trenton D. Adams
 */
@Path("parameters")
public class ParametersSample extends PageModel
{
    private static final String PARAMETERS_JSP = "/WEB-INF/jsp/parameters.jsp";

    @Inject
    protected ParameterHandler<SampleParameters> pageParameters;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/")
    @Template(name = "/WEB-INF/jsp/index.jsp")
    public PageModel getParameters()
    {
        page = PARAMETERS_JSP;
        return this;
    }
}
