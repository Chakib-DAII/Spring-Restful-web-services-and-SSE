package com.DaiiChakib.Restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//static filtering
//@JsonIgnoreProperties(value={"field1","field2"})
//dynamic filter
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	private String field2;
	//@JsonIgnore
	private String field3;
	
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getfield1() {
		return field1;
	}

	public void setfield1(String field1) {
		this.field1 = field1;
	}

	public String getfield2() {
		return field2;
	}

	public void setfield2(String field2) {
		this.field2 = field2;
	}

	public String getfield3() {
		return field3;
	}

	public void setfield3(String field3) {
		this.field3 = field3;
	}
	
	
	
	
	
}
