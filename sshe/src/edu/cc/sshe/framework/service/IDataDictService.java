package edu.cc.sshe.framework.service;

import java.util.List;

import edu.cc.sshe.framework.domain.DataDict;

public interface IDataDictService extends IBaseService<DataDict>{

	/**
	 * 查询大类
	 * @return
	 */
	public List<DataDict> findDictTypes();
	
	/**
	 * 查询指定类型下的字典值
	 * @param pcode
	 * @return
	 */
	public List<DataDict>  findByType(String pcode);
}
