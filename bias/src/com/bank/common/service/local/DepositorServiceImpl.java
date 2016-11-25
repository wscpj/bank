package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import com.bank.common.base.BaseService;
import com.bank.common.dao.DepositorDao;
import com.bank.common.model.Depositor;
import com.bank.common.service.DepositorService;

public class DepositorServiceImpl extends BaseService
        implements DepositorService {

    private static final long serialVersionUID = 8714128483890562883L;

    private DepositorDao depositorDao;

    @Override
    public List<Depositor> findAllDepositorByParams(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return depositorDao.findAllDepositorByParams(map);
    }

    @Override
    public Boolean saveDepositor(Depositor depositor) {
        // TODO Auto-generated method stub
        return depositorDao.add(depositor);
    }

    @Override
    public Depositor findByDepositorId(Integer id) {
        return depositorDao.getById(id);
    }

    @Override
    public void deleteDepositorByIds(List<Integer> ids) {
    	depositorDao.deleteDepositorByIds(ids);
    }
    @Override
    public Boolean updateDepositor(Depositor depositor){
    	return depositorDao.update(depositor);
    }
    
    
	public DepositorDao getDepositorDao() {
		return depositorDao;
	}

	public void setDepositorDao(DepositorDao depositorDao) {
		this.depositorDao = depositorDao;
	}


}
