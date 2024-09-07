package com.st.controller;

import com.st.bean.Department;
import com.st.bean.RespBean;
import com.st.exception.SteduException;
import com.st.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public RespBean insert(@RequestBody Department department) throws SteduException {
        //添加
        if (departmentService.insert(department)) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败，请重新添加");
        }
    }

    @DeleteMapping("/{did}")
    public RespBean delete(@PathVariable("did") Integer did) throws SteduException {
        //删除
        if (departmentService.delete(did)) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败，请重新添加");
        }
    }
    @PutMapping
    public RespBean update(@RequestBody Department department) throws SteduException {
        //修改
        if (departmentService.update(department)) {
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败，请重新添加");
        }
    }

    @GetMapping("/{did}")
    public RespBean selectByDid(@PathVariable("did") Integer did) {
        Department d = departmentService.selectByDid(did);
        return RespBean.ok("",d);
    }
    @GetMapping
    public RespBean selelctAll() {
        List<Department> departmentList = departmentService.selectAll();
        return RespBean.ok("",departmentList);
    }
}
