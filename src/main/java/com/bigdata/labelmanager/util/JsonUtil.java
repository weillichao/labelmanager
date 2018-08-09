package com.bigdata.labelmanager.util;


import com.bigdata.labelmanager.domain.CatagoryManager;
import com.bigdata.labelmanager.service.CatagoryManagerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

public class JsonUtil {




    private static Gson gson =new GsonBuilder().serializeNulls().create();

    public static String getJsonbyList(List<?> ds)
    {

        return gson.toJson(ds);
    }






}
