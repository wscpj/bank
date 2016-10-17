package com.bank.common.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.ConvertUtils;

import com.bank.common.AppContext;
import com.bank.common.convert.DateConvert;
import com.bank.common.exception.ValidationException;
import com.bank.common.util.ObjectUtil;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;


public abstract class BaseService implements Serializable{
	
	private static final long serialVersionUID = -8779239453137243883L;
	
	private static final String OVAL_MESSAGE_BASE_NAME = "ovalMessage";
    private static final ResourceBundle RESOURCE_BUNDLE_ZH_CN = ResourceBundle.getBundle(
            OVAL_MESSAGE_BASE_NAME, Locale.CHINA);
    private static final ResourceBundle RESOURCE_BUNDLE_EN_US = ResourceBundle.getBundle(
            OVAL_MESSAGE_BASE_NAME, Locale.US);

    static {
        ConvertUtils.register(new DateConvert(), Date.class);
    }

    protected Object copyObject(Object srcObject,Class<?> clz) {
        return ObjectUtil.objectCopy(srcObject, clz);
    }

    protected Validator validator;


    public void setValidator(Validator validator) {
        this.validator = validator;
    }


    protected Boolean validateObject(Object o) throws ValidationException {
        ResourceBundleMessageResolver resolver = (ResourceBundleMessageResolver) Validator
                .getMessageResolver();
        ResourceBundle resourceBundle = getResourceBundle();
        resolver.addMessageBundle(resourceBundle);

        List<ConstraintViolation> violations = validator.validate(o);
        if (violations != null && !violations.isEmpty()) {
            ValidationException validationException = new ValidationException();
            for (ConstraintViolation violation : violations) {
                String ovalField = violation.getContext().toString();
                String fieldNameTemp = ovalField.substring(0, ovalField.lastIndexOf("."));
                Integer index = fieldNameTemp.lastIndexOf(".");
                String fieldName = ovalField.substring(index + 1, ovalField.length());
                String message = violation.getMessage();

                // get field display name
                fieldName = resourceBundle.getString(fieldName);
                message = message.replace(ovalField, fieldName);
                validationException.addError(fieldName, message);

            }
            if (validationException.isError()) {
                throw validationException;
            }
        }

        return Boolean.TRUE;
    }

    private ResourceBundle getResourceBundle() {
        Locale locale = AppContext.getContext().getLocale();
        if (locale == Locale.US) {
            return RESOURCE_BUNDLE_EN_US;
        } else {
            return RESOURCE_BUNDLE_ZH_CN;
        }
    }

}
