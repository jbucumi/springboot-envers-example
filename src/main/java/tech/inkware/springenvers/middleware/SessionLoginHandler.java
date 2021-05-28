/**
 * 
 */
package tech.inkware.springenvers.middleware;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import tech.inkware.springenvers.security.model.CrmUser;
import tech.inkware.springenvers.security.service.UserService;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Component
public class SessionLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	static Logger logger = LoggerFactory.getLogger(SessionLoginHandler.class);
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
			
		response.sendRedirect(request.getContextPath());
		
		HttpSession session = request.getSession(true);
		CrmUser crmUser = userService.getByUsername(authentication.getName());
		session.setAttribute("current_user", crmUser);
		
		logger.info("Authentication successful!");
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException ae) throws IOException, ServletException {
		logger.error("An error occured during authentication!", ae);
		ae.printStackTrace();
	}

}
