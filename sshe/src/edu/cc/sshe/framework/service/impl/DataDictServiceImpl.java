package edu.cc.sshe.framework.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.cc.sshe.framework.bean.PageBean;
import edu.cc.sshe.framework.domain.DataDict;
import edu.cc.sshe.framework.service.BaseServiceSupport;
import edu.cc.sshe.framework.service.IDataDictService;

@Service
public class DataDictServiceImpl extends BaseServiceSupport<DataDict> implements IDataDictService {

	@SuppressWarnings("unchecked")
	@Override
	public List<DataDict> findDictTypes() {
		return (List<DataDict>) dao.findListByHql("from DataDict where pcode is null");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataDict> findByType(String pcode) {
		String hql = "from DataDict d where d.pcode=?";
		return (List<DataDict>) dao.findListByHql(hql, pcode);
	}

	
}
