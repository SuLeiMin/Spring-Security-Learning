package net.codejava;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		  auth.inMemoryAuthentication()
	            .passwordEncoder(new BCryptPasswordEncoder())
	                .withUser("namhm")
	                .password("$2a$10$i6/i9yZvlSZAx/7PjVy6reA7FRt9K4CwHNO4uLswV53VOOpnwXSKW")
	                .roles("USER")
	            .and()
	                .withUser("admin")
	                .password("$2a$10$Nb4u6exPm3Ne.ieg6r81N.Liz/6SXL4s8Uv0GNS3FMSga07gxX23C")
	                .roles("ADMIN");
		 
	  }
	 
		protected void configure(HttpSecurity http) throws Exception {
			 http.authorizeRequests()
			    .antMatchers("/").permitAll()//any page will allow in browser
			    .antMatchers("/new").hasAnyRole("USER","ADMIN")
			    .antMatchers("/edit/*","/delete/*").hasRole("ADMIN")
		        .anyRequest().authenticated()
		        .and()
		        .httpBasic()
			 	.and()
			 	.exceptionHandling().accessDeniedPage("/403");
	    }  
		

}
