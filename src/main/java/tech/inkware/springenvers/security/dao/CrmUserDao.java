/**
 * 
 */
package tech.inkware.springenvers.security.dao;

import org.springframework.stereotype.Repository;

import tech.inkware.springenvers.core.CoreRepository;
import tech.inkware.springenvers.security.model.CrmUser;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Repository
public interface CrmUserDao extends CoreRepository<CrmUser> {

	CrmUser findByUsername(String username);

}
