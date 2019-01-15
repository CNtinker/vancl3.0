package com.van.service;

import com.van.pojo.P_pk_children;

import java.util.List;

/**
 * Created by dzw on 2019/1/8.
 */
public interface ProductService {

     List<P_pk_children> findP_Pk_childrensByP_id(Integer P_id,Integer biao_id);

    String findColorByColorId(Integer color_id);

    String findSizeByColorId(Integer Size_id);

    Integer findColorIdByColor(String Color);

    Integer findSizeIdBySize(String Size);

}
