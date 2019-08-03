package com.jack.myblog.dao.es;

import com.jack.myblog.pojo.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Blog 存储库.
 * 
 */
public interface EsBlogDao extends ElasticsearchRepository<EsBlog, String> {
 
	/**
	 * 模糊查询(去重)
	 * @param title
	 * @param Summary
	 * @param content
	 * @param tags
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title, String Summary, String content, String tags, Pageable pageable);
	
	EsBlog findByBlogId(Long blogId);
}
