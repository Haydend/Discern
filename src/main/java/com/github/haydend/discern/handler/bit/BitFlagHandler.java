package com.github.haydend.discern.handler.bit;

import com.github.haydend.discern.model.FieldInfo;

public interface BitFlagHandler {

  byte[] encode(FieldInfo field);

}
