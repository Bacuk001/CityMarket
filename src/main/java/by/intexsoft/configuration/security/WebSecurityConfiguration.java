package by.intexsoft.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class for configuring WebSecurity. In the class, the configuration method is
 * overridden, which in the HttpSecurity settings has permission and
 * prohibitions on using the components of the application. class extends
 * {@link WebSecurityConfigurerAdapter}.
 * 
 * @see {@link TokenFilter}, {@link HttpSecurity}.
 * 
 */
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	private TokenFilter tokenFilter;

	@Autowired
	WebSecurityConfiguration(TokenFilter tokenFilter) {
		this.tokenFilter = tokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/products/**", "/localisation/*", "/api/category/*", "/api/markets", "/api/user**",
						"/api/Application**")
				.permitAll().antMatchers("/api/order").hasRole("ADMIN").antMatchers("/api/orders")
				.access("hasRole('ADMIN')").and().csrf().disable()
				.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests().and();
	}
}
