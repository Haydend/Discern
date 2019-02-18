package com.github.haydend.discern;

import com.github.haydend.discern.annotation.BitFlag;
import com.github.haydend.discern.annotation.BitFlagLogic;
import com.github.haydend.discern.annotation.PrependFieldLength;
import com.github.haydend.discern.annotation.Unsigned;
import com.github.haydend.discern.handler.bit.SetIfNotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestModel {


  @BitFlag({
      @BitFlagLogic(positon = 0, logic = SetIfNotNull.class)

  })
  private byte bitFlag;

  @Unsigned
  private Integer fieldOne;

  @PrependFieldLength
  private Integer fieldTwo;

  @PrependFieldLength
  private String fieldThree;

  @Unsigned
  @PrependFieldLength
  private Integer fieldFour;

  private Integer fieldFive;

}
