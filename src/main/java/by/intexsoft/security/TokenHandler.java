package by.intexsoft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.User;
import by.intexsoft.service.impl.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Class that works with the token. contains methods that encode, decode tokens.
 * 
 * @see {@link Jwts}
 */
@Service
public class TokenHandler {
	private UserService userService;

	@Autowired
	public TokenHandler(UserService userService) {
		this.userService = userService;
	}

	private static final String SECRET = "APPLICATION";

	/**
	 * A method that translates a token into a string. accesses the user service and
	 * searches for the {@link User} and returns the user object.
	 * 
	 * @return {@link User}
	 */
	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
		User user = userService.findByName(username);
		return user;
	}

	/**
	 * A method that creates a token for the user.
	 */
	public String createTokenForUser(String userName) {
		String jwt = Jwts.builder().setSubject(userName).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		return jwt;
	}

}
