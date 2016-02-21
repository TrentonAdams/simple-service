package com.example;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;
import org.glassfish.jersey.server.mvc.Template;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
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

    @Inject
    private HttpSession session;

    @InjectLinks(value = {
        @InjectLink(resource = MyResource.class),
        @InjectLink(resource = NewService.class)})
    private
    List<Link> serviceLinks;

    @InjectLink(resource = NewService.class)
    private URI link;


    public NewService()
    {
        name = "my service " + System.currentTimeMillis();
    }

    public String getName()
    {
        return name;
    }

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
    @Template(name = "test.jsp")
    @Produces(MediaType.TEXT_HTML)
    public TestClass getTest()
    {
        return new TestClass();
    }

    public URI getLink()
    {
        return link;
    }

    public HttpSession getSession()
    {
        return session;
    }

    public List<Link> getServiceLinks()
    {
        return serviceLinks;
    }

    public static class TestClass
    {
        @InjectLink(resource = NewService.class)
        private URI newServiceUri;

        public URI getNewServiceUri()
        {
            return newServiceUri;
        }

        public void setNewServiceUri(URI newServiceUri)
        {
            this.newServiceUri = newServiceUri;
        }
    }
}
