package by.intexsoft.configuration.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

/**
 * A filter that is added to the filter chain, which processes information about
 * user authentication in the system. The filter checks whether the user is
 * registered with the system or not. If not then registers. If the user is not
 * in the system, then he does not register the service in the system. Filter
 * added before {@link UsernamePasswordAuthenticationFilter}.
 * 
 * @see {@link GenericFilterBean}, {@link TokenAuthenticationService}
 */
@Service
public class TokenFilter extends GenericFilterBean {
	TokenAuthenticationService tokenAuthenticationService;

	@Autowired
	TokenFilter(TokenAuthenticationService tokenAuthenticationService) {
		this.tokenAuthenticationService = tokenAuthenticationService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;		
		Authentication authentication = tokenAuthenticationService.getAuthentication(httpRequest);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		SecurityContextHolder.getContext().setAuthentication(null);
	}
}
