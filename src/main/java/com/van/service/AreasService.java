package com.van.service;

import com.van.pojo.Areas;

import java.util.List;

public interface AreasService {

    //查询省
    List<Areas> findAllProvence();
    //查询市
    List<Areas> findAllCityByPid(Integer ar_parent_id);
    //查询区
    List<Areas> findAllQuByarid(Integer cr_parent_id);
}
