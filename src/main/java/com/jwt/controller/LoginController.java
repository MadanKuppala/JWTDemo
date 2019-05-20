package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.config.JwtTokenUtil;
import com.jwt.model.ApiResponse;
import com.jwt.model.AuthToken;
import com.jwt.model.LoginUser;
import com.jwt.model.User;
import com.jwt.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;
	
	 @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	    public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
	        final User user = userService.findOne(loginUser.getUsername());
	        final String token = jwtTokenUtil.generateToken(user);
	        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername()));
	    }

}
