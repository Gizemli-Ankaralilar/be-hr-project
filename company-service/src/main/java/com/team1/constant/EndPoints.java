package com.team1.constant;

public class EndPoints {

    public static final String VERSION="api/v1";
    public  static  final String REGISTER="/register";
    public static final String COMPANY=VERSION+"/company";
    public static final String WORKER=VERSION+"/worker";
    public static final String FINANCE=VERSION+"/finance";
    public static final String PERMIT=VERSION+"/permit";
    public static final String COMMENT=VERSION+"/comment";

    ///Company
    public static final String SAVE_COMPANY=VERSION+"/save/company";
    public  static  final String FIND_ALL_COMPANY="/find_all/company";
    public  static  final String FIND_ALL_WORKER="/find_all/worker";

    //Worker
    public static final String FINDALL_WORKER = "/find_all/worker";
    public  static final String DELETE_BY_ID_WORKER="/delete_by_id/worker";
    public static final String SAVE_WORKER=VERSION+"/save/worker";

    //Permit
    public static final String FINDALL_PERMIT = "/find_all/permit";
    public  static final String DELETE_BY_ID_PERMIT="/delete_by_id/permit";
    public static final String SAVE_PERMIT=VERSION+"/save/permit";

    //Comment
    public static final String FINDALL_COMMENT = "/find_all/worker";
    public  static final String DELETE_BY_ID_COMMENT="/delete_by_id/comment";
    public static final String SAVE_COMMENT=VERSION+"/save/worker";

    //Finance
    public static final String FINDALL_FINANCE = "/find_all/finance";
    public  static final String DELETE_BY_ID_FINANCE="/delete_by_id/finance";
    public static final String SAVE_FINANCE=VERSION+"/save/finance";


}
