/**
 * 
 */
package tech.inkware.springenvers.core;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

//@formatter:off
/**
 * @author jeanclaude.bucumi 
 * jeanclaude.bucumi@outlook.com
 */ 
// @formatter:on

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class CoreEntity<U> implements Serializable {

	private static final long serialVersionUID = 1L;

	public CoreEntity() {
		super();
		id = UUID.randomUUID().toString().replace("-", "");
		dataStatus = DATA_STATUS.ACTIVE;
	}

	@Id
	@Column(name = "id", unique = true, insertable = true)
	private String id;

	@CreatedBy
	@Column(updatable = false)
	protected U CreatedBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	@Column(updatable = false)
	protected Date CreatedDate;

	@LastModifiedBy
	protected U LastModifiedBy;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date LastModifiedDate;
	
	@Column(name = "data_status")
	@Enumerated(EnumType.ORDINAL)
	private DATA_STATUS dataStatus;

}
