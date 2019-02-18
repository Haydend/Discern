package com.github.haydend.discern;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Optional;

import com.github.haydend.discern.annotation.Handler;
import com.github.haydend.discern.annotation.Order;
import com.github.haydend.discern.annotation.PrependFieldLength;
import com.github.haydend.discern.annotation.Size;
import com.github.haydend.discern.annotation.Unsigned;
import com.github.haydend.discern.handler.field.FieldHandler;
import com.github.haydend.discern.model.FieldInfo;

public class FieldInfoFactory {

  public FieldInfo create(Field field) {

    Optional<Class<FieldHandler<?>>> handler = getAnnotation(field, Handler.class).map(a -> a.value());

    String name = field.getName();
    Class<?> clazz = field.getType();
    Annotation[] annotations = field.getAnnotations();

    boolean signed = !getAnnotation(field, Unsigned.class).isPresent();

    Optional<Integer> fixedLength = getAnnotation(field, Size.class).map(a -> a.value());
    boolean prependFieldLength = getAnnotation(field, PrependFieldLength.class).isPresent();

    Optional<Integer> order = getAnnotation(field, Order.class).map(a -> a.value());

    return new FieldInfo(handler, field, name, clazz, signed, fixedLength, prependFieldLength, order, annotations);

  }

  private <A extends Annotation> Optional<A> getAnnotation(Field field, Class<A> annotation) {
    return Optional.ofNullable(field.getAnnotation(annotation));
  }

}
