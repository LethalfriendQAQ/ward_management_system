package com.st.controller;

import com.st.bean.RespBean;
import com.st.bean.Ward;
import com.st.exception.SteduException;
import com.st.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping( "/user/ward")
public class WardController {
    @Autowired
    private WardService wardService;

    @PostMapping
    public RespBean insert(@RequestBody Ward ward) throws SteduException {
        if (wardService.insert(ward)) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败，请重新添加");
        }
    }

    @DeleteMapping("/{pid}")
    public RespBean delete(@PathVariable("wid") Long wid) throws SteduException {

        wardService.delete(wid);
        return RespBean.ok("设置成出院状态成功");
    }
    @PutMapping
    public RespBean update(@RequestBody Ward ward) throws SteduException {
        if (wardService.update(ward)) {
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败，请重新修改");
        }
    }
    @GetMapping("/all")
    public RespBean selectAll() {
        List<Ward> wardList = wardService.selectAll();
        return RespBean.ok("", wardList);
    }
    @GetMapping("/{wid}")
    public RespBean selectByWid(@PathVariable("wid") Long wid) {
        Ward ward = wardService.selectByWid(wid);
        return RespBean.ok("", ward);
    }
}
