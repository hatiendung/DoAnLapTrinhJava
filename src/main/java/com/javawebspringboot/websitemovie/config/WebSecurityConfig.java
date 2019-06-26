package com.javawebspringboot.websitemovie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	
		
//		http.authorizeRequests().antMatchers("/login/**","/logout/**","/").permitAll();
//		http.authorizeRequests().antMatchers("/admin/**").authenticated();
//		http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN","USER");
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/download-movie/**").hasRole("USER")
			.antMatchers("/login/**","/**").permitAll()
			.anyRequest().authenticated()
			
			.and()
			
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/dang-nhap")
			.defaultSuccessUrl("/loginHandler")
			.failureUrl("/login?error=true")
			.usernameParameter("email")
			.passwordParameter("password");



		http.logout().logoutUrl("/dang-xuat").deleteCookies("JSESSIONID").logoutSuccessUrl("/"); 
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/web/**","/images/**");
	}

}
