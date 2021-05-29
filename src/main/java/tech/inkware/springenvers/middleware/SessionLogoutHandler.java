/**
 * 
 */
package tech.inkware.springenvers.middleware;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Component
public class SessionLogoutHandler extends SimpleUrlLogoutSuccessHandler {
	static Logger logger = LoggerFactory.getLogger(SessionLogoutHandler.class);

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		// Update Session Logs

		logger.info("Logout successful ..........");
		super.onLogoutSuccess(request, response, authentication);
	}

}
