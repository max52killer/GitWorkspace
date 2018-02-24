package edu.cc.sshe.framework.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.dao.IBaseDao;

@Transactional(readOnly=false)
public abstract class BaseServiceSupport<T> implements IBaseService<T>{

	
	@Autowired
	protected IBaseDao dao;
	
	public IBaseDao getDao() {
		
		return dao;
	}
	
	/**
	 * 获取T的class类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getTClass() {
	  ParameterizedType superType = (ParameterizedType)getClass().getGenericSuperclass();
	  Type[] actualTypes = superType.getActualTypeArguments();
	  
	  if(actualTypes == null || actualTypes.length == 0) {
		  throw new IllegalArgumentException(getClass().getName()+"没有泛型参数");
	  }
	  
	  return (Class<T>)actualTypes[0];
	}
	
	
	@Override
	public void save(T obj) {
		dao.save(obj);
	}

	@Override
	public void update(T obj) {
		dao.update(obj);
	}

	@Override
	public void delete(Object... objs) {
		dao.delete(objs);
	}

	@Override
	public void deleteById(Serializable... ids) {
		dao.deleteById(getTClass(), ids);
	}

	@Transactional(readOnly=true)
	@Override
	public T findById(Serializable id) {
		
		return dao.findById(getTClass(), id);
	}

	@Transactional(readOnly=true)
	@Override
	public T findByProperty(String pName, Object pValue) {
		
		return dao.findByProperty(getTClass(), pName, pValue);
	}

	@Transactional(readOnly=true)
	@Override
	public T findByProperties(String[] pNames, Object[] pValues) {
		
		return dao.findByProperties(getTClass(), pNames, pValues);
	}

	@Transactional(readOnly=true)
	public PageBean findPagedData(int page,int pageSize) {
		
		return dao.findPagedData("from " + getTClass().getSimpleName(), page, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String hql = "from " + getTClass().getSimpleName();
		return (List<T>) dao.findListByHql(hql);
	}
	
	
	
}
