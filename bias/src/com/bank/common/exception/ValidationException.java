package com.bank.common.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends Exception {
    private static final long serialVersionUID = -6637943825186416961L;

    private Map<String, String> fieldErrors = new HashMap<String, String>();

   public void addError(String field, String errorMessage) {
       fieldErrors.put(field, errorMessage);
   }

   public Map<String, String> getFieldErrors() {
       return fieldErrors;
   }

   public boolean isError() {
       return !fieldErrors.isEmpty();
   }
}
