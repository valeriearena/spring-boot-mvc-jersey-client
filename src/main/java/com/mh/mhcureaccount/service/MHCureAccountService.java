package com.mh.mhcureaccount.service;

import com.mh.mhcureaccount.bean.UserAccount;
import com.mh.mhcureaccount.resource.UserPasswordManagementData;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class MHCureAccountService {

    private static final String PARAM_USER_NAME = "userName";
    private static final String PARAM_CURRENT_PASSWORD = "currentPassword";
    private static final String PARAM_TOKEN = "token";
    private static final String PARAM_NEW_PASSWORD = "newPassword";
    private static final String PARAM_CONFIRM_PASSWORD = "confirmPassword";

    private Client client = ClientBuilder.newClient();

    public String ping(){

        WebTarget webTarget = client
                .target("http://localhost:8080/heartbeat")
                .path("/icapi/open/ping");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        UserPasswordManagementData data = response.readEntity(UserPasswordManagementData.class);

        return data.getMessage();
    }

    public Response validateToken(String userName, String token) {

        WebTarget webTarget = client
                .target("http://localhost:8080/heartbeat")
                .path("/icapi/open/account/token")
                .queryParam(PARAM_USER_NAME, userName)
                .queryParam(PARAM_TOKEN, token);


        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        return invocationBuilder.get();
    }

    public Response registerResource(UserAccount userAccount) {
        WebTarget webTarget = client
                .target("http://localhost:8080/heartbeat")
                .path("/icapi/open/account");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        return invocationBuilder.put(Entity.json(userAccount));
    }

    public Response forgotPasswordResource(String userName) {

        WebTarget webTarget = client
                .target("http://localhost:8080/heartbeat")
                .path("/icapi/open/account/forgot")
                .queryParam(PARAM_USER_NAME, userName);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        return invocationBuilder.put(Entity.json("{}"));
    }

    public UserAccount registerController(UserAccount userAccount) {
        WebTarget webTarget = client
                .target("http://localhost:8080/heartbeat")
                .path("/icapi/open/account");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.json(userAccount));
        UserPasswordManagementData data = response.readEntity(UserPasswordManagementData.class);

        if(response.getStatus() == 200){
            userAccount.setSuccessMessage(data.getMessage());
        }
        else{
            userAccount.setErrorMessage(data.getMessage());
        }
        return userAccount;
    }

    public UserAccount forgotPasswordController(UserAccount userAccount, String userName) {

        WebTarget webTarget = client
                .target("http://localhost:8080/heartbeat")
                .path("/icapi/open/account/forgot")
                .queryParam(PARAM_USER_NAME, userName);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.put(Entity.json("{}"));
        UserPasswordManagementData data = response.readEntity(UserPasswordManagementData.class);

        if(response.getStatus() == 200){
            userAccount.setSuccessMessage(data.getMessage());
        }
        else{
            userAccount.setErrorMessage(data.getMessage());
        }
        return userAccount;
    }

}
