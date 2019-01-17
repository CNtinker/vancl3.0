package com.van.serviceImpl;

import com.van.dao.UserMapper;
import com.van.pojo.User;
import com.van.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAllUsers(Map<String, Object> map) {
        return userMapper.findAllUsers(map);
    }

    @Override
    public User findLoginPwdUser(String loginName, String pwd) {
        return userMapper.findLoginPwdUser(loginName,pwd);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUserById(Integer uid) {
        return userMapper.updateUserById(uid);
    }

    @Override
    public int deleteUserById(Integer uid) {
        return userMapper.deleteUserById(uid);
    }

    @Override
    public User findLoginUser(String login_name) {
        return userMapper.findLoginUser(login_name);
    }

    @Override
    public User findUserById(Integer uid) {
        return userMapper.findUserById(uid);
    }

    @Override
    public int updateUserState(Integer state, Integer uid) {
        return userMapper.updateUserState(state,uid);
    }
}
