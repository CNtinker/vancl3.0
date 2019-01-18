package com.van.service;

import com.van.pojo.P_pk_children;
import com.van.pojo.Product;

import java.util.List;

/**
 * Created by dzw on 2019/1/8.
 */
public interface ProductService {

     List<P_pk_children> findP_Pk_childrensByP_id(Integer P_id,Integer biao_id);

    String findColorByColorId(Integer color_id);


    String findSizeByColorId(Integer Size_id);

    //添加商品
    int addProduct(Product pd);

    //根据商品id查询商品信息
    Product findProductById(Integer p_id);

    //根据商品id修改商品信息
    int updateProductById(Product pd);

    //根据商品id删除商品
    int deleteProductById(Integer p_id);



    Integer findColorIdByColor(String Color);

    Integer findSizeIdBySize(String Size);

}
