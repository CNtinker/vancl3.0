package com.van.dao;

import com.van.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dzw on 2018/12/20.
 */
@Repository
public interface CategoryMapper {
    /**
     *
     * 查询一级分类
     */
    List<Category> findLv1Category();
    /**
     *
     * 根据父类id查询二级分类
     */
    List<Category> findLv2CategoryByP_id(Integer P_id);


}
