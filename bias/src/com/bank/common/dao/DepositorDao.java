package com.bank.common.dao;

import java.util.List;
import java.util.Map;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.Depositor;

public interface DepositorDao extends IBaseDao<Depositor, Integer> {
	
	public List<Depositor> findAllDepositorByParams(Map<String, Object> map);

    public Integer getCount(Map<String, Object> map);

    public Depositor getById(Integer id);

    public void deleteDepositorByIds(List<Integer> ids);
    
}
