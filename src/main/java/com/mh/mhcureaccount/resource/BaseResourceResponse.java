package com.mh.mhcureaccount.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mh.mhcureaccount.enums.ServiceResponseStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResourceResponse
{
    protected boolean success = true;     // true if the request was processed successfully. false if there was an error, in which case the following fields will contain additional information.

    protected ServiceResponseStatus status = ServiceResponseStatus.SUCCESS;   // an enum of supported response status values

    public BaseResourceResponse() {
    }

    public BaseResourceResponse(boolean success, ServiceResponseStatus status) {
        this.success = success;
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ServiceResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceResponseStatus status) {
        this.status = status;
    }

}
