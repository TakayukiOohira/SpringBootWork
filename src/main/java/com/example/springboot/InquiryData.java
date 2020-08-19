package com.example.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="inquirydata")
public class InquiryData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;

	@Column(length = 10, nullable = false)
	@NotEmpty
	@Size(min=1,max=10)
	private String name;

	@Column(length = 200, nullable = false)
	@NotEmpty
	@Email
	private String email;

	@Column(nullable = true)
	private String contents;

	public InquiryData() {};

	public InquiryData(long id, String name, String email, String contents) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contents = contents;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
}
