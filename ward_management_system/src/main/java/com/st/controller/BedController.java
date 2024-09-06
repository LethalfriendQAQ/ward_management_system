package com.st.controller;


import com.st.bean.Bed;
import com.st.bean.RespBean;
import com.st.exception.SteduException;
import com.st.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user/bed")
public class BedController {
    @Autowired
    public BedService bedService;

    @PostMapping
    public RespBean insert(@RequestBody Bed bed) throws SteduException {
        //添加
        if (bedService.insert(bed)) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败，请重新添加");
        }
    }

    @DeleteMapping("/{bid}")
    public RespBean delete(@PathVariable("bid") Long bid) throws SteduException {
        //添加
        if (bedService.delete(bid)) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败，请重新添加");
        }
    }
    @PutMapping
    public RespBean update(@RequestBody Bed bed) throws SteduException {
        //添加
        if (bedService.update(bed)) {
            return RespBean.ok("修改成功");
        } else {
            return RespBean.error("修改失败，请重新添加");
        }
    }

    @GetMapping("/{bid}")
    public RespBean selectByDid(@PathVariable("bid") Long bid) {
        Bed b = bedService.selectByBid(bid);
        return RespBean.ok("",b);
    }
    @GetMapping
    public RespBean selelctAll() {
        List<Bed> bedList = bedService.selectAll();
        return RespBean.ok("",bedList);
    }

    // 根据病房号查询空闲病床
    @GetMapping("/selectFreeBedsByWnumber/{wnumber}")
    public RespBean selectFreeBedsByWnumber(@RequestParam String wnumber) {
        List<Bed> freeBeds = bedService.selectFreeBedsByWnumber(wnumber);
        return RespBean.ok("",freeBeds);
    }
}
