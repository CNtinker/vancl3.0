package com.van.serviceImpl;

import com.van.dao.ProductMapper;
import com.van.pojo.P_pk_children;
import com.van.pojo.Product;
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
    public List<P_pk_children> findP_Pk_childrensByP_id(Integer P_id,Integer biao_id) { return productMapper.findP_Pk_childrensByP_id(P_id,biao_id); }

    @Override
    public String findColorByColorId(Integer color_id) {
        return productMapper.findColorByColorId(color_id);
    }

    @Override
    public String findSizeByColorId(Integer Size_id) {
        return productMapper.findSizeByColorId(Size_id);
    }


    //根据商品id查询商品信息
    @Override
    public Product findProductById(Integer p_id) {
        return productMapper.findProductById(p_id);
    }

    //根据商品id修改商品信息
    @Override
    public int updateProductById(Product pd) {
        return productMapper.updateProductById(pd);
    }
   //添加商品
    @Override
    public int addProduct(Product pd) {
        return productMapper.addProduct(pd);
    }

    //根据商品id删除商品
    @Override
    public int deleteProductById(Integer p_id) {
        return productMapper.deleteProductById(p_id);
    }

    @Override
    public Integer findColorIdByColor(String Color){ return productMapper.findColorIdByColor(Color);}

    @Override
    public Integer findSizeIdBySize(String Size){return productMapper.findSizeIdBySize(Size);}

    @Override
    public Integer updateStockByP_id(Integer P_id,Integer P_stock) {
        return productMapper.updateP_stockByP_id(P_id,P_stock);
    }
}
