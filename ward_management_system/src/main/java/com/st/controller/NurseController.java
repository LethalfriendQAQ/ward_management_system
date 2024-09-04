package com.st.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.st.bean.Nurse;
import com.st.bean.RespBean;
import com.st.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( "/user/nurse")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @GetMapping
    public RespBean selectByPage(Integer pageNum, String nname) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 5);
        List<Nurse> nurseList = nurseService.selectByNname(nname);
        PageInfo<Nurse> pageInfo = new PageInfo<>(nurseList);
        return RespBean.ok("查询成功", pageInfo);
    }
    @PostMapping
    public RespBean insert(@RequestBody Nurse nurse) {
        if (nurseService.insert(nurse)) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败，请重新添加");
        }
    }

    @DeleteMapping("/{nid}")
    public RespBean delete(@PathVariable("nid") Long nid) {

        nurseService.delete(nid);
        return RespBean.ok("删除成功");
    }
    @PutMapping
    public RespBean update(@RequestBody Nurse nurse) {
        if (nurseService.update(nurse)) {
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败，请重新修改");
        }
    }
    @GetMapping("/all")
    public RespBean selectAll() {
        List<Nurse> nurseList = nurseService.selectAll();
        return RespBean.ok("", nurseList);
    }
    @GetMapping("/byNid/{nid}")
    public RespBean selectByNid(@PathVariable("nid") Long nid) {
        Nurse nurse = nurseService.selectByNid(nid);
        return RespBean.ok("", nurse);
    }

    @GetMapping("/byDid/{did}")
    public RespBean selectByDid(@PathVariable("did") Integer did) {
        List<Nurse> nurseList = nurseService.selectByDid(did);
        return RespBean.ok("", nurseList);
    }

    @GetMapping("/byNno/{nno}")
    public RespBean selectByNno(@PathVariable("nno") String nno) {
        Nurse nurse = nurseService.selectByNno(nno);
        return RespBean.ok("", nurse);
    }
}
