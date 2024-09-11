package com.st.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.st.bean.Patient;
import com.st.bean.RespBean;
import com.st.exception.SteduException;
import com.st.service.NurseService;
import com.st.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping( "/user/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private NurseService nurseService;

    @GetMapping("/selectByPage")
    public RespBean selectByPage(Integer pageNum, String pname, Integer did, Integer pstatus) {
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, 10);
        //if (pstatus == null) {
        //    List<Patient> patientList = patientService.selectByPname(pname);
        //    PageInfo<Patient> pageInfo = new PageInfo<>(patientList);
        //    return RespBean.ok("查询成功", pageInfo);
        //} else {
        //    List<Patient> patientList = patientService.selectByConditions(pname, did, pstatus);
        //    PageInfo<Patient> pageInfo = new PageInfo<>(patientList);
        //    return RespBean.ok("查询成功", pageInfo);
        //}

        List<Patient> patientList = patientService.selectByConditions(pname, did, pstatus);
        PageInfo<Patient> pageInfo = new PageInfo<>(patientList);
        return RespBean.ok("查询成功", pageInfo);
    }
    @PostMapping
    public RespBean insert(@RequestBody Patient patient) throws SteduException {
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

    @GetMapping("/patientCountByDepartment")
    public List<Map<String, Object>> getPatientCountByDepartment() {
        return patientService.getPatientCountByDepartment();
    }
    @PostMapping("/upload")
    public RespBean upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Patient.class, new PageReadListener<Patient>(dataList -> {
            for (Patient patient : dataList) {
                try {
                    patientService.insert(patient);
                } catch (SteduException e) {
                    throw new RuntimeException(e);
                }
            }
        })).sheet().doRead();
        return RespBean.ok("上传成功");
    }
    @GetMapping("/download")
    public void downloadPatient(HttpServletResponse response) {
        try {
            // 设置下载响应头
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("患者信息表", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            // 模拟患者数据
            List<Patient> patients = patientService.selectAll(); // 获取患者列表

            // 使用EasyExcel写入Excel文件
            EasyExcel.write(response.getOutputStream(), Patient.class)
                    .sheet("患者信息")
                    .doWrite(patients);

        } catch (IOException e) {
            throw new RuntimeException("导出失败", e);
        }
    }


}
