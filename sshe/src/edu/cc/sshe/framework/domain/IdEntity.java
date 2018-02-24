package edu.cc.sshe.framework.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 生成主键
 * @author song
 *
 */
@MappedSuperclass
public class IdEntity {
	
	@Id
	@Column(name="id",length=40)
	protected String id;
	
	public IdEntity(){
		this.id=UUID.randomUUID().toString().replace("-", "");
		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
