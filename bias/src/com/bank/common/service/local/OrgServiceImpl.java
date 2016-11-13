package com.bank.common.service.local;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bank.common.base.BaseService;
import com.bank.common.dao.OrgDao;
import com.bank.common.model.Organization;
import com.bank.common.service.OrgService;
import com.bank.common.util.EmailUtil;

public class OrgServiceImpl extends BaseService implements OrgService {

    private static final long serialVersionUID = 7440921737614461773L;

    private final Logger logger = Logger.getLogger(OrgServiceImpl.class);
    private OrgDao orgDao;

    public void setOrgDao(OrgDao orgDao) {
        this.orgDao = orgDao;
    }

    @Override
    public List<Organization> searchOrganizations(Map<String, Object> map) {
        logger.info("searchOrganizations paramters:{}");
        List<Organization> result = null;
        try {
            result = orgDao.searchOrganizations(map);
        } catch (Exception e) {
            EmailUtil util = new EmailUtil();
            util.sendMailExt("searchOrganizations error " + e.getMessage());
            logger.error("searchOrganizations error " + e);
        }
        return result;
    }

    @Override
    public Boolean addOrganization(Organization org) {
        logger.info("addOrganization organization:{}" + org.toString());
        Boolean result = Boolean.FALSE;
        try {
            result = orgDao.add(org);
        } catch (Exception e) {
            EmailUtil util = new EmailUtil();
            util.sendMail(e.getMessage());
            logger.error("addOrgainzation error " + e);
        }
        return result;
    }

    @Override
    public Boolean updateOrganization(Organization org) {
        logger.info("updateOrganization organization:{}" + org.toString());
        Boolean result = Boolean.FALSE;
        try {
            result = orgDao.update(org);
        } catch (Exception e) {
            EmailUtil util = new EmailUtil();
            util.sendMail(e.getMessage());
            logger.error("updateOrgainzation error " + e);
        }
        return result;
    }

    @Override
    public Boolean deleteOrgByIds(List<Integer> ids) {
        logger.info("deleteOrgByIds ids:{}" + ids.toString());
        Boolean result = Boolean.FALSE;
        try {
            result = orgDao.deleteOrgByIds(ids);
        } catch (Exception e) {
            EmailUtil util = new EmailUtil();
            util.sendMail(e.getMessage());
            logger.error("deleteOrgByIds error " + e);
        }
        return result;
    }

    @Override
    public Organization getOrgById(Integer id) {
        logger.info("getOrgById ids:{}" + id.toString());
        Organization result = null;
        try {
            result = orgDao.getById(id);
        } catch (Exception e) {
            EmailUtil util = new EmailUtil();
            util.sendMail(e.getMessage());
            logger.error("getOrgById error " + e);
        }
        return result;
    }

}
