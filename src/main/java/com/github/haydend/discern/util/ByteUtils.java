package com.github.haydend.discern.util;

import java.util.Queue;

public class ByteUtils {

  public static final int unsignedShortToInt(byte[] b) {
    int l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
    return l;
  }

  public static final long unsignedIntToLong(byte[] b) {
    long l = 0;
    l |= b[0] & 0xFF;
    l <<= 8;
    l |= b[1] & 0xFF;
    l <<= 8;
    l |= b[2] & 0xFF;
    l <<= 8;
    l |= b[3] & 0xFF;
    return l;
  }

  public static byte[] getFieldBytes(int fieldLength, Queue<Byte> messageBytes) {
    byte[] fieldBytes = new byte[fieldLength];

    for (int i = 0; i < fieldLength; i++) {
      fieldBytes[i] = messageBytes.poll();
    }

    return fieldBytes;
  }

  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
          + Character.digit(s.charAt(i + 1), 16));
    }
    return data;
  }

  private final static char[] hexArray = "0123456789abcdef".toCharArray();

  public static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = hexArray[v >>> 4];
      hexChars[j * 2 + 1] = hexArray[v & 0x0F];
    }
    return new String(hexChars);
  }

  public static byte[] prependFieldLength(byte[] fieldBytes) {
    // Get the field length that need prepending the byte array.
    byte fieldLength = (byte) fieldBytes.length;

    // Build a new byte array, with the field length byte first, then the field bytes;
    byte[] messageBytes = new byte[fieldBytes.length + 1];
    messageBytes[0] = fieldLength;
    System.arraycopy(fieldBytes, 0, messageBytes, 1, fieldBytes.length);

    return messageBytes;
  }

  public static int getFieldLength(Queue<Byte> messageBytes) {
    int fieldLength = messageBytes.poll();
    return fieldLength;
  }

}
