package com.checklog.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// 프로퍼티에 했던 보안 설정을 인메모리에
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}

	// 인증 및 인가절차 무시
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	// Spring Security Filter Chain 접근하여 url 인증 및 인가 처리
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/reports", "/reports/**").access("hasRole('ROLE_VIEW')")
			.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll()
			.usernameParameter("username").passwordParameter("password")
		.and()
			.logout().permitAll()
		.and()
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());	
			// 사이트 요청 간 위조방지
			// post 요청 시 반드시 _csrftoken 값을 넣어야 함
	}
}
