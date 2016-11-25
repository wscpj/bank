package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Depositor;

public interface DepositorService {
	public List<Depositor> findAllDepositorByParams(Map<String, Object> map);

    public Boolean saveDepositor(Depositor depositor);

    public Depositor findByDepositorId(Integer id);

    public void deleteDepositorByIds(List<Integer> ids);
    
    public Boolean updateDepositor(Depositor depositor);
}
