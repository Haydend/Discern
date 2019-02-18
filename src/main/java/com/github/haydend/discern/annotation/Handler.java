package com.github.haydend.discern.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.github.haydend.discern.handler.field.FieldHandler;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Handler {

  Class<FieldHandler<?>> value();

}
