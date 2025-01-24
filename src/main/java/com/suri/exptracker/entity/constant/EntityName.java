package com.suri.exptracker.entity.constant;

import com.suri.exptracker.entity.Expense;
import com.suri.exptracker.entity.User;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum EntityName {
  USER(User.class.getName()),
  EXPENSE(Expense.class.getName());

  private final String className;
  private static final Map<String, EntityName> entityNameMap;

  EntityName(String className) {
    this.className = className;
  }

  static {
    Map<String, EntityName> map = new HashMap<>();
    for (EntityName entityName : EntityName.values()) {
      map.put(entityName.getClassName(), entityName);
    }
    entityNameMap = Collections.unmodifiableMap(map);
  }

  public static EntityName getEntityName(String className) {
    return entityNameMap.get(className);
  }
}
