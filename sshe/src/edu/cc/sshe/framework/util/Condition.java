package edu.cc.sshe.framework.util;

public class Condition {

	// jobno like %123%
	private String fieldName;
	private String opterator;
	private Object value;
	
	public static final String OPT_LIKE = "like";

	public Condition(String fieldName, String opterator, Object value) {
		super();
		this.fieldName = fieldName;
		this.opterator = opterator;
		this.value = value;
	}

	public static void main(String[] args) {
		new Condition("jobno",Condition.OPT_LIKE,"%00%");
	}
}
