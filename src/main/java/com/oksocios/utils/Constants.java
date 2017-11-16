package com.oksocios.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

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

    private static final Map<Integer, String> defaultProfilePictures;
    static
    {
        defaultProfilePictures = new HashMap<>();
        defaultProfilePictures.put(1, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-1.png");
        defaultProfilePictures.put(2, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-2.png");
        defaultProfilePictures.put(3, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-3.png");
        defaultProfilePictures.put(4, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-4.png");
        defaultProfilePictures.put(5, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-5.png");
        defaultProfilePictures.put(6, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-6.png");
        defaultProfilePictures.put(7, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-7.png");
        defaultProfilePictures.put(8, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-8.png");
        defaultProfilePictures.put(9, "https://s3-sa-east-1.amazonaws.com/oksocios/utils/avatars/U-9.png");
    }

    public static String getRandomImage(){
        Random r = new Random();
        int Low = 1;
        int High = 10;
        int result = r.nextInt(High-Low) + Low;
        return defaultProfilePictures.get(result);
    }
    
}
