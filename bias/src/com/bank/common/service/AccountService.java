package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Account;

public interface AccountService {

    public List<Account> findAllAccountByParams(Map<String, Object> map);

}
