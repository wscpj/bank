package com.bank.common.dao;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Account;

public interface AccountDao {
    public List<Account> findAllAccountByParams(Map<String, Object> map);

    public Integer getCount(Map<String, Object> map);

    public Boolean add(Account accout);

    public Account getById(Integer id);

    public void deleteAccountByIds(List<Integer> ids);
}
