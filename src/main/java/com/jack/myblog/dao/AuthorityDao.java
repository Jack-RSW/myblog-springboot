package com.jack.myblog.dao;

import com.jack.myblog.pojo.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityDao extends JpaRepository<Authority,Long> {
}
