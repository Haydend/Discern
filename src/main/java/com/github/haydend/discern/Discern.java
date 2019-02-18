package com.github.haydend.discern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.github.haydend.discern.handler.field.FieldHandler;
import com.github.haydend.discern.handler.field.HandleResolver;
import com.github.haydend.discern.model.FieldInfo;

public class Discern {

  private final FieldInfoFactory fieldInfoFactory = new FieldInfoFactory();
  private final HandleResolver handleResolver = new HandleResolver();

  public byte[] encode(Object model) {

    List<FieldInfo> fields = Arrays.asList(model.getClass().getDeclaredFields()).stream()
        .map(fieldInfoFactory::create)
        .sorted((f1, f2) -> Integer.compare(f1.getOrder(), f2.getOrder()))
        .collect(Collectors.toList());

    List<Byte> modelBytes = new ArrayList<Byte>();
    for (FieldInfo field : fields) {

      FieldHandler<?> fieldHandler = handleResolver.getHandlerForField(field);
      Object fieldValue = getValue(field, model);
      byte[] bytes = fieldHandler.encode(fieldValue, field);

      add(modelBytes, bytes);
    }

    Byte[] bytes = modelBytes.toArray(new Byte[modelBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private Object getValue(FieldInfo field, Object model) {
    try {
      field.getOriginalField().setAccessible(true);
      return field.getOriginalField().get(model);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private void add(List<Byte> bytes, byte[] toAdd) {
    bytes.addAll(Arrays.asList(ArrayUtils.toObject(toAdd)));
  }

}
