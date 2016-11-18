package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Account;

public interface AccountService {

    public List<Account> findAllAccountByParams(Map<String, Object> map);

    public Boolean saveAccount(Account accout);

    public Account findByAccountId(Integer id);

    public void deleteAccountByIds(List<Integer> ids);

}
