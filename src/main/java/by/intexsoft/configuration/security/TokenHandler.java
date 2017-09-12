package by.intexsoft.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.configuration.service.UserService;
import by.intexsoft.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenHandler {
	private UserService userService;

	@Autowired
	public TokenHandler(UserService userService) {
		this.userService = userService;
	}

	private static final String SECRET = "APPLICATION";

	public User parseUserFromToken(String token) {
		String username = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
		User user = userService.findByName(username);
		return user;
	}

	public String createTokenForUser(String userName) {
		String jwt = Jwts.builder().setSubject(userName).signWith(SignatureAlgorithm.HS512, SECRET).compact();
		return jwt;
	}

}
