package com.github.haydend.discern.handler.field;

import java.util.Queue;

import com.github.haydend.discern.model.FieldInfo;

public abstract class FieldHandler<T> {

  protected abstract byte[] encodeInternal(T field, FieldInfo fieldInfo);

  @SuppressWarnings("unchecked")
  public byte[] encode(Object field, FieldInfo fieldInfo) {
    return encodeInternal((T) field, fieldInfo);
  }

  public abstract T decode(Queue<Byte> bytes, FieldInfo fieldInfo);

}
