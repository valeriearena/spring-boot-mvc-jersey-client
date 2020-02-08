package com.mh.mhcureaccount.resource;

import com.mh.mhcureaccount.bean.UserAccount;
import com.mh.mhcureaccount.service.MHCureAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/account")
public class MHCureAccountResource {

    private static final String PARAM_USER_NAME = "userName";
    private static final String PARAM_CURRENT_PASSWORD = "currentPassword";
    private static final String PARAM_TOKEN = "token";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    private static final String PARAM_CONFIRM_PASSWORD = "confirmPassword";

    @Autowired
    private MHCureAccountService mhCureAccountService;

    @GET
    @Path("/ping")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return mhCureAccountService.ping()+"!!!";
    }

    @GET
    @Path("/token")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateToken(@QueryParam(PARAM_USER_NAME) String userName, @QueryParam(PARAM_TOKEN) String token){

        return mhCureAccountService.validateToken(userName, token);

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserAccount userAccount){
        return mhCureAccountService.registerResource(userAccount);
    }

    @PUT
    @Path("/forgot")
    @Produces(MediaType.APPLICATION_JSON)
    public Response forgotPassword(@QueryParam(PARAM_USER_NAME) String userName){
        return mhCureAccountService.forgotPasswordResource(userName);
    }


    public MHCureAccountService getMhCureAccountService() {
        return mhCureAccountService;
    }

    public void setMhCureAccountService(MHCureAccountService mhCureAccountService) {
        this.mhCureAccountService = mhCureAccountService;
    }
}
