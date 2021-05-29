/**
 * 
 */
package tech.inkware.springenvers.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import tech.inkware.springenvers.core.CoreEntity;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Getter
@Setter
@Entity
@Table(name = "crm_privileges")
public class CrmPrivilege extends CoreEntity<String> {

	private static final long serialVersionUID = 1L;

	@Column(name = "privilege_name", updatable = false, unique = true)
	private String privilegeName;

	@Column(name = "privilege_description")
	private String privilegeDescription;;

	public CrmPrivilege(String name) {
		this.privilegeName = name;
	}

	public CrmPrivilege() {
		super();
	}

}
