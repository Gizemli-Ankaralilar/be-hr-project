package com.team1.constant;

public class EndPoints {

    public static final String VERSION="api/v1";
    public static final String COMPANY=VERSION+"/company";
    public static final String WORKER=VERSION+"/company-worker";

    //Genel
    public  static  final String FIND_ALL="/find_all";
    public  static  final String FIND_BY_ID="/find_by_id";
    public  static  final String DELETE_BY_ID="/delete_by_id";
    public  static  final String SAVE="/save";

    ///Company
    public  static  final String REGISTER="/register";
    public static final String SAVE_COMPANY=VERSION+"/save_company";


    // APO EKLEDİ
    public static final String UPDATE = "/update/{taxNumber}";
    public static final String DELETE = "/delete/{taxNumber}";
    public static final String FINDALL = "/findall";

    public static final String FINDALL_WORKER = "/findallworker";
    public static final String UPDATE_WORKER = "/updateworker";
    public static final String DELETE_WORKER = "/deleteworker";

    public static final String FINDBYID = "/findbyid";
    public static final String ADDCOMPANY = "/addcompany";
    public static final String CHECKCOMPANY = "/check-company";

}
