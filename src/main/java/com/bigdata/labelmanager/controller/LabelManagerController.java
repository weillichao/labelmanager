package com.bigdata.labelmanager.controller;


import com.bigdata.labelmanager.domain.CatagoryManager;
import com.bigdata.labelmanager.domain.LabelManager;
import com.bigdata.labelmanager.exception.MyException;
import com.bigdata.labelmanager.service.CatagoryManagerService;
import com.bigdata.labelmanager.service.LabelManagerService;
import com.bigdata.labelmanager.util.JsonUtil;
import com.bigdata.labelmanager.util.UuidUtil;
import com.bigdata.labelmanager.util.dateUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/labelmanagerlabel")
public class LabelManagerController {



        @Autowired
        LabelManagerService labelManagerService;
         @Autowired
        CatagoryManagerService  managerService;

        /***
         * 数据库数据查询
         * @return json
         */
        @RequestMapping(value="/search" ,method= RequestMethod.GET)
        public String searchLabel (@RequestParam(value="parameters",required=true)  String param){
            List<LabelManager> systems=new ArrayList<LabelManager>();
            if(param.equals("{}"))
            {
                systems= labelManagerService.searcherLabel();
            }
            else {
                JsonObject jsonObject = new JsonParser().parse(param).getAsJsonObject();
                String params=jsonObject.get("label_id").toString();
                String paramss =params.replace("\"", "");
                if(paramss.isEmpty())
                {
                    systems= labelManagerService.searcherLabel();
                }
                else {
                    systems = labelManagerService.searcherLabelNamebyID(paramss);
                }
            }
            String result=JsonUtil.getJsonbyList(systems);
            return result;
        }

        /***
         * 数据库数据增加
         * @return json
         */
        @RequestMapping(value="/add" ,method= RequestMethod.POST)
        public String InsertLabel(@RequestBody LabelManager ctgmanager) throws MyException {
            String result=null;
            ctgmanager.setLabel_id(UuidUtil.getUuid());
            ctgmanager.setCreate_date(dateUtil.getDate());
            ctgmanager.setUpdate_date(dateUtil.getDate());
            String name=managerService.searcherCatagoryNamebyID(ctgmanager.getCatagory_id());
            if(name.isEmpty())
            {
                throw new MyException("没查到标签类型");
            }
            ctgmanager.setCatagory_name(name);
            try {
                result = labelManagerService.InsertLabel(ctgmanager);
            }catch(Exception e)
            {
                throw new MyException("标签插入失败");
            }
            return result;
        }




        /***
         * 数据库数据更新
         * @return json
         */
        @RequestMapping(value="/update" ,method= RequestMethod.PUT)
        public String UpdateDatabase (@RequestBody  LabelManager ctgmanager) throws MyException {
            String result=null;
            ctgmanager.setUpdate_date(dateUtil.getDate());
            try {
               result = labelManagerService.UpdateLabel(ctgmanager);
            }catch(Exception e)
            {
                throw new MyException("标签更新失败");
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
        List<LabelManager> systems= labelManagerService.searcherLabelName();
        List<Map<String,String>> result =new ArrayList<Map<String,String>>();
        for(LabelManager sm:systems)
        {
            Map<String,String> list=new HashMap<String,String>();
            list.put("label",sm.getLabel_name());
            list.put("value",sm.getLabel_id());
            result.add(list);
        }
        System.out.println(systems.toString());
        return result;
    }

    @RequestMapping(value="/searchbyId" ,method= RequestMethod.GET)
    public String databaseSystembyId (@RequestParam(value="result",required=true)  String id) throws MyException {

        String result=null;
        List<LabelManager> systems=new ArrayList<LabelManager>();
        try {
           systems = labelManagerService.searcherLabelNamebyCatagoryID(id);
        }catch(Exception e)
        {
            throw new MyException("没找到标签");
        }

        result=JsonUtil.getJsonbyList(systems);
        return result;
    }
}
