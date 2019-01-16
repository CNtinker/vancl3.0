package com.van.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Areas {

	private int ar_id;        //id
	private String ar_name;   //省名
	private int ar_parent_id;    //父类ID

	private List<Areas> list;  //对应子集的一个集合



}
