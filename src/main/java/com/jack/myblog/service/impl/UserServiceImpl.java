package com.jack.myblog.service.impl;

import com.jack.myblog.dao.UserDao;
import com.jack.myblog.pojo.User;
import com.jack.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{

    @Autowired
    UserDao userDao;

    @Transactional
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    public void removeUsersInBatch(List<User> users) {
        userDao.deleteAllInBatch();
    }

    @Override
    public Page<User> listUsersByNameLike(String name, Pageable pageable) {
        name = "%"+name+"%";
        Page<User> users = userDao.findByNameLike(name, pageable);
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> listUsersByUsernames(Collection<String> usernames) {
        return userDao.findByUsernameIn(usernames);
    }

}
