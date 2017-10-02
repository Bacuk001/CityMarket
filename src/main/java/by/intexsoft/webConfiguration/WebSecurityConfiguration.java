package by.intexsoft.webConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import by.intexsoft.security.TokenFilter;

/**
 * Class for configuring WebSecurity. In the class, the configuration method is
 * overridden, which in the HttpSecurity settings has permission and
 * prohibitions on using the components of the application. class extends
 * {@link WebSecurityConfigurerAdapter}.
 * 
 * @see {@link TokenFilter}, {@link HttpSecurity}.
 * 
 */
@Configuration
@EnableWebSecurity
@ComponentScan("by.intexsoft.security")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private TokenFilter tokenFilter;

	@Autowired
	public WebSecurityConfiguration(TokenFilter tokenFilter) {
		this.tokenFilter = tokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/products/**", "/localisation/*", "/api/category/*", "/api/markets", "/api/user**",
						"/api/Application**")
				.permitAll().antMatchers("/user/save", "/market/save", "/stock/save").hasRole("ADMIN")
				.antMatchers("/stock/sign").hasRole("MANAGER_SHOP").antMatchers("/category/save", "/product/save/")
				.hasRole("MANAGER_STOCK").antMatchers("/api/orders").access("hasRole('ADMIN')").and().csrf().disable()
				.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests().and();
	}
}
