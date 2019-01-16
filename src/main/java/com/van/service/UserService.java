package com.van.service;

import com.van.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    /*
     * 查询所有的用户
     *
     * **/
    List<User> findAllUsers(Map<String,Object> map);


    /*
     * 根据登录名和密码去查询用户
     *
     * **/
    User findLoginPwdUser(String loginName,String pwd);

    /*
     * 添加用户
     * **/
    int addUser(User user);

    /*
     * 根据用户id修改用户
     * */
    int updateUserById(Integer uid);

    /*
     *根据id删除用户
     *
     * **/
    int deleteUserById(Integer uid);

    User findLoginUser(String login_name);

    /*
     * 根据ID查询用户
     *
     * **/
    User findUserById(Integer uid);
    /*
     * 根据用户id 修改用户状态
     *
     * **/
    int updateUserState(Integer state,Integer uid);
}
