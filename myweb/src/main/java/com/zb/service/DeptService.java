package com.zb.service;

import com.zb.entity.Dept;

import java.util.List;

public interface DeptService  {
    public List<Dept> findDeptList();

    public Dept findDeptById( Integer id);

    public void writerDeptAddNum(Integer id);

    public Integer readerDeptByIdViewCount(Integer id);
}
