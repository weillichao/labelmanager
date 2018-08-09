package com.bigdata.labelmanager.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.bigdata.labelmanager.domain.LabelManager;
import com.bigdata.labelmanager.domain.LabelValueManager;
import com.bigdata.labelmanager.exception.MyException;
import com.bigdata.labelmanager.service.LabelManagerService;
import com.bigdata.labelmanager.service.LabelValueService;
import com.bigdata.labelmanager.util.JsonUtil;
import com.bigdata.labelmanager.util.UuidUtil;
import com.bigdata.labelmanager.util.dateUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/labelvaluemanager")
public class LabelValueManagerController {

    @Autowired
    LabelValueService labelValueService;
    @Autowired
    LabelManagerService labelManagerService;

    /***
     * 数据库数据查询
     * @return json
     */
    @RequestMapping(value="/search" ,method= RequestMethod.GET)
    public String searchLabel (@RequestParam(value="parameters",required=true)  String param){



        List<LabelValueManager> systems=new ArrayList<LabelValueManager>();
        if(param.equals("{}"))
        {
           // systems= labelValueService.searcherLabel();
        }
        else {
            JsonObject jsonObject = new JsonParser().parse(param).getAsJsonObject();
            String params=jsonObject.get("label_id").toString();
            String paramss =params.replace("\"", "");
            if(paramss.isEmpty())
            {
                //systems= labelValueService.searcherLabel();
            }
            else {
                systems = labelValueService.searcherLabelbyID(paramss);
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
    public String InsertDatabase (@RequestBody LabelValueManager ctgmanager) throws MyException {
        //CatagoryManager dbmanager =JsonUtil.getDatabasebyJson(systems);
        ctgmanager.setId(UuidUtil.getUuid());
        ctgmanager.setParent_id(UuidUtil.getUuid());
        ctgmanager.setCreate_date(dateUtil.getDate());
        ctgmanager.setOperation("create");
        String result=null;
        try {

            String label_name = (labelManagerService.searcherLabelNamebyID(ctgmanager.getLabel_id())).get(0).getLabel_name();
            ctgmanager.setLabel_name(label_name);
            result = labelValueService.InsertLabel(ctgmanager);
        }catch(Exception e)
        {
            throw new MyException("标签值插入失败");
        }
        return result;
    }




    /***
     * 数据库数据更新
     * @return json
     */
    @RequestMapping(value="/update" ,method= RequestMethod.PUT)
    public String UpdateDatabase (@RequestBody  LabelValueManager ctgmanager) throws MyException {
        String result=null;
        LabelValueManager oldlabelvalue=labelValueService.searcherLabelValueNamebyID(ctgmanager.getId());
        oldlabelvalue.setStatus("INACTIVE");
        ctgmanager.setId(UuidUtil.getUuid());
        labelValueService.UpdateLabel(oldlabelvalue);
        ctgmanager.setOperation("update");
        ctgmanager.setParent_id(oldlabelvalue.getId());
        ctgmanager.setCreate_date(dateUtil.getDate());
        try {
           result = labelValueService.InsertLabel(ctgmanager);
        }catch(Exception e)
        {
            throw new MyException("标签值更新失败");
        }

        return result;
    }

    @RequestMapping(value="/searchbyId" ,method= RequestMethod.GET)
    public String searchLabelbyId (@RequestParam(value="result",required=true)  String param) throws MyException {

        List<LabelValueManager> systems=new ArrayList<LabelValueManager>();
        try {
            systems = labelValueService.searcherLabelbyID(param);
        }catch(Exception e)
        {
            throw new MyException("标签查询失败");
        }

        String result=JsonUtil.getJsonbyList(systems);
        return result;
    }

}
