package com.jack.myblog.dao;

import com.jack.myblog.pojo.Catalog;
import com.jack.myblog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Catalog 仓库.
 *
 */
public interface CatalogDao extends JpaRepository<Catalog, Long> {
	
	/**
	 * 根据用户查询
	 * @param user
	 * @return
	 */
	List<Catalog> findByUser(User user);
	
	/**
	 * 根据用户查询
	 * @param user
	 * @param name
	 * @return
	 */
	List<Catalog> findByUserAndName(User user, String name);
}
