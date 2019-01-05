package com.van.dao;

import com.van.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
/*
 * 查询所有的用户
 *
 * **/
    List<User> findAllUser();

    /*
     * 根据用户id查询用户
     *
     * **/
    User findByIdUser(Integer uid);

    /*
     * 根据登录名和密码去查询用户
     *
     * **/
    User findLoginPwdUser(User user);

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
    /*
    * 根据用户名查询用户
    *
    * **/
    User findLoginUser(String login_name);
}
