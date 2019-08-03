package com.jack.myblog.service.impl;

import com.jack.myblog.dao.CommentDao;
import com.jack.myblog.pojo.Comment;
import com.jack.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Comment 服务.
 * 
 * @since 1.0.0 2017年4月9日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.blog.service.CommentService#removeComment(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeComment(Long id) {
		commentDao.delete(id);
	}
	@Override
	public Comment getCommentById(Long id) {
		return commentDao.findOne(id);
	}

}
