package com.zb.mapper;

import com.zb.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    public List<Dept> findDeptList();

    public Dept findDeptById(@Param("id") Integer id);
}
