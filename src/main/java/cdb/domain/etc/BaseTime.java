package cdb.domain.etc;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseTime {

	@CreatedDate
	@Column(columnDefinition = "TimeStamp(6) null default null", updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(columnDefinition = "TimeStamp(6) null default null")
	private LocalDateTime updatedDate;

}
