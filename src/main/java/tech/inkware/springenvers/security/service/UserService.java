/**
 * 
 */
package tech.inkware.springenvers.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.inkware.springenvers.security.dao.CrmPrivilegeDao;
import tech.inkware.springenvers.security.dao.CrmRoleDao;
import tech.inkware.springenvers.security.dao.CrmUserDao;
import tech.inkware.springenvers.security.model.CrmPrivilege;
import tech.inkware.springenvers.security.model.CrmRole;
import tech.inkware.springenvers.security.model.CrmUser;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Service
public class UserService {
	@Autowired
	private CrmUserDao userDao;

	@Autowired
	private CrmRoleDao roleDao;

	@Autowired
	private CrmPrivilegeDao privilegeDao;

	public List<CrmUser> getAllUsers() {
		return userDao.findAll();
	}

	public CrmUser saveUser(CrmUser u) {
		return userDao.saveAndFlush(u);
	}

	public CrmUser getUserById(String id) {
		return userDao.getById(id);
	}

	public CrmUser getByUsername(String name) {
		return userDao.findByUsername(name);
	}

	public List<CrmRole> getAllRoles() {
		return roleDao.findAll();
	}

	public CrmRole saveRole(CrmRole r) {
		return roleDao.save(r);
	}

	public CrmRole getRoleById(String id) {
		return roleDao.getById(id);
	}

	public List<CrmPrivilege> getAllPrivileges() {
		return privilegeDao.findAll();
	}

	public CrmPrivilege savePrivilege(CrmPrivilege p) {
		return privilegeDao.save(p);
	}

	public CrmPrivilege getPrivilegeById(String uuid) {
		return privilegeDao.getById(uuid);
	}
}
