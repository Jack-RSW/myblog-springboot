package com.jack.myblog.service.impl;

import com.jack.myblog.dao.BlogDao;
import com.jack.myblog.pojo.*;
import com.jack.myblog.pojo.es.EsBlog;
import com.jack.myblog.service.BlogService;
import com.jack.myblog.service.EsBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Blog 服务.
 * 
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private EsBlogService esBlogService;

	/**
	 * 保存博客，通过esBlogService和blogDao
	 * @param blog
	 * @return
	 */
	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		boolean isNew = (blog.getId() == null);
		EsBlog esBlog = null;
		
		Blog returnBlog = blogDao.save(blog);
		
		if (isNew) {
			esBlog = new EsBlog(returnBlog);
		} else {
			esBlog = esBlogService.getEsBlogByBlogId(blog.getId());
			esBlog.update(returnBlog);
		}

		esBlogService.updateEsBlog(esBlog);
		return returnBlog;
	}

	/**
	 * 移除博客
	 * @param id
	 */
	@Transactional
	@Override
	public void removeBlog(Long id) {
		blogDao.delete(id);
		EsBlog esblog = esBlogService.getEsBlogByBlogId(id);
		esBlogService.removeEsBlog(esblog.getId());
	}

	/**
	 * 获取博客
	 * @param id
	 * @return
	 */
	@Override
	public Blog getBlogById(Long id) {
		return blogDao.findOne(id);
	}

	/**
	 * 根据用户名进行分页模糊查询（最新）
	 * @param user
	 * @param title
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		//Page<Blog> blogs = blogDao.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
		String tags = title;
		Page<Blog> blogs = blogDao.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title,user, tags,user, pageable);
		return blogs;
	}

	/**
	 * 根据用户名进行分页模糊查询（最热）
	 * @param user
	 * @param title
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		Page<Blog> blogs = blogDao.findByUserAndTitleLike(user, title, pageable);
		return blogs;
	}

	/**
	 * 根据分类进行查询
	 * @param catalog
	 * @param pageable
	 * @return
	 */
	@Override
	public Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable) {
		Page<Blog> blogs = blogDao.findByCatalog(catalog, pageable);
		return blogs;
	}

	/**
	 * 阅读量递增
	 * @param id
	 */
	@Override
	public void readingIncrease(Long id) {
		Blog blog = blogDao.findOne(id);
		blog.setReadSize(blog.getCommentSize()+1);
		this.saveBlog(blog);
	}

	/**
	 * 发表评论
	 * @param blogId
	 * @param commentContent
	 * @return
	 */
	@Override
	public Blog createComment(Long blogId, String commentContent) {
		Blog originalBlog = blogDao.findOne(blogId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Comment comment = new Comment(user, commentContent);
		originalBlog.addComment(comment);
		return this.saveBlog(originalBlog);
	}

	/**
	 * 删除评论
	 * @param blogId
	 * @param commentId
	 */
	@Override
	public void removeComment(Long blogId, Long commentId) {
		Blog originalBlog = blogDao.findOne(blogId);
		originalBlog.removeComment(commentId);
		this.saveBlog(originalBlog);
	}

	/**
	 * 点赞
	 * @param blogId
	 * @return
	 */
	@Override
	public Blog createVote(Long blogId) {
		Blog originalBlog = blogDao.findOne(blogId);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vote vote = new Vote(user);
		boolean isExist = originalBlog.addVote(vote);
		if (isExist) {
			throw new IllegalArgumentException("该用户已经点过赞了");
		}
		return this.saveBlog(originalBlog);
	}

	/**
	 * 取消点赞
	 * @param blogId
	 * @param voteId
	 */
	@Override
	public void removeVote(Long blogId, Long voteId) {
		Blog originalBlog = blogDao.findOne(blogId);
		originalBlog.removeVote(voteId);
		this.saveBlog(originalBlog);
	}
}
