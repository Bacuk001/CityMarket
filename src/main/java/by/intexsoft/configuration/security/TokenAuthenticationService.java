package by.intexsoft.configuration.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.User;

@Service
public class TokenAuthenticationService {
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private TokenHandler tokenHand;

	@Autowired
	public TokenAuthenticationService(TokenHandler tokenHand) {
		this.tokenHand = tokenHand;
	}

	public void addAuthentication(HttpServletResponse response, String user) {
		response.addHeader("X-AUTH-TOKEN", tokenHand.createTokenForUser(user));
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final User user = tokenHand.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(user);
			}
		}
		return null;
	}

}
