package tech.inkware.springenvers.security.service;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.RequiredArgsConstructor;
import tech.inkware.springenvers.security.dao.CrmRoleDao;
import tech.inkware.springenvers.security.model.CrmPrivilege;
import tech.inkware.springenvers.security.model.CrmRole;
import tech.inkware.springenvers.security.model.CrmUser;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */
@Service
@RequiredArgsConstructor
public class SecurityService {
	private final CrmRoleDao roleRepository;
	@Autowired
	private UserService userService;

	public boolean hasPrivilege(String... privileges) {
		boolean allowed = false;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		final HttpSession session = attr.getRequest().getSession(false);
		if (auth != null && auth.isAuthenticated() && session != null) {
			@SuppressWarnings("unchecked")
			final Collection<CrmRole> currentRole = (Collection<CrmRole>) session.getAttribute("current_user_roles");

			if (currentRole != null) {
				Iterator<CrmRole> it = currentRole.iterator();
				while (it.hasNext()) {
					String r = it.next().getRoleName();
					final CrmRole role = roleRepository.findByRoleName(r);
					for (String pr : privileges) {
						allowed = role.getPrivileges().stream().map(CrmPrivilege::getPrivilegeName)
								.anyMatch(p -> p.equals(pr));
					}
				}
			}
		}
		return allowed;
	}

	public boolean hasRole(String... ros) {
		boolean allowed = false;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		final HttpSession session = attr.getRequest().getSession(false);
		if (auth != null && auth.isAuthenticated() && session != null) {
			CrmUser currentUser = getCurrentUser(session);
			if (currentUser != null) {
				Collection<CrmRole> roles = currentUser.getRoles();
				if (!roles.isEmpty()) {
					for (String role : ros) {
						if (!role.startsWith("ROLE_")) {
							allowed = roles.stream().map(CrmRole::getRoleName).anyMatch(r -> r.equals("ROLE_" + role));
						} else {
							allowed = roles.stream().map(CrmRole::getRoleName).anyMatch(r -> r.equals(role));
						}

						if (allowed == true) {
							break;
						}
					}
				}
			}
		}
		return allowed;
	}

	public CrmUser getCurrentUser(HttpSession session) {

		if (session != null) {
			CrmUser sessionUser = (CrmUser) session.getAttribute("current_user");
			if (sessionUser != null) {
				CrmUser currentUser = userService.getUserById(sessionUser.getId());
				return (currentUser != null) ? currentUser : null;
			}
		}

		return null;

	}

	public Boolean isAuthenticated() {
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		final HttpSession session = attr.getRequest().getSession(false);
		return getCurrentUser(session) != null;
	}
}
