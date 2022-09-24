package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * This class allows to configure security of the application namely the
 * authentication and the authorization
 * 
 * @author Rémi Deronzier
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * @param Exceptionhttp.authorizeRequests().and().formLogin().permitAll().loginProcessingUrl("/doLogin").defaultSuccessUrl("/"
	 * @param AntPathRequestMatcher("/app-logout"
	 * @param "POST")).clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID"
	 * @param authProvider(
	 * @throws Exceptionhttp.authorizeRequests().and().formLogin().permitAll().loginProcessingUrl("/doLogin").defaultSuccessUrl("/"
	 * @throws true).and().oauth2Login().and().logout().permitAll().logoutRequestMatcher(new                                                                                                                                                                                                   AntPathRequestMatcher("/app-logout"
	 * @throws "POST")).clearAuthentication(true).invalidateHttpSession(true).deleteCookies("JSESSIONID"
	 * @throws "sticky-cookie").and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry()).and().sessionFixation().migrateSession().and().rememberMe().userDetailsService(userDetailsService).rememberMeParameter("sticky").rememberMeCookieName("sticky-cookie");}public DaoAuthenticationProvider
	 *                                                                                                                                                                                                                                                                                         authProvider()
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {// @formatter:off
		http
				.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers(
						"/login*")
				.permitAll()
				.anyRequest().authenticated()

				.and()
				.formLogin().permitAll().loginProcessingUrl("/doLogin")
				.defaultSuccessUrl("/", true)
				
				.and().oauth2Login()
				
				.and()
				.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/app-logout", "POST"))
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "sticky-cookie")

				.and().sessionManagement().maximumSessions(1)
				.sessionRegistry(sessionRegistry()).and().sessionFixation()
				.migrateSession()

				.and()
				.rememberMe()
				.userDetailsService(userDetailsService)
				.rememberMeParameter("sticky")
				.rememberMeCookieName("sticky-cookie");
	} // @formatter:on

	/**
	 * @return DaoAuthenticationProvider
	 */
	@Bean
	public DaoAuthenticationProvider authProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/**
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	/**
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * @return SessionRegistry
	 */
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

}
