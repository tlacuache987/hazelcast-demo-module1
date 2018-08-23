package com.pluralsight.hazelcast.shared;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1298993717834293544L;

	@Id
	private Long id;

	private String name;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

}
