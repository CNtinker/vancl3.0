package com.van.serviceImpl;

import com.van.dao.CategoryMapper;
import com.van.dao.GglbMapper;
import com.van.pojo.Category;
import com.van.pojo.Img;
import com.van.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dzw on 2018/12/20.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private GglbMapper gglbMapper;


    @Override
    public List<Category> findAllCategory(){
        /*list用于接收一级分类*/
        List<Category> list=categoryMapper.findLv1Category();
        /*遍历一级分类，将二级分类加入一级分类子类(chile)*/
        for(Category c1:list ){
            /*list2用于接收二级分类*/
            List<Category> list2=categoryMapper.findLv2CategoryByP_id(c1.getCc_id());
            c1.setChile(list2);
        }
        return list;
    }

    @Override
    public List<Img> findImgByPrantClass(String PrantClassId) {


        return gglbMapper.findImgByPrantClass(PrantClassId);
    }
}