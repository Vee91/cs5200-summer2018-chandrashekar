package edu.northeastern.cs5200.controllers;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.dto.Person;
import edu.northeastern.cs5200.service.RegisterService;
import edu.northeastern.cs5200.util.ResponseResource;

@RestController
@RequestMapping("/api")
public class RegistrationController {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	RegisterService registerService;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authManager;

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseResource register(@RequestBody Person person) {
		return registerService.register(person);
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ResponseResource login(@RequestBody Map<String, String> dataMap, HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResource out = new ResponseResource();
		try {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
					dataMap.get("username"), dataMap.get("password"));
			Authentication auth = authManager.authenticate(authReq);

			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);

			HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT_KEY", sc);
			out.setCode("200");
		} catch (InternalAuthenticationServiceException ex) {
			out.setCode("400");
			out.setMessage("Authentication failed. Please try again");
		}
		return out;
	}
}
