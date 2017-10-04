package by.intexsoft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class for configuring WebSecurity. In the class, the configuration method is
 * overridden, which in the HttpSecurity settings has permission and
 * prohibitions on using the components of the application. Class extends
 * {@link WebSecurityConfigurerAdapter}.
 * 
 * @see {@link TokenFilter}, {@link HttpSecurity},
 *      {@link WebSecurityConfigurerAdapter}.
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
				.permitAll().antMatchers("/api/user/save", "/api/market/save", "/api/stock/save", "/api/orders**")
				.hasRole("ADMIN").antMatchers("/api/stock/sign").hasRole("MANAGER_SHOP")
				.antMatchers("/api/category/save", "/api/product/save/").hasRole("MANAGER_STOCK").and().csrf().disable()
				.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests().and();
	}
}
