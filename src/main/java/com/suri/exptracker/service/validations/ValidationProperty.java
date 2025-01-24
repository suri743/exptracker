package com.suri.exptracker.service.validations;

import com.suri.exptracker.entity.constant.EntityName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValidationProperty {
  EntityName[] entityName();

  ValidationOperation[] operation();
}
