package com.example;


import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.server.mvc.Template;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")

public class MyResource
{

/*    @Inject
    public HttpSession session;*/

    @Context
    private UriInfo uriInfo;

    /**
     * Method handling HTTP GET requests. The returned object will be sent to
     * the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/string")
    public String getIt()
    {
//        System.out.println(request.getSession().getId());
        return "Got it!";
    }

    @GET
    @Template(name = "index2.jsp")
    public String getTemplate()
    {
        return "mymodel";
    }

}
