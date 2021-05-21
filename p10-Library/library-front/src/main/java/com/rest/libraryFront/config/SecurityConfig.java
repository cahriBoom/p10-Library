package com.rest.libraryFront.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.rest.libraryFront.service.ClientService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final UserDetailsService userDetailsService;
	
	@Autowired
	public SecurityConfig(@Qualifier("clientService") UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(BCryptManagerUtil.passwordencoder());
	}
	 
	@Autowired
	ClientService clientService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf()
		 .disable()
		 .authenticationProvider(getProvider())
         .formLogin()
         .loginPage("/login")
         .defaultSuccessUrl("/accueil").failureUrl("/login?error=loginError")
         //.usernameParameter("username").passwordParameter("password")
         .and()
         .logout()
         .logoutUrl("/logout")
         .logoutSuccessUrl("/login")
         .invalidateHttpSession(true)
         .and()
         .authorizeRequests()
         .antMatchers("/search*/**").authenticated()
         .antMatchers("/welcome*/**").authenticated()
         .antMatchers("/description*/**").authenticated()
         .antMatchers("/emprunt*/**").authenticated()
         .antMatchers("/consultEmprunt*/**").authenticated()
         .anyRequest().permitAll();
	   }
		
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientService);
    }
	
	@Bean
    public AuthenticationProvider getProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(clientService);
        provider.setPasswordEncoder(BCryptManagerUtil.passwordencoder());
        return provider;
    }
}
