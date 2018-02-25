package com.sirajul.springDemo.Configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
            .antMatchers("/register").permitAll()
            .antMatchers("/confirm").permitAll();
    }
}

/*
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .authorizeRequests() .antMatchers("/resources/**",
 * "/registration").permitAll() .anyRequest().authenticated() .and()
 * .formLogin() .loginPage("/login") .permitAll() .and() .logout() .permitAll();
 * }
 */