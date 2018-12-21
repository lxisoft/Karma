package com.lxisoft.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@EnableOAuth2Sso
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class OAuth2SsoConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* private final CorsFilter corsFilter; */

	/*
	 * public OAuth2SsoConfiguration(CorsFilter corsFilter) { this.corsFilter =
	 * corsFilter; }
	 */
	/*
	 * @Bean public AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler() { return
	 * new AjaxLogoutSuccessHandler(); }
	 */

	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring() .antMatchers(HttpMethod.OPTIONS, "/**")
	 * .antMatchers("/app/
	 **//**
		 * .{js,html}")
		 */ /*
			 * .antMatchers("/i18n/**") .antMatchers("/content/**")
			 * .antMatchers("/swagger-ui/index.html") .antMatchers("/test/**");
			 */
	/* } */

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http .csrf()
	 * .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	 * .and() .addFilterBefore(corsFilter, CsrfFilter.class) .headers()
	 * .frameOptions() .disable() .and() .logout() .logoutUrl("/api/logout")
	 * .logoutSuccessHandler(ajaxLogoutSuccessHandler()) .and()
	 * .authorizeRequests() .antMatchers("/api/**").authenticated()
	 * .antMatchers("/management/health").permitAll()
	 * .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
	 * .anyRequest().permitAll(); }
	 */

	/* *//**
			 * This OAuth2RestTemplate is used by
			 * org.springframework.cloud.security.oauth2.proxy.OAuth2TokenRelayFilter
			 * from Spring Cloud Security to refresh the access token when
			 * needed.
			 *//*
			 * @Bean public OAuth2RestTemplate
			 * oAuth2RestTemplate(OAuth2ProtectedResourceDetails
			 * oAuth2ProtectedResourceDetails, OAuth2ClientContext
			 * oAuth2ClientContext) { return new
			 * OAuth2RestTemplate(oAuth2ProtectedResourceDetails,
			 * oAuth2ClientContext); }
			 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77" + new BCryptPasswordEncoder().encode("adminpassword"));
		httpSecurity.authorizeRequests().antMatchers("/home/pending", "/helps/incompleted").hasAnyRole("ADMIN")
				.antMatchers("/").hasAnyRole("USER").anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll().and().logout().permitAll()// .anyRequest()
				// .permitAll()
				// .fullyAuthenticated()
				.and().formLogin();
		// httpSecurity.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77");
		// PasswordEncoder encoder =
		// PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("select id,password,activated from jhi_user where id=?")
				.authoritiesByUsernameQuery("select user_id,authority_name from jhi_user_authority where user_id=?");
		/*
		 * auth.inMemoryAuthentication().withUser("user").password(encoder.
		 * encode("password")).authorities("ROLE_USER")
		 * .and().withUser("admin").password(encoder.encode("password")).
		 * authorities("ROLE_ADMIN", "ROLE_USER");
		 */

	}
}
