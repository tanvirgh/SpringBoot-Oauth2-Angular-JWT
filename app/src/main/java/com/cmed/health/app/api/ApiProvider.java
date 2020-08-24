package com.cmed.health.app.api;

/**
 * Creator : Tanvir Chowdhury
 * Date    : 2020-08-23
 */
public class ApiProvider {

    public static final String BASE_PATH = "/api";



    public static final class UserApi {
        public static final String ROOT_PATH = BASE_PATH + "/" + ApiConstants.USERS;
        public static final String USERID = "/" + "{" + ApiConstants.USER_ID + "}";
    }

    public static final class PrescriptionApi {
        public static final String ROOT_PATH = BASE_PATH + "/" + ApiConstants.PRESCRIPTIONS;
        public static final String PRESCRIPTIONID = "/" + "{" + ApiConstants.PRESCRIPTION_ID + "}";
    }


}
