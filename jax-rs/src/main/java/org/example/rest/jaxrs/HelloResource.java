package org.example.rest.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class HelloResource {
    public HelloResource() {
        System.out.println("HelloResource()");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String helloAsync() {
        return "Hello world";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}/loginType/password")
    public Account setPasswordLoginType(@PathParam("userId") Integer userId, @QueryParam("username") String username, @QueryParam("password") String password) throws Exception {
        Account account = new Account();
        account.setUserId(userId);
        account.setUserName(username);
        account.setAvatar(password);
        return account;
    }
}
