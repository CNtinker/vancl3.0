package com.van.serviceImpl;

import com.van.dao.CategoryMapper;
import com.van.dao.ProductMapper;
import com.van.pojo.Category;
import com.van.pojo.Product;
import com.van.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dzw on 2018/12/21.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> findAllById(Map<String, Object> map) {

        return productMapper.findAllById(map);
    }
}
