package com.van.serviceImpl;

import com.van.dao.AreasMapper;
import com.van.pojo.Areas;
import com.van.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreasServiceImpl implements AreasService{
    @Autowired
    private AreasMapper areasMapper;
    //查询省
    @Override
    public List<Areas> findAllProvence() {
        return areasMapper.findAllProvence();
    }
    //查询市
    @Override
    public List<Areas> findAllCityByPid(Integer ar_parent_id) {
        return areasMapper.findAllCityByPid(ar_parent_id);
    }
    //查询区
    @Override
    public List<Areas> findAllQuByarid(Integer cr_parent_id) {
        return areasMapper.findAllQuByarid(cr_parent_id);
    }
}
