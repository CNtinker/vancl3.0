package com.van.dao;

import com.van.pojo.Areas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreasMapper {

    //查询省
    List<Areas> findAllProvence();
    //查询市
    List<Areas> findAllCityByPid(Integer ar_parent_id);
    //查询区
    List<Areas> findAllQuByarid(Integer cr_parent_id);

}
