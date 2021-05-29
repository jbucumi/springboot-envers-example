/**
 * 
 */
package tech.inkware.springenvers.security.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import tech.inkware.springenvers.core.CoreEntity;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Getter
@Setter
@Entity
@Table(name = "crm_roles")
public class CrmRole extends CoreEntity<String> {

	private static final long serialVersionUID = 1L;

	@Column(name = "role_name")
	@NotBlank
	private String roleName;
	@Column(name = "role_description")
	private String roleDescription;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "crm_users_accesses", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	@Setter
	@NotNull
	private Collection<CrmPrivilege> privileges;

	public CrmRole(String name) {
		this.roleName = name;
	}

	public CrmRole() {
		super();
	}
}
