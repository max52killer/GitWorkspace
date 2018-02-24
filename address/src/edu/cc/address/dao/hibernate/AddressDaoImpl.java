package edu.cc.address.dao.hibernate;

//import java.util.List;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate4.HibernateCallback;
//import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.cc.address.dao.IAddressDao;
import edu.cc.address.domain.Address;
/**
 * 注释的部分为未封装之前添加的内容，如果需要使用手动去掉注释，并取消继承
 * @author song
 *
 */
@Repository("addressDao")
public class AddressDaoImpl extends BaseDaoSupport<Address> implements IAddressDao {
//	private HibernateTemplate hibernateTemplate;
	
//	@Autowired
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		
//		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
//	}
//	
//	@Override
//	public void save(Address addr) {
//		hibernateTemplate.save(addr);
//		
//	}
//
//	@Override
//	public void update(Address addr) {
//		hibernateTemplate.update(addr);
//		
//	}
//
//	@Override
//	public void delete(Integer id) {
//		hibernateTemplate.delete(findById(id));
//		
//	}
//
//	@Override
//	public Address findById(Integer id) {
//		
//		return hibernateTemplate.get(Address.class, id);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Address> findByProperty(String propName, Object propValue) {
//		return (List<Address>) hibernateTemplate.find("from Address a where a." + propName + "=?", propValue);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Address> findByPage(final String username,final int page, final int pageSize) {
//		return hibernateTemplate.executeWithNativeSession(new HibernateCallback<List<Address>>() {
//			@Override
//			public List<Address> doInHibernate(Session session) throws HibernateException {
//			   String hql = "from Address a where a.user.username=?";
//			   Query query = session.createQuery(hql)
//					                .setParameter(0, username)
//					                .setFirstResult((page - 1) * pageSize)
//					                .setMaxResults(pageSize);
//			   return query.list();
//			}
//		});
//		
//	}
//
//	@Override
//	public int findCount(final String username) {
//	
//		return hibernateTemplate.executeWithNativeSession(new HibernateCallback<Integer>() {
//			@Override
//			public Integer doInHibernate(Session session) throws HibernateException {
//				Long count = (Long) session.createQuery("select count(*) from Address a where a.user.username=?").setParameter(0, username).uniqueResult();
//			    return count.intValue();
//			}
//		});
//	}

}
