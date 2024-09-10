package com.st.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.st.bean.Duty;
import com.st.bean.RespBean;
import com.st.exception.SteduException;
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

    @GetMapping("/selectByPage")
    public RespBean selectByPage(Integer pageNum) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 3);

        List<Duty> dutyList = dutyService.selectAll();
        PageInfo<Duty> pageInfo = new PageInfo<>(dutyList);
        System.out.println(pageInfo);
        return RespBean.ok("查询成功", pageInfo);
    }

    @PostMapping("/insert/{duty}")
    public RespBean insert(@RequestBody Duty duty) {
        //添加
        if (dutyService.insert(duty)) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败，请重新添加");
        }
    }

    @DeleteMapping("/{dutyId}")
    public RespBean delete(@PathVariable("dutyId") Long dutyId) {
        //删除
        if (dutyService.delete(dutyId)) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败，请重新添加");
        }
    }

    @PutMapping
    public RespBean update(@RequestBody Duty duty) throws SteduException {
        //修改
        if (dutyService.update(duty)) {
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败，请重新添加");
        }
    }

    @GetMapping("/selectByDutyId/{dutyId}")
    public RespBean selectByDutyId(@PathVariable("dutyId") Long dutyId) {
        return RespBean.ok("", dutyService.selectByDutyId(dutyId));
    }

    @GetMapping("/selectAll")
    public RespBean selectAll() {
        List<Duty> dutyList = dutyService.selectAll();

        return RespBean.ok("", dutyList);
    }

    @GetMapping("/allNurse/{dutyId}")
    public RespBean allNurse(@PathVariable("dutyId") Integer dutyId) {
        List<Map<String, Object>> nurseMapList = nurseService.selectByNname(null)
                .stream()
                .map(n ->{
                    Map<String, Object> nurseMap = new HashMap<>();

                    //检查department是否为null
                    String label = (n.getDepartment() != null) ?
                            n.getDepartment().getDname() + "-" + n.getNname():
                            n.getNname(); //如果department为null，则只显示名字

                    nurseMap.put("key", n.getNid());
                    nurseMap.put("label", n.getNno() + "-" + n.getNname());

                    return nurseMap;
                }).collect(Collectors.toList());

        //获取已经分配护士的nid
        List<Long> nids = dutyService.selectNidByDutyId(dutyId);

        Map<Object, Object> map = new HashMap<>();
        map.put("allNurse", nurseMapList);
        map.put("selectNids", nids);

        return RespBean.ok("", map);
    }

    @PostMapping("/dutyIdAndNid")
    public RespBean insertDutyIdAndNid(@RequestBody Map<String, Object> map) {
        System.out.println(map);

        Integer dutyId = (Integer) map.get("dutyId");
        List<Integer> nidsList = (List<Integer>) map.get("nids");
        Long[] nids = new Long[nidsList.size()];
        for (int i = 0;i < nidsList.size(); i++) {
            int nid = nidsList.get(i);
            nids[i] = new Long(nid + "");
        }
        dutyService.insertDutyIdAndNid(dutyId, nids);
        return RespBean.ok("分配护士成功");
    }
}
