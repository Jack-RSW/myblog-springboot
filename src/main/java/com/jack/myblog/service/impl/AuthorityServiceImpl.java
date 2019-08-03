package com.jack.myblog.service.impl;

import com.jack.myblog.dao.AuthorityDao;
import com.jack.myblog.dao.UserDao;
import com.jack.myblog.pojo.Authority;
import com.jack.myblog.pojo.User;
import com.jack.myblog.service.AuthorityService;
import com.jack.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityDao.findOne(id);
    }
}
