package com.jack.myblog.service.impl;

import com.jack.myblog.dao.CatalogDao;
import com.jack.myblog.pojo.Catalog;
import com.jack.myblog.pojo.User;
import com.jack.myblog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Catalog 服务.
 * 
 */
@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogDao catalogDao;
	
	@Override
	public Catalog saveCatalog(Catalog catalog) {
		// 判断重复
		List<Catalog> list = catalogDao.findByUserAndName(catalog.getUser(), catalog.getName());
		if(list !=null && list.size() > 0) {
			throw new IllegalArgumentException("该分类已经存在了");
		}
		return catalogDao.save(catalog);
	}

	@Override
	public void removeCatalog(Long id) {
		catalogDao.delete(id);
	}

	@Override
	public Catalog getCatalogById(Long id) {
		return catalogDao.findOne(id);
	}

	@Override
	public List<Catalog> listCatalogs(User user) {
		return catalogDao.findByUser(user);
	}

}
