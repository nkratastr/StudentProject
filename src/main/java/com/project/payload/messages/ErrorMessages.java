package com.project.payload.messages;

public class ErrorMessages {
    private ErrorMessages() {
    }
    public static final String NOT_PERMITTED_METHOD_MESSAGE="You dont have a permission to do this operation";
    public static final String PASSWORD_NOT_MATCHED="Your password not matched";


    public static final String ROLE_NOT_FOUND = "There is no role like that, check the database";
    public static final String NOT_FOUND_USER_USERROLE_MESSAGE = "Error: User not found with user-role %s";

    public static final String ALREADY_REGISTER_MESSAGE_USERNAME = "Error : User with username %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_SSN = "Error : User with ssn %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_PHONE = "Error : User with phone number %s is already registered";
    public static final String ALREADY_REGISTER_MESSAGE_EMAIL = "Error : User with e-mail %s is already registered";

    public static final String NOT_FOUND_USER_MESSAGE = "Error: User not found with id : %s";




}