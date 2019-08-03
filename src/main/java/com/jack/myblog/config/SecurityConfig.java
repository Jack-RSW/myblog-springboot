package com.jack.myblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String KEY = "jack.com";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   // 使用 BCrypt 加密
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
        return authenticationProvider;
    }

    /**
     * 自定义配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // 都可以访问
                .antMatchers("/h2-console/**").permitAll() // 都可以访问
                .antMatchers("/admins/**").hasRole("ADMIN") // 需要相应的角色才能访问
                .and()
                .formLogin()   //基于 Form 表单登录验证
                .loginPage("/login").failureUrl("/login-error") // 自定义登录界面
                .and().rememberMe().key(KEY) // 启用 remember me
                .and().exceptionHandling().accessDeniedPage("/403");  // 处理异常，拒绝访问就重定向到 403 页面
        http.csrf().ignoringAntMatchers("/h2-console/**"); // 禁用 H2 控制台的 CSRF 防护
        http.headers().frameOptions().sameOrigin(); // 允许来自同一来源的H2 控制台的请求
    }

    /**
     * 认证信息管理
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    /*@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();

		http
				//使用form表单post方式进行登录
				.formLogin()
				//登录页面为自定义的登录页面
				.loginPage("/login")
				//设置登录成功跳转页面，error=true控制页面错误信息的展示
				.successForwardUrl("/index").failureUrl("/login?error=true")
				.permitAll()
				.and()
				//允许不登陆就可以访问的方法，多个用逗号分隔
				.authorizeRequests().antMatchers("/test").permitAll()
				//其他的需要授权后访问
				.anyRequest().authenticated();

		//session管理,失效后跳转
		http.sessionManagement().invalidSessionUrl("/login");
		//单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
		//http.sessionManagement().maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy());
		//单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
		//退出时情况cookies
		http.logout().deleteCookies("JESSIONID");
		//解决中文乱码问题
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8"); filter.setForceEncoding(true);
		//
		http.addFilterBefore(filter,CsrfFilter.class);
	}*/

	/*@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
		//返回错误信息提示，而不是Bad Credential
		bean.setHideUserNotFoundExceptions(true);
		//覆盖UserDetailsService类
		bean.setUserDetailsService(userDetailsService);
		//覆盖默认的密码验证类
		bean.setPasswordEncoder(passwordEncoder);
		return bean;
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.daoAuthenticationProvider());
	}*/


}
