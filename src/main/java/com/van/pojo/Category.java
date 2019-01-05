package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by dzw on 2018/12/20.
 */
@Getter
@Setter
public class Category {
    private Integer cc_id;
    private String cc_name;
    private Integer cc_parent_id;
    private List<Category> chile;
}
