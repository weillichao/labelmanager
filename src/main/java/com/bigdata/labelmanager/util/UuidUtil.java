package com.bigdata.labelmanager.util;

import java.util.UUID;

public class UuidUtil {
    public static String getUuid()
    {
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        //System.out.println(uuidStr);
        return uuidStr;
    }
}
