package com.zb.controller;

import com.alibaba.fastjson.JSON;
import com.zb.entity.Dept;
import com.zb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show() {
        return JSON.toJSONString(deptService.findDeptList());
    }

    @RequestMapping(value = "/findDeptById")
    @ResponseBody
    public String findDeptById(Integer id) {
        return JSON.toJSONString(deptService.findDeptById(id));
    }
}
