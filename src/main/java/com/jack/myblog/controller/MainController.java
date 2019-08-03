package com.jack.myblog.controller;

import com.jack.myblog.pojo.Authority;
import com.jack.myblog.pojo.User;
import com.jack.myblog.result.Response;
import com.jack.myblog.service.AuthorityService;
import com.jack.myblog.service.UserService;
import com.jack.myblog.utils.ConstraintViolationExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主页控制器.
 *
 */
@Controller
public class MainController {

	private static final Long ROLE_USER_AUTHORITY_ID = 2L;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;
	
	@GetMapping("/")
	public String root() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "redirect:/blogs";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		model.addAttribute("errorMsg", "登陆失败，用户名或者密码错误！");
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	public String registerUser(User user, Model model) {
		List<Authority> authorities = new ArrayList<>();
		authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
		user.setAuthorities(authorities);
		user.setEncodePassword(user.getPassword());
		try {
			userService.saveUser(user);
		} catch (ConstraintViolationException e) {
			model.addAttribute("error", true);
			model.addAttribute("message", ConstraintViolationExceptionHandler.getMessage(e));
			return "register";
		}catch (Exception e){
			model.addAttribute("error", true);
			model.addAttribute("message", ConstraintViolationExceptionHandler.defaultErrorHandler(e));
			return "register";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/search")
	public String search() {
		return "search";
	}
}
