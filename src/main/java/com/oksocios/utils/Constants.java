package com.oksocios.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {

    public static final String STATUS_NAME_PENDING = "Pendiente";
    public static final int STATUS_KEY_PENDING = 0;
    public static final String STATUS_NAME_ACTIVE = "Activo";
    public static final int STATUS_KEY_ACTIVE = 1;

    public static final String ROLE_NAME_ADMIN = "ROLE_ADMIN";
    public static final int ROLE_KEY_ADMIN = 0;
    public static final String ROLE_NAME_CUSTOMER = "ROLE_CUSTOMER";
    public static final int ROLE_KEY_CUSTOMER = 1;
    public static final String ROLE_NAME_EMPLOYEE = "ROLE_EMPLOYEE";
    public static final int ROLE_KEY_EMPLOYEE = 2;

    private static final Map<Integer, String> roleMap;
    static
    {
        roleMap = new HashMap<>();
        roleMap.put(ROLE_KEY_ADMIN, ROLE_NAME_ADMIN);
        roleMap.put(ROLE_KEY_CUSTOMER, ROLE_NAME_CUSTOMER);
        roleMap.put(ROLE_KEY_EMPLOYEE, ROLE_NAME_EMPLOYEE);
    }

    public static String getRoleName(int key){
        return roleMap.get(key);
    }
    
}
