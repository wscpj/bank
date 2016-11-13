package com.bank.common.service;

import java.util.List;
import java.util.Map;

import com.bank.common.model.Organization;

public interface OrgService {

    List<Organization> searchOrganizations(Map<String, Object> map);

    Boolean addOrganization(Organization org);

    Boolean updateOrganization(Organization org);

    Boolean deleteOrgByIds(List<Integer> ids);

    Organization getOrgById(Integer id);

}
