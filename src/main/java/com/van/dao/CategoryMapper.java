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



    //根据分类id查询所属父类id
    int findPid(Integer cc_id);

    //添加分类
    int addCate(Category ctg);

    //根据cc_id删除分类
    int delCate(Integer cc_id);


}
