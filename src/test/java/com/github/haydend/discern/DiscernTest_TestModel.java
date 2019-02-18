package com.github.haydend.discern;

import org.junit.Assert;
import org.junit.Test;

public class DiscernTest_TestModel {

  // Class under test.
  private final Discern discern = new Discern();

  @Test
  public void test() {

    // Given
    TestModel testModel = new TestModel(1, 2, "abc", 4, -1);

    // When
    byte[] bytes = discern.encode(testModel);

    // Then
    byte[] expectedBytes = new byte[] {
        0x00, 0x01,
        0x04, 0x00, 0x00, 0x00, 0x02,
        0x03, 0x61, 0x62, 0x63,
        0x02, 0x00, 0x04,
        (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
    };

    Assert.assertArrayEquals(expectedBytes, bytes);
  }

}
