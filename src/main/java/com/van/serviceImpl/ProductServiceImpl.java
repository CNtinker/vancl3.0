package com.van.serviceImpl;

import com.van.dao.ProductMapper;
import com.van.pojo.P_pk_children;
import com.van.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dzw on 2019/1/8.
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<P_pk_children> findP_Pk_childrensByP_id(Integer P_id,Integer biao_id) {
        return productMapper.findP_Pk_childrensByP_id(P_id,biao_id);
    }

    @Override
    public String findColorByColorId(Integer color_id) {
        return productMapper.findColorByColorId(color_id);
    }

    @Override
    public String findSizeByColorId(Integer Size_id) {
        return productMapper.findSizeByColorId(Size_id);
    }
}
