package edu.cc.sshe.framework.util;

import java.util.ArrayList;
import java.util.List;

public class HqlBuilder {

	private String hql;
	private String where ;
	private String orderBy;
	private List<Object> params = new ArrayList<Object>();
	
	public HqlBuilder(String hql) {
		
	   int wherePos = hql.toLowerCase().indexOf("where");
	   
	   if(wherePos != -1) {  //hql中有where
		   where = hql.substring(wherePos);
		   this.hql = hql.substring(0,wherePos); 
	   } else {
		   this.hql = hql;
		   this.where = " where '1'='1' ";
	   }
		
	}
	
	public HqlBuilder addCondition(String field,Object value ) {
		if(value == null) {
			return this;
		}
		
		if(value instanceof String) {
			String str = (String)value;
			if(str.trim().equals("")) {
				return this;
			}			
		}
		
		where += " and " + field ;
		params.add(value);
		
		return this;
	}
	
	
    public HqlBuilder addLikeCondition(String field,String value ) {
		if(value == null || "".equals(value)) {
			return this;
		}
    	
		where += " and " + field ;
		params.add("%" + value + "%");
		
		return this;
	}
	
    public HqlBuilder addOrder(String alias,String sort,String order ) {
	   if(sort == null || sort.equals("")) {
		   return this;
	   }
    
	   if(order == null) {
		   order = "";
	   }
    
	   if(orderBy == null) {
		   orderBy = " order by " + alias + "." + sort + " " + order;
	   } else {
		   orderBy += "," + alias + "." + sort + " " + order;
	   }
		
		return this;
	}
    
    
    public String builderHql() {
    	
    	return hql + where + (orderBy == null ?  "" : orderBy);
    }
    
    public Object[] getHqlParams() {
    	
    	return params.toArray();
    }
}
