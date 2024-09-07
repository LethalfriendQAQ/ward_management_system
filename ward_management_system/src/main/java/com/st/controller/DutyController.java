package com.st.controller;

import com.st.bean.Duty;
import com.st.bean.RespBean;
import com.st.service.DutyService;
import com.st.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/user/duty")
public class DutyController {
    @Autowired
    private DutyService dutyService;
    @Autowired
    private NurseService nurseService;

    @GetMapping("/selectAll")
    public RespBean selectAll() {
        List<Duty> dutyList = dutyService.selectAll();
        return RespBean.ok("", dutyList);
    }

    @GetMapping("/allNurse")
    public RespBean allNurse() {
        List<Map<String, Object>> nurseMapList = nurseService.selectByNname(null)
                .stream()
                .map(n ->{
                    Map<String, Object> nurseMap = new HashMap<>();
                    nurseMap.put("key", n.getNid());
                    nurseMap.put("label", n.getNno() + "-" + n.getNname());

                    return nurseMap;
                }).collect(Collectors.toList());

        return RespBean.ok("", nurseMapList);
    }

}
