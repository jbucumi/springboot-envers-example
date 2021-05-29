
package tech.inkware.springenvers.core;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//@formatter:off
/**
* @author jeanclaude.bucumi 
* jeanclaude.bucumi@outlook.com
*/ 
//@formatter:on

class EnversUserAuditorListener implements RevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		EnversUserAuditor customRevisionEntity = (EnversUserAuditor) revisionEntity;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			customRevisionEntity.setUsername("ANONYMOUS");
		}
		customRevisionEntity.setUsername(authentication.getName());
	}
}
