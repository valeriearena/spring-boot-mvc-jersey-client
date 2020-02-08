package com.mh.mhcureaccount.enums;

/**
 * Created by michaelkellstrand on 12/1/16.
 */
public enum ServiceResponseStatus
{
    SUCCESS,
    FAILURE,
    ERRORS,

    /*FORCE_LOGOUT,*/   // TODO - Do we want this?

    MISSING_TOKEN,
    INVALID_TOKEN,
    EXPIRED_TOKEN,

    CLIENT_VERSION,         // Init - client version obsolete
    DEVICE_TYPE_CHANGE,     // Init - user changing device types
    NO_PRIMARY_HOSPITAL,    // Init - primary hospital must be selected

    DOWN_FOR_MAINTENENCE,

    // Incoming VOIP Calls
    CALL_EXTENSION_AVAILABLE,
    CALL_EXTENSION_DND,
    CALL_EXTENSION_UNKNOWN;
}
