package com.github.haydend.discern.model;

import java.lang.reflect.Field;
import java.util.List;

import com.github.haydend.discern.handler.bit.BitFlagHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BitFlag {

  private Field originalField;
  private String name;
  private List<BitFlagEntry> bitLogic;

  @Getter
  @AllArgsConstructor
  public class BitFlagEntry {

    private FieldInfo field;
    private int position;
    private BitFlagHandler bitFlagHandler;

  }

}
