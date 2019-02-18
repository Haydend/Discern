package com.github.haydend.discern.handler.field;

import java.nio.charset.StandardCharsets;
import java.util.Queue;

import com.github.haydend.discern.model.FieldInfo;
import com.github.haydend.discern.util.ByteUtils;

public class StringHandler extends FieldHandler<String> {

  @Override
  public byte[] encodeInternal(String field, FieldInfo fieldInfo) {

    byte[] fieldbytes = field.getBytes(StandardCharsets.UTF_8);

    if (fieldInfo.isPrependFieldLength()) {
      fieldbytes = ByteUtils.prependFieldLength(fieldbytes);
    }

    return fieldbytes;
  }

  @Override
  public String decode(Queue<Byte> bytes, FieldInfo fieldInfo) {

    // Use Prepended Field length byte if supplied, else check for fixed length annotation, else read the rest of the queue.
    int fieldLength;
    if (fieldInfo.isPrependFieldLength()) {
      fieldLength = ByteUtils.getFieldLength(bytes);

    } else {
      fieldLength = fieldInfo.getFixedLength()
          .orElse(bytes.size());
    }

    byte[] fieldBytes = ByteUtils.getFieldBytes(fieldLength, bytes);
    return new String(fieldBytes, StandardCharsets.UTF_8);
  }


}
