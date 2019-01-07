package com.van.dao;

import com.van.pojo.Category;
import com.van.pojo.Img;
import com.van.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2018/12/21.
 */
@Repository
public interface ProductMapper {
    //查询所有的商品
    List<Product> findAllById(Map<String, Object> map);
    //根据Prant_class查询图片表的img_path(图片路径)
    List<Img> findImgByPrant_class(String PrantClass);

}
