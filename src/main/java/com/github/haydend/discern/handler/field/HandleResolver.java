package com.github.haydend.discern.handler.field;

import java.util.HashMap;
import java.util.Map;

import com.github.haydend.discern.model.FieldInfo;

public class HandleResolver {

  private final Map<Class<?>, FieldHandler<?>> DEFAULT_HANDLERS = new HashMap<>();

  public HandleResolver() {
    addHandler(Integer.class, new IntegerHandler());
    addHandler(String.class, new StringHandler());
  }

  public <T> void addHandler(Class<T> clazz, FieldHandler<T> handler) {
    DEFAULT_HANDLERS.put(clazz, handler);
  }

  public FieldHandler<?> getHandlerForField(FieldInfo fieldInfo) {
    return DEFAULT_HANDLERS.get(fieldInfo.getClazz());

  }


}
