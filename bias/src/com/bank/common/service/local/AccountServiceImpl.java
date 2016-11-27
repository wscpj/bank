package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import com.bank.common.base.BaseService;
import com.bank.common.dao.AccountDao;
import com.bank.common.model.Account;
import com.bank.common.service.AccountService;

public class AccountServiceImpl extends BaseService implements AccountService {

    private static final long serialVersionUID = 8714128483890562883L;

    private AccountDao accountDao;

    @Override
    public List<Account> findAllAccountByParams(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return accountDao.findAllAccountByParams(map);
    }

    @Override
    public Boolean saveAccount(Account accout) {
        // TODO Auto-generated method stub
        return accountDao.add(accout);
    }

    @Override
    public Account findByAccountId(Integer id) {
        return accountDao.getById(id);
    }

    @Override
    public void deleteAccountByIds(List<Integer> ids) {
        accountDao.deleteAccountByIds(ids);
    }

    @Override
    public Boolean updateAcount(Account account) {
        // TODO Auto-generated method stub
        return accountDao.update(account);
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

}
