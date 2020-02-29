package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.entity.Dept;
import com.zb.mapper.DeptMapper;
import com.zb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper ;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public List<Dept> findDeptList() {
        return deptMapper.findDeptList();
    }

    @Override
    public Dept findDeptById(Integer id) {
        Dept dept =null;
        //获取访问次数
        Integer count = this.readerDeptByIdViewCount(id);
        if(count!=null && count%10==0){
            //检查缓存中是否存储信息
            if(redisTemplate.hasKey("deptObj:"+id)){
                String json = redisTemplate.opsForValue().get("deptObj:"+id);
                dept= JSON.parseObject(json,Dept.class);
                System.out.println("查询缓存数据");
            }else{
                System.out.println("访问10次之后查询查询数据库");
                dept= deptMapper.findDeptById(id);
                redisTemplate.opsForValue().set("deptObj:"+id,JSON.toJSONString(dept));
                redisTemplate.expire("deptObj:"+id,10, TimeUnit.SECONDS);
            }
        }else {
            if(redisTemplate.hasKey("deptObj:"+id)){
                String json = redisTemplate.opsForValue().get("deptObj:"+id);
                dept= JSON.parseObject(json,Dept.class);
                System.out.println("10次之后查询缓存数据");
            }else {
                dept = deptMapper.findDeptById(id);
                System.out.println("查询数据库");
            }
        }
        this.writerDeptAddNum(id);
        return dept;
    }

    @Override
    public void writerDeptAddNum(Integer id) {
        redisTemplate.opsForValue().increment("dept:"+id,1);
    }

    @Override
    public Integer readerDeptByIdViewCount(Integer id) {
        String s = redisTemplate.opsForValue().get("dept:" + id);
        if(s==null){
            return null;
        }
        return Integer.parseInt(s);
    }
}
