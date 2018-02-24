package edu.cc.sshe.framework.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.cc.sshe.framework.bean.PageBean;

@Repository("baseDao")
public class HibernateBaseDaoSupport implements IBaseDao {

	protected HibernateTemplate ht;

	@Autowired//spring自动注入session
	public void setSessionFactory(SessionFactory sessionFactory) {

		this.ht = new HibernateTemplate(sessionFactory);
	}

	/**
	 * 生成count查询的hql
	 * 
	 * @param hql
	 * @return
	 */
	private static String getCountHql(String hql) {
		// hql = "FRom Employee e left join fetch e.dept where e.sex=? and
		// e.job=? and e.sal>? order by e.sal desc";
		// select count(*) from Employee e left join e.dept where e.sex=? and
		// e.job=? and e.sal>? "
		if (hql == null) {
			return null;
		}

		int fromPos = hql.toLowerCase().indexOf("from");
		if (fromPos == -1) {
			throw new IllegalArgumentException(hql + "不是一个有效的HQL查询语句");
		}

		String countHql = "select count(*) " + hql.substring(fromPos);

		// 处理order by
		int orderPos = countHql.toLowerCase().indexOf(" order ");

		if (orderPos != -1) {
			countHql = countHql.substring(0, orderPos);
		}

		// 处理fetch
		countHql = countHql.replaceAll("(?i)fetch", "");

		return countHql;
	}

	@Override
	public void save(Object entity) {
      ht.save(entity);
	}

	@Override
	public void update(Object entity) {
      ht.update(entity);
	}

	@Override
	public void deleteById(Class<?> clazz,Serializable... ids) {
		for(Serializable id : ids) {
			ht.delete(findById(clazz,id));
		}
		
	}

	@Override
	public void delete(Object... entities) {
		for(Object obj : entities) {
			ht.delete(obj);
		}
		
	}


	@Override
	public <T> T findById(Class<T> clazz, Serializable id) {
		return ht.get(clazz, id);
	}

	@Override
	public <T> T findByProperty(Class<T> clazz, String pName, Object pValue) {
		List<T> list = (List<T>) findListByProperty(clazz,pName,pValue);
		if(list.isEmpty()) {
			return null;
		} else if(list.size() > 1) {
			throw new RuntimeException("期望返回一条记录，实际返回" + list.size() + "条记录");
		} else {
			return list.get(0);
		}
		
		
	}

	/**
	 * 根据单个字段值查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByProperty(Class<T> clazz, String pName, Object pValue) {
		String hql = "from " + clazz.getSimpleName() + " o where o." + pName + "=?";
		return (List<T>) findListByHql(hql,pValue);
	}
	/**
	 * 根据多个字段属性置查询单个对象的值
	 */
	@Override
	public <T> T findByProperties(Class<T> clazz, String[] pNames, Object[] pValues) {
		List<T> list = (List<T>) findListByProperties(clazz,pNames,pValues);
		if(list.isEmpty()) {
			return null;
		} else if(list.size() > 1) {
			throw new RuntimeException("期望返回一条记录，实际返回" + list.size() + "条记录");
		} else {
			return list.get(0);
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListByProperties(Class<T> clazz, String[] pNames, Object[] pValues) {
		if(pNames.length != pValues.length) {
			throw new IllegalArgumentException("条件数量与参数数量不匹配!");
		}
		
		StringBuilder builder = new StringBuilder("from "); 
		builder.append(clazz.getSimpleName())
		       .append(" o ");
		
		boolean isFirst = true;
		for(String pName : pNames) {
			if(isFirst) {
				builder.append(" where ");
				isFirst = false;
			} else {
				builder.append(" and ");
			}
			
			builder.append(" o.").append(pName).append("=? ");
		}
		
		return (List<T>) findListByHql(builder.toString(), pValues);
	}

	@Override
	public Object findByHql(String hql) {
		
		return findByHql(hql,new Object[]{});
	}

	@Override
	public Object findByHql(String hql, Object... params) {
		List<?> list = ht.find(hql, params);
		
		if(list.isEmpty()) {
			return null;
		} else if(list.size() > 1) {
			throw new RuntimeException("期望返回一条记录，实际返回" + list.size() + "条记录");
		} else {
			return list.get(0);
		}
		
		
	}

	@Override
	public List<?> findListByHql(String hql) {
		
		return findListByHql(hql,new Object[]{});
	}

	@Override
	public List<?> findListByHql(String hql, Object... params) {
	
		return ht.find(hql, params);
	}

	@Override
	public PageBean findPagedData(final String hql, final int page, final int pageSize) {
		
		return findPagedData(hql,page,pageSize,new Object[]{});
	}

	@Override
	public PageBean findPagedData(final String hql, final int page, final int pageSize, final Object... params) {
		return ht.executeWithNativeSession(new HibernateCallback<PageBean>() {
			@Override
			public PageBean doInHibernate(Session session) throws HibernateException {
				//hql = "select e from Employee e left join fetch e.dept where e.sex=? and e.job=? and e.sal>? order by e.sal desc";
				//  select count(*) from Employee e left join  e.dept where e.sex=? and e.job=? and e.sal>? "
				PageBean pb = new PageBean();
				pb.setPage(page);
				pb.setPageSize(pageSize);
				//查询当前页的数据
				Query dataQuery = session.createQuery(hql);
				//绑定Query的参数
				if(params != null && params.length > 0) {
					for(int i = 0;i < params.length;i++) {
						dataQuery.setParameter(i, params[i]);
					}
				}
				dataQuery.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize);
				List<?> data = dataQuery.list();
				pb.setRows(data);
				//查询总数据量
				String countHql = getCountHql(hql);
				Query countQuery = session.createQuery(countHql);
				if(params != null && params.length > 0) {
					for(int i = 0;i < params.length;i++) {
						countQuery.setParameter(i, params[i]);
					}
				}
				
				int total = ((Long)countQuery.uniqueResult()).intValue();
				pb.setTotal(total);
				return pb;
			}
		});
	}

	@Override
	public int execute(String hql) {
		
		return execute(hql,new Object[]{});
	}

	@Override
	public int execute(String hql, Object... params) {
		
		return ht.bulkUpdate(hql, params);
	}

}
