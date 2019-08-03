package com.jack.myblog.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 *
 */
@RestController
@ControllerAdvice
public class ConstraintViolationExceptionHandler {

	/**
	 * 获取批量异常信息
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = ConstraintViolationException.class)
	public static String getMessage(ConstraintViolationException e) {
		List<String> msgList = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			msgList.add(constraintViolation.getMessage());
        }
		String messages = StringUtils.join(msgList.toArray(), ";");
		System.out.println(messages);
		return messages;
	}

	@ExceptionHandler(value = Exception.class)
	public static String defaultErrorHandler(Exception e){
		Class constraintViolationException = null;
		try {
			constraintViolationException = Class.forName("org.hibernate.exception.ConstraintViolationException");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if(null!=e.getCause()  && constraintViolationException==e.getCause().getClass()) {
			return "违反了约束，多半是外键约束";
		}
		return e.getMessage();
	}

}
