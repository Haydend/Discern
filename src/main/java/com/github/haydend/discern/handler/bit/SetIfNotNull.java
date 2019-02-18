package com.github.haydend.discern.handler.bit;

import java.util.Objects;

import com.github.haydend.discern.model.FieldInfo;

public class SetIfNotNull implements BitFlagHandler {

  @Override
  public byte[] encode(FieldInfo field) {

    if (Objects.isNull(field)) {
      return new byte[] {0x00};
    } else {
      return new byte[] {0x01};
    }
  }

}
