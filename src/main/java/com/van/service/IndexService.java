package com.van.service;

import com.van.pojo.Category;
import com.van.pojo.Img;

import java.util.List;

/**
 * Created by dzw on 2018/12/20.
 */
public interface IndexService {
    /**
     *
     * 查询所有分类
     */
    List<Category> findAllCategory();
/*根据图片prant_class查找图片路径*/
    List<Img> findImgByPrantClass(String PrantClassId);
}
