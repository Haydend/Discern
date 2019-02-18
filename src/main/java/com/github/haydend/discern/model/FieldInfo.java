package com.github.haydend.discern.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;

import com.github.haydend.discern.handler.field.FieldHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldInfo {

  private Optional<Class<FieldHandler<?>>> handler;

  private Field originalField;
  private String name;
  private Class<?> clazz;
  private boolean signed;

  private Optional<Integer> fixedLength;
  private boolean prependFieldLength;

  private Optional<Integer> order;

  private Annotation[] originalAnnoations;

  public int getOrder() {
    return order.orElse(Integer.MAX_VALUE);
  }

}
