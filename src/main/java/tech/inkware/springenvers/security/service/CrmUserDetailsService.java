/**
 * 
 */
package tech.inkware.springenvers.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.inkware.springenvers.security.dao.CrmUserDao;
import tech.inkware.springenvers.security.model.CrmPrivilege;
import tech.inkware.springenvers.security.model.CrmRole;
import tech.inkware.springenvers.security.model.CrmUser;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Service("userDetailsService")
public class CrmUserDetailsService implements UserDetailsService {

	@Autowired
	private CrmUserDao dao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		CrmUser u = dao.findByUsername(username);
	
		if (u != null) {
			if (u.isActive()) {
				return new User(u.getUsername(), u.getPassword(), getAuthorities(u.getRoles()));
			}
		}
		throw new UsernameNotFoundException(String.format("Unable to authenticate the user : %s\n", username));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<CrmRole> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<CrmRole> roles) {
		Collection<CrmPrivilege> collection = new ArrayList<>();

		for (CrmRole role : roles) {
			collection.addAll(role.getPrivileges());
		}
		return collection.stream().map(CrmPrivilege::getPrivilegeName).collect(Collectors.toList());
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		return privileges.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

}
