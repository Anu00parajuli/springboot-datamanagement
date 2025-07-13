package com.eon.springbootdatamanagement.validator;

import java.util.Map;

public class AbstractValidator {
    protected void validateMandatory(String name, Object value, Map<String, String> errors) {

        if(value == null){
            errors.put(name, name+ " is mandatory");
        }
    }

    protected void validateLength(String name, Object value, int length, Map<String, String> errors) {

        if(value != null && ((String)value).length() > length){
            errors.put(name, name + " value length exceeds -"+ length);
        }
    }

    protected void validateNumberLength(String name, Double value, int length, Map<String, String> errors) {

        if (value != null) {
            String number = String.valueOf(value);
            if(number.length() > length) {
                errors.put(name, name + " value length exceeds -" + length);
            }
        }
    }
    protected void validateNumberLength(String name, Long value, int length, Map<String, String> errors) {

        if (value != null) {
            String number = String.valueOf(value);
            if(number.length() > length) {
                errors.put(name, name + " value length exceeds -" + length);
            }
        }
    }
    protected void validateNumberLength(String name, Integer value, int length, Map<String, String> errors) {

        if (value != null) {
            String number = String.valueOf(value);
            if(number.length() > length) {
                errors.put(name, name + " value length exceeds -" + length);
            }
        }
    }
}
