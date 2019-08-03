package com.jack.myblog.service.impl;

import com.jack.myblog.dao.VoteDao;
import com.jack.myblog.pojo.Vote;
import com.jack.myblog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Vote 服务.
 * 
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteDao voteDao;
	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.blog.service.VoteService#removeVote(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeVote(Long id) {
		voteDao.delete(id);
	}
	@Override
	public Vote getVoteById(Long id) {
		return voteDao.findOne(id);
	}

}
