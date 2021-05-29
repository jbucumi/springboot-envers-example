/**
 * 
 */
package tech.inkware.springenvers.security.dao;

import org.springframework.stereotype.Repository;

import tech.inkware.springenvers.core.CoreRepository;
import tech.inkware.springenvers.security.model.CrmRole;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */
@Repository
public interface CrmRoleDao extends CoreRepository<CrmRole> {

	CrmRole findByRoleName(String r);

}
