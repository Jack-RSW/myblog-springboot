package com.jack.myblog.dao;

import com.jack.myblog.pojo.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Vote 仓库.
 *
 */
public interface VoteDao extends JpaRepository<Vote, Long> {
 
}
