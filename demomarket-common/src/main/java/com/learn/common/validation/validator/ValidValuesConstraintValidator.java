package com.learn.common.validation.validator;

import com.learn.common.validation.annotation.ValidValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 996Worker
 * @program demo-Mail-demo
 * @description
 * @create 2021-12-07 12:24
 */
public class ValidValuesConstraintValidator implements ConstraintValidator<ValidValues, Integer> {

    private Set<Integer> set;

    @Override
    public void initialize(ValidValues constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        int[] values = constraintAnnotation.values();

        set = new HashSet<>();
        for (int value : values) {
            set.add(value);
        }
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (set.contains(integer)) {
            return true;
        }

        return false;
    }
}