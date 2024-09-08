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

    @DeleteMapping("/{wid}")
    public RespBean delete(@PathVariable("wid") Long wid) throws SteduException {

        wardService.delete(wid);
        return RespBean.ok("删除成功");
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
    @GetMapping("/byDid/{did}")
    public RespBean selectByDid(@PathVariable("did") Integer did) {
        List<Ward> wardList = wardService.selectByDid(did);
        return RespBean.ok("", wardList);
    }
    @GetMapping("/byWid/{wid}")
    public RespBean selectByWid(@PathVariable("wid") Long wid) {
        Ward ward = wardService.selectByWid(wid);
        return RespBean.ok("", ward);
    }

    @GetMapping("/byWnumber/{wnumber}")
    public RespBean selectByWnumber(@PathVariable("wnumber") String  wnumber) {
        Ward ward = wardService.selectByWnumber(wnumber);
        return RespBean.ok("", ward);
    }
}
