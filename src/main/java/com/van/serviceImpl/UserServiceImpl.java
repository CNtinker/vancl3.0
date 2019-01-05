package com.van.serviceImpl;

import com.van.dao.UserMapper;
import com.van.pojo.User;
import com.van.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findByIdUser(Integer uid) {
        return userMapper.findByIdUser(uid);
    }

    @Override
    public User findLoginPwdUser(User user) {
        return userMapper.findLoginPwdUser(user);
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
}
