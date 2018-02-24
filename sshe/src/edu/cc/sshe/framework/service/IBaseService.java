package edu.cc.sshe.framework.service;

import java.io.Serializable;
import java.util.List;

import edu.cc.sshe.framework.bean.PageBean;

public interface IBaseService<T> {

	public void save(T obj);
	
	public void update(T obj);
	
	public void delete(Object...objs);
	
	public void deleteById(Serializable...ids);
	
	public T findById(Serializable id);
	
	public T findByProperty(String pName,Object pValue);
	
	public T findByProperties(String[] pNames,Object[] pValues);
	
	public List<T> findAll();
	
	public PageBean findPagedData(int page,int pageSize);
}
