package com.van.dao;

import com.van.pojo.Img;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dzw on 2019/1/3.
 */
@Repository
public interface GglbMapper {

    List<Img> findImgByPrantClass(String PrantClassId);

}
