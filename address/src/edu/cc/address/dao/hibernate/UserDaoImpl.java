package edu.cc.address.dao.hibernate;

//import java.util.List;

//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.cc.address.dao.IUserDao;
import edu.cc.address.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoSupport<User> implements IUserDao {

//	private HibernateTemplate hibernateTemplate;
//	
//	@Autowired
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		
//		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
//	}
//	
//	
//	@Override
//	public void save(User user) {
//		
//	//	Session session = sessionFactory.openSession();
//	//	session.beginTransaction();
//	//	Session session = sessionFactory.getCurrentSession(); //获取当前事务中的Session
//	//	session.save(user);  
//	//	session.getTransaction().commit();
//	//	session.close();
//		hibernateTemplate.save(user);
//	}
//
//	@Override
//	public void update(User user) {
//		hibernateTemplate.update(user);
//	}
//
//	@Override
//	public User findById(Integer id) {
//		return hibernateTemplate.get(User.class, id);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<User> findAll() {
//		return (List<User>) hibernateTemplate.find("from User", new Object[]{});
//	}
//
//	@Override
//	public void delete(Integer id) {
//		hibernateTemplate.delete(findById(id));
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public User findByUsername(String username) {
//		List<User> users = (List<User>) hibernateTemplate.find("from User where username=?", username);
//		return users.size() > 0 ? users.get(0) : null;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public User findByNameAndPassword(String username, String password) {
//		List<User> users = (List<User>) hibernateTemplate.find("from User where username=? and password=?", username,password);
//		return users.size() > 0 ? users.get(0) : null;
//	}

}
