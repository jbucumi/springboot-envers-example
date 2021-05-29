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

import lombok.Getter;
import lombok.Setter;
import tech.inkware.springenvers.core.CoreEntity;

/**
 * @author jeanclaude.bucumi (jeanclaude.bucumi@outlook.com)
 */

@Getter
@Setter
@Entity
@Table(name = "crm_users")
public class CrmUser extends CoreEntity<String>{

	private static final long serialVersionUID = 1L;

	@Column(name = "user_email", unique = true)
	private String email;
	@Column(name = "user_mobile", unique = true)
	private String mobile;
	@Column(name = "user_full_name")
	private String fullName;
	@Column(name = "user_pseudo", updatable = false, unique = true)
	private String username;
	@Column(name = "user_password")
	private String password;
	private boolean active, locked, expired;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "crm_users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<CrmRole> roles;

	@Override
	public String toString() {
		return "CrmUser [email=" + email + ", mobile=" + mobile + ", fullName=" + fullName + ", username=" + username
				+ ", password=" + password + ", active=" + active + ", locked=" + locked + ", expired=" + expired + "]";
	}
	
}
