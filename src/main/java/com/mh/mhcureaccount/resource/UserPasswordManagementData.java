package com.mh.mhcureaccount.resource;

import com.mh.mhcureaccount.enums.ServiceResponseStatus;

public class UserPasswordManagementData extends BaseResourceResponse {

    private String message;

    public UserPasswordManagementData(){}

    public UserPasswordManagementData(String message){
        this.message = message;
    }

    public UserPasswordManagementData(String message, boolean success, ServiceResponseStatus serviceResponseStatus){
        super(success, serviceResponseStatus);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
