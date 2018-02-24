package edu.cc.address.service;

import edu.cc.address.bean.PageBean;
import edu.cc.address.domain.Address;

public interface IAddressService {

	public void save(Address addr);
	
	public void update(Address addr);
	
	public void delete(String username,Integer id);
	
	public Address findById(Integer id);
	
	public PageBean queryPaged(String username,int page,int pageSize);
	
	
}
