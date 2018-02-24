package edu.cc.sshe.framework.dao;

import java.io.Serializable;
import java.util.List;

import edu.cc.sshe.framework.bean.PageBean;

public interface IBaseDao {
	

		public void save(Object entity );
		
		public void update(Object entity);
		
		/**
		 * 根据主键删除
		 * @param ids
		 */
		public void deleteById(Class<?> clazz,Serializable...ids);
		
		/**
		 * 根据对象删除
		 * @param entities
		 */
		public void delete(Object...entities);
		
		/**
		 * 根据主键查询
		 * @param id
		 * @return
		 */
		public <T> T findById(Class<T> clazz,Serializable id);
		
		/**
		 * 根据属性查询
		 * @param pName
		 * @param pValue
		 * @return
		 */
		public <T> T findByProperty(Class<T> clazz,String pName,Object pValue);
		
		public <T> List<T> findListByProperty(Class<T> clazz,String pName,Object pValue);
		
		
		/**
		 * 多属性查询
		 * @param pName
		 * @param pValue
		 * @return
		 */
		public <T> T findByProperties(Class<T> clazz,String[] pNames,Object[] pValues);
		
		public <T> List<T> findListByProperties(Class<T> clazz,String[] pNames,Object[] pValues);
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
