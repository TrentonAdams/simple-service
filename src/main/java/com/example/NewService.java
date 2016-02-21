package com.example;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.server.mvc.Template;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * <p/>
 * Created :  19/02/16 6:23 PM MST
 * <p/>
 * Modified : $Date$ UTC
 * <p/>
 * Revision : $Revision$
 *
 * @author Trenton D. Adams
 */
@Path("/newservice")
public class NewService
{
    protected String name;

    public NewService()
    {
        name = "my service " + System.currentTimeMillis();
    }

    /**
     * Example of a custom injection using {@link HttpSessionFactory}
     */
    @Inject
    private HttpSession session;

    /**
     * Provides detailed uri request information
     */
    @Context
    private UriInfo uriInfo;

    /**
     * Injects links for all services.
     */
    @InjectLinks(value = {
        @InjectLink(resource = MyResource.class),
        @InjectLink(resource = NewService.class)})
    private
    List<Link> serviceLinks;

    /**
     * A link to this service, which can be used to construct sub-URIs.
     */
    @InjectLink(resource = NewService.class)
    private URI serviceUri;

    /**
     * The current jsp page to rendered within the index.jsp
     */
    private String page;

    /*
     * Data methods.
     */
    public String getName()
    {
        return name;
    }

    public HttpSession getSession()
    {
        return session;
    }

    public List<Link> getServiceLinks()
    {
        return serviceLinks;
    }

    public UriInfo getUriInfo()
    {
        return uriInfo;
    }

    public URI getServiceUri()
    {
        return serviceUri;
    }

    public String getPage()
    {
        return page;
    }

    /*
     * JAX-RS service methods .
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/")
    @Template(name = "index.jsp")
    public NewService getService()
    {
        return this;
    }

    @GET
    @Path("/test")
    @Template(name = "index.jsp")
    @Produces(MediaType.TEXT_HTML)
    public NewService getTest()
    {
        page = "/test.jsp";
        return this;
    }
    @GET
    @Path("/test/{pathParam}")
    @Template(name = "index.jsp")
    @Produces(MediaType.TEXT_HTML)
    public NewService getPathParam()
    {
        page = "/testpath.jsp";
        return this;
    }

}
