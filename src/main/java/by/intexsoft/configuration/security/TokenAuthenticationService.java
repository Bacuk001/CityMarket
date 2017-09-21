package by.intexsoft.configuration.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.User;

/**
 * The service is designed to work with authentication in the application.
 * Creates a token and puts it in response or retrieves the token from the
 * request and checks to see if it matches the data. If the token is a valid
 * service, the user registers to open access to the closed components.
 * 
 * @see @link {@link Authentication}, {@link HttpServletRequest},
 *      {@link TokenHandler}, {@link HttpServletResponse}
 */
@Service
public class TokenAuthenticationService {
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private TokenHandler tokenHand;

	@Autowired
	TokenAuthenticationService(TokenHandler tokenHand) {
		this.tokenHand = tokenHand;
	}

	/**
	 * A service method that places a registered user's token.
	 */
	public String addAuthentication(String userName) {
		return tokenHand.createTokenForUser(userName);
	}

	/**
	 * The service method checks whether there is a field in which to store the
	 * token and then checks for the validity of the token and returns the object
	 * for registration in the system.
	 * 
	 * @see {@link Authentication}
	 */
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
