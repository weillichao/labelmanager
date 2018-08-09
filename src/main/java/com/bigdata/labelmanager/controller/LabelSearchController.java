package com.bigdata.labelmanager.controller;

import com.bigdata.labelmanager.domain.Condition;
import com.bigdata.labelmanager.domain.LabelManager;
import com.bigdata.labelmanager.exception.MyException;
import com.bigdata.labelmanager.service.CatagoryManagerService;
import com.bigdata.labelmanager.service.LabelManagerService;
import com.bigdata.labelmanager.service.LabelValueService;
import com.bigdata.labelmanager.util.DBUtil;
import com.bigdata.labelmanager.util.JsonUtil;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/labelsearch")
public class LabelSearchController {
    @Autowired
    LabelManagerService labelManagerService;
    @Autowired
    LabelValueService labelvalueService;



    private static Gson gson =new GsonBuilder().serializeNulls().create();

    /***
     * 标签查询
     * @return json
     */
    @RequestMapping(value="/search" ,method= RequestMethod.GET)
    public String searchLabel (@RequestParam(value="result",required=true) String result,@RequestParam(value="pageSize",required=true) String num,@RequestParam(value="page",required=true) String page) throws MyException {


        String jsonstring=null;
        List<Condition> userList =new ArrayList<Condition>();
        //计算分页信息
        int start =(Integer.valueOf(page)-1)*Integer.valueOf(num);
        //输入参数json解析
        try {
            JsonArray condition = new JsonParser().parse(result).getAsJsonArray();
            userList = gson.fromJson(condition, new TypeToken<List<Condition>>() {
            }.getType());
        }catch(Exception e)
        {
            throw new MyException("传入参数格式有误");
        }
        for(Condition cd :userList)
        {
            String id=labelvalueService.searcherLabelValueNamebyID(cd.getId()).getLabel_id();
            String name=labelManagerService.searcherLabelNamebyID(id).get(0).getLabel_alias();
            cd.setId(name);
        }
        System.out.println(userList .toString());


        //查找数据库
        try {
            List<Map<String, String>> result1 = DBUtil.find(userList, start, Integer.valueOf(num));
            Gson gson = new Gson();
            jsonstring = gson.toJson(result1);
        }catch(Exception e)
        {
            throw new MyException("标签查询失败");
        }
        JsonObject st=new  JsonObject();
        st.addProperty("data",jsonstring);
        st.addProperty("total",DBUtil.getNum(userList));

        String jsonresult = gson.toJson(st);

        return jsonresult;
    }



}
