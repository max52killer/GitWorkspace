package edu.cc.address.service.impl;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cc.address.bean.PageBean;
import edu.cc.address.dao.IAddressDao;
import edu.cc.address.domain.Address;
import edu.cc.address.exception.ServiceException;
import edu.cc.address.service.IAddressService;

@Service("addressServivce")
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IAddressDao addrDao;
	
	@Override
	public void save(Address addr) {
		addrDao.save(addr);
	}

	@Override
	public void update(Address addr) {
		addrDao.update(addr);
	}

	@Override
	public void delete(String username, Integer id) {
		Address addr =  addrDao.findById(id);
		if(!addr.getUser().getUsername().equals(username)) {
			throw new ServiceException("只能删除自己的记录");
		}
		
//		addrDao.delete(id);
		addrDao.deleteById(id);
	}

	@Override
	public Address findById(Integer id) {
		
		return addrDao.findById(id);
	}

	@Override
	public PageBean queryPaged(String username, int page, int pageSize) {
//		PageBean pb = new PageBean();
//		pb.setPage(page);
//		pb.setPageSize(pageSize);
//		//查询当前页的数据
//		List<Address> rows = addrDao.findByPage(username, page, pageSize);
//		int total = addrDao.findCount(username);
//		
//		pb.setRows(rows);
//		pb.setTotal(total);
//		
//		return pb;
		return addrDao.findPagedData("from Address a where a.user.username=?", page, pageSize,username);
	}

}
