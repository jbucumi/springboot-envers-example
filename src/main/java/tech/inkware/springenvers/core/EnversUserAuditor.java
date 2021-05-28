package tech.inkware.springenvers.core;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

//@formatter:off
/**
* @author jeanclaude.bucumi 
* jeanclaude.bucumi@outlook.com
*/ 
//@formatter:on

@Entity
@Table(name = "REVINFO")
@RevisionEntity(EnversUserAuditorListener.class)
public class EnversUserAuditor extends DefaultRevisionEntity {

	private static final long serialVersionUID = 1L;

	private String username;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
