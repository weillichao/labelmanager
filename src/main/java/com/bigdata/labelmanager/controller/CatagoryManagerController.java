package com.bigdata.labelmanager.controller;

import com.bigdata.labelmanager.domain.CatagoryManager;
import com.bigdata.labelmanager.domain.LabelManager;
import com.bigdata.labelmanager.domain.LabelValueManager;
import com.bigdata.labelmanager.exception.MyException;
import com.bigdata.labelmanager.service.CatagoryManagerService;
import com.bigdata.labelmanager.service.LabelManagerService;
import com.bigdata.labelmanager.service.LabelValueService;
import com.bigdata.labelmanager.util.JsonUtil;
import com.bigdata.labelmanager.util.UuidUtil;
import com.bigdata.labelmanager.util.dateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 数据库类型操作类
 * @return json
 */



@RestController
@RequestMapping("/labelmanagercatagory")
public class CatagoryManagerController {

    @Autowired
    CatagoryManagerService catagoryManagerService;
    @Autowired
    LabelManagerService labelManagerService;
    @Autowired
    LabelValueService labelValueService;

    /***
     * 数据库数据查询
     * @return json
     */
    @RequestMapping(value="/search" ,method= RequestMethod.GET)
    public String databaseSystem (@RequestParam(value="parameters",required=true)  String param)
    {
        List<CatagoryManager> systems=new ArrayList<CatagoryManager>();
        if(param.equals("{}"))
        {
            systems= catagoryManagerService.searcherCatagory();
        }
        else {
            JsonObject jsonObject = new JsonParser().parse(param).getAsJsonObject();
            String params=jsonObject.get("catagory_id").toString();
            String paramss =params.replace("\"", "");
            if(paramss.isEmpty())
            {
                systems= catagoryManagerService.searcherCatagory();
            }
            else {
                systems = catagoryManagerService.searcherCatagorybyID(paramss);
            }
        }
        String result=JsonUtil.getJsonbyList(systems);
        return result;
    }





    /***
     * 数据库数据查询
     * @return json
     */
    @RequestMapping(value="/searchall" ,method= RequestMethod.GET)
    public String databaseSystemAll () throws Exception {
        List<CatagoryManager> systems=new ArrayList<CatagoryManager>();
        try {
          systems = catagoryManagerService.searcherCatagory();

        }catch(Exception e)
        {
            throw new MyException("数据库查询失败");
        }


        String result=JsonUtil.getJsonbyList(systems);
        return result;
    }




    /***
     * 数据库数据增加
     * @return json
     */
    @RequestMapping(value="/add" ,method= RequestMethod.POST)
    public String InsertDatabase (@RequestBody  CatagoryManager ctgmanager) throws MyException {
        //CatagoryManager dbmanager =JsonUtil.getDatabasebyJson(systems);
        ctgmanager.setCatagory_id(UuidUtil.getUuid());
        ctgmanager.setCreate_date(dateUtil.getDate());
        String result=null;
        try {
            result = catagoryManagerService.InsertCatagory(ctgmanager);
        }catch(Exception e)
        {
            throw new MyException("数据库插入失败");
        }
        return result;
    }




    /***
     * 数据库数据更新
     * @return json
     */
    @RequestMapping(value="/update" ,method= RequestMethod.PUT)
    public String UpdateDatabase (@RequestBody  CatagoryManager ctgmanager) throws MyException {
        String result=null;
        try {
            result = catagoryManagerService.UpdateCatagory(ctgmanager);
        }
      catch(Exception e)
      {
                throw new MyException("数据库更新失败");
      }
        return result;
    }



    /***
     * 数据库数据更新
     * @return json
     */
    @RequestMapping(value="/getLabel" ,method= RequestMethod.GET)
    public  List<Map<String,String>> getLabel ()
    {
        List<CatagoryManager> systems= catagoryManagerService.searcherCatagoryName();
        List<Map<String,String>> result =new ArrayList<Map<String,String>>();
        for(CatagoryManager sm:systems)
        {
            Map<String,String> list=new HashMap<String,String>();
            list.put("label",sm.getCatagory_name());
            list.put("value",sm.getCatagory_id());
            result.add(list);
        }
        System.out.println(systems.toString());
        return result;
    }


    /***
     * 生成标签树json
     * @return json
     */
    @RequestMapping(value="/getLabelTree" ,method= RequestMethod.GET)
    public  String getLabelTree ()
    {
        List<CatagoryManager> ss=catagoryManagerService.searcherCatagory();
        JsonArray jsa=new JsonArray();
        for(CatagoryManager sm :ss)
        {

            JsonObject jso=new JsonObject();
            jso.addProperty("id",sm.getCatagory_id());
            jso.addProperty("text",sm.getCatagory_name());
            jso.addProperty("expanded", true);
            List<LabelManager> lm=labelManagerService.searcherLabelNamebyCatagoryID(sm.getCatagory_id());
            JsonArray jsa1=new JsonArray();
            for(LabelManager lms :lm)
            {

                JsonObject jso1=new JsonObject();
                jso1.addProperty("id",lms.getLabel_id());
                jso1.addProperty("text",lms.getLabel_name());
                jso1.addProperty("expanded", true);
                List<LabelValueManager> lv=labelValueService.searcherLabelbyID(lms.getLabel_id());
                JsonArray jsa2=new JsonArray();
                for(LabelValueManager lvs :lv)
                {

                    JsonObject jso2=new JsonObject();
                    jso2.addProperty("id",lvs.getId());
                    jso2.addProperty("text",lvs.getLabel_value());
                    jsa2.add(jso2);
                }
                jso1.add("items",jsa2);
                jsa1.add(jso1);
            }
            jso.add("items",jsa1);
            jsa.add(jso);


        }
        Gson gson = new Gson();
        String jsonstring = gson.toJson(jsa);


        return jsonstring;

    }




}
