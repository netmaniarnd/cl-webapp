package com.checklod.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	@Value("${spring.security.user.name}")
	private String username;
	@Value("${spring.security.user.password}")
	private String password;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**/", "/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// 페이지 권한 설정
				// .antMatchers("/admin/**").hasRole("ADMIN")
				// .antMatchers("/user/myinfo").hasRole("MEMBER")
				.antMatchers("/**").hasRole("VIEW").and() // 로그인 설정
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/front", true)
				.permitAll()
				.and().csrf().disable();
//    .and() // 로그아웃 설정
//        .logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//        .logoutSuccessUrl("/user/logout/result")
//        .invalidateHttpSession(true)
//    .and()
//        // 403 예외처리 핸들링
//        .exceptionHandling().accessDeniedPage("/user/denied");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());

		auth.inMemoryAuthentication()
			.withUser(username).password("{noop}"+password).roles("ADMIN", "VIEW");

	}
}
