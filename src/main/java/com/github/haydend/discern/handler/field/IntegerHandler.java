package com.github.haydend.discern.handler.field;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Queue;

import com.github.haydend.discern.model.FieldInfo;
import com.github.haydend.discern.util.ByteUtils;

public class IntegerHandler extends FieldHandler<Integer> {

  @Override
  public byte[] encodeInternal(Integer field, FieldInfo fieldInfo) {

    byte[] bytes = null;
    if (fieldInfo.isSigned()) {
      bytes = ByteBuffer.allocate(4).putInt(field).array();

    } else { // Unsigned
      bytes = new byte[2];

      byte[] integerBytes = BigInteger.valueOf(field).toByteArray();
      int padding = 2 - integerBytes.length;
      System.arraycopy(integerBytes, 0, bytes, padding, integerBytes.length);
    }

    if (fieldInfo.isPrependFieldLength()) {
      bytes = ByteUtils.prependFieldLength(bytes);
    }

    return bytes;

  }

  @Override
  public Integer decode(Queue<Byte> bytes, FieldInfo fieldInfo) {

    if (fieldInfo.isPrependFieldLength()) {
      bytes.poll(); // Throw away the field length because we don't need it.
    }

    if (fieldInfo.isSigned()) {

      byte[] fieldBytes = ByteUtils.getFieldBytes(4, bytes);
      return ByteBuffer.wrap(fieldBytes).getInt();

    } else { // Unsigned
      byte[] valueBytes = ByteUtils.getFieldBytes(2, bytes);
      return ByteUtils.unsignedShortToInt(valueBytes);
    }
  }

}
