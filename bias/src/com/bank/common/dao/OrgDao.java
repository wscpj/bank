package com.bank.common.dao;

import java.util.List;
import java.util.Map;

import com.bank.common.base.IBaseDao;
import com.bank.common.model.Organization;


public interface OrgDao extends IBaseDao<Organization, Integer> {

    List<Organization> searchOrganizations(Map<String, Object> map);

    Boolean deleteOrgByIds(List<Integer> ids);

}
