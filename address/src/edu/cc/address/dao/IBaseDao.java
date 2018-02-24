package edu.cc.address.dao;

import java.io.Serializable;
import java.util.List;

import edu.cc.address.bean.PageBean;

public interface IBaseDao<T> {
	
public void save(T entity );
	
	public void update(T entity);
	
	/**
	 * 根据主键删除
	 * @param ids
	 */
	public void deleteById(Serializable...ids);
	
	/**
	 * 根据对象删除
	 * @param entities
	 */
	@SuppressWarnings("unchecked")
	public void delete(T...entities);
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	
	/**
	 * 根据属性查询
	 * @param pName
	 * @param pValue
	 * @return
	 */
	public T findByProperty(String pName,Object pValue);
	
	public List<T> findListByProperty(String pName,Object pValue);
	
	
	/**
	 * 多属性查询
	 * @param pName
	 * @param pValue
	 * @return
	 */
	public T findByProperties(String[] pNames,Object[] pValues);
	
	public List<T> findListByProperties(String[] pNames,Object[] pValues);
	/**
	 * HQL查询，返回对象
	 * @param hql
	 * @return
	 */
	public Object findByHql(String hql);
	
	public Object findByHql(String hql,Object...params);
	
	/**
	 * HQL查询，返回List
	 * @param hql
	 * @return
	 */
	public List<?> findListByHql(String hql);
	
	public List<?> findListByHql(String hql,Object...params);
	
	/**
	 * 分页查询
	 * @param hql
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageBean findPagedData(String hql,int page,int pageSize);
	public PageBean findPagedData(String hql,int page,int pageSize,Object...params);
	
	/**
	 * 执行delete,update风格的hql
	 * @param hql
	 * @return
	 */
	public int execute(String hql) ;
	public int execute(String hql,Object...params) ;

}
