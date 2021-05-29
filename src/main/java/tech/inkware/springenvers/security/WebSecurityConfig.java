/**
 * 
 */
package tech.inkware.springenvers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import tech.inkware.springenvers.middleware.SessionLoginHandler;
import tech.inkware.springenvers.middleware.SessionLogoutHandler;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SessionLoginHandler loginHandler;

	@Autowired
	private SessionLogoutHandler logoutHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.sessionManagement().maximumSessions(2).expiredUrl("/login?sessionExpired=true");
		http.sessionManagement().sessionFixation().migrateSession();
		http.sessionManagement().invalidSessionUrl("/login");
		http.sessionManagement().sessionFixation();

		http.authorizeRequests().antMatchers("/login").permitAll().and().authorizeRequests().antMatchers("/assets/**")
				.permitAll().and().authorizeRequests().antMatchers("/webjars/**").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll()
				.successHandler(loginHandler).usernameParameter("username").passwordParameter("password").and().logout()
				.logoutSuccessUrl("/login").permitAll().logoutSuccessHandler(logoutHandler).and().csrf().disable();
	}

}
