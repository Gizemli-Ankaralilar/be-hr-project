package com.team1.constant;

public class EndPoints {

    public static final String VERSION="api/v1";
    public static final String AUTH=VERSION+"/auth";

    //Genel
    public  static  final String FIND_ALL="/find_all";
    public  static  final String UPDATE="/update";
    public  static  final String FIND_BY_ID="/find_by_id";
    public  static  final String DELETE_BY_ID="/delete_by_id";
    public  static  final String SAVE="/save";

    ///Auth
    public  static  final String VISITOR_REGISTER="/visitor-register";
    public  static  final String LOGIN="/login";
    public  static  final String ACTIVATE_STATUS="/activate_status";
    public static final String REGISTER_COMPANY=VERSION+"/save_company";
}
