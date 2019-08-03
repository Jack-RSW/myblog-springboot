package com.jack.myblog.dao;

import com.jack.myblog.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment,Long> {
}
