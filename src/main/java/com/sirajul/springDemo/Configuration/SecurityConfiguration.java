package com.sirajul.springDemo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	/*
	 * @Bean public BCryptPasswordEncoder bCryptPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); } replace this code into MvcConfig this code
	 * work here also
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
				// .antMatchers("/register").permitAll()
				// .///antMatchers("/confirm")//.///permitAll();
				.antMatchers("/register").permitAll().antMatchers("/confirm").permitAll()
				.antMatchers("/registration**", "/forgot-password**", "/reset-password**").permitAll()
				.antMatchers("/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
				.antMatchers("/", "/home", "/about").permitAll().antMatchers("/admin/**").hasAnyRole("ADMIN")

				.antMatchers("/user/**").hasAnyRole("USER").antMatchers("/books/**").hasAnyRole("USER")

				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll();

	}

	// create two users, admin and user
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("admin")
				.password("password").roles("ADMIN");
    }
}

/*
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .authorizeRequests() .antMatchers("/resources/**",
 * "/registration").permitAll() .anyRequest().authenticated() .and()
 * .formLogin() .loginPage("/login") .permitAll() .and() .logout() .permitAll();
 * }
 */