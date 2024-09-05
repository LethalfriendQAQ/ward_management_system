package com.st.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.st.bean.Patient;
import com.st.bean.RespBean;
import com.st.service.NurseService;
import com.st.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( "/user/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private NurseService nurseService;

    @GetMapping
    public RespBean selectByPage(Integer pageNum, String pname) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 10);
        List<Patient> patientList = patientService.selectByPname(pname);
        PageInfo<Patient> pageInfo = new PageInfo<>(patientList);
        return RespBean.ok("查询成功", pageInfo);
    }
    @PostMapping
    public RespBean insert(@RequestBody Patient patient) {
        if (patientService.insert(patient)) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败，请重新添加");
        }
    }

    @DeleteMapping("/{pid}")
    public RespBean delete(@PathVariable("pid") Long pid) {

        patientService.delete(pid);
        return RespBean.ok("设置成出院状态成功");
    }
    @PutMapping
    public RespBean update(@RequestBody Patient patient) {
        if (patientService.update(patient)) {
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败，请重新修改");
        }
    }
    @GetMapping("/all")
    public RespBean selectAll() {
        List<Patient> patientList = patientService.selectAll();
        return RespBean.ok("", patientList);
    }
    @GetMapping("/{pid}")
    public RespBean selectByPid(@PathVariable("pid") Long pid) {
        Patient patient = patientService.selectByPid(pid);
        return RespBean.ok("", patient);
    }
}
