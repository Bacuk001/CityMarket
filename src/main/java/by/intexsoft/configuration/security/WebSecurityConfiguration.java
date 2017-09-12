package by.intexsoft.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	TokenFilter tokenFilter;

	@Autowired
	WebSecurityConfiguration(TokenFilter tokenFilter) {
		this.tokenFilter = tokenFilter;
	}

	/*
	 * Configure security for specific HTTP requests. Added filter for encoding
	 * information.
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/products/**", "/localisation/*", "/api/bank/category/**").permitAll()
				.antMatchers("/api/users").hasRole("ADMIN").antMatchers("/api/markets").access("hasRole('ADMIN')").and()
				.formLogin().and().csrf().disable()
				.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class).authorizeRequests().and();
	}
}
