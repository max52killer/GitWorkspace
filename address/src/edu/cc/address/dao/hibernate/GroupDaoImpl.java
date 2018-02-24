package edu.cc.address.dao.hibernate;

//import java.util.List;
//
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import edu.cc.address.dao.IGroupDao;
import edu.cc.address.domain.Group;

@Repository("groupDao")
public class GroupDaoImpl extends BaseDaoSupport<Group> implements IGroupDao {
//	private HibernateTemplate hibernateTemplate;
	
//	@Autowired
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		
//		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
//	}
//	
//	@Override
//	public void save(Group group) {
//		hibernateTemplate.save(group);
//	}
//
//	@Override
//	public void update(Group group) {
//		hibernateTemplate.update(group);
//	}
//
//	@Override
//	public void delete(Integer id) {
//		hibernateTemplate.delete(findById(id));
//	}
//
//	@Override
//	public Group findById(Integer id) {
//		return hibernateTemplate.get(Group.class, id);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Group> findByProperty(String propName, Object propValue) {
//		return (List<Group>) hibernateTemplate.find("from Group g where g." + propName + "=?", propValue);
//	}

}
