package org.example.vue3manager.core.snowflake;

/**
 * 雪花算法生成器
 *
 * @author aidan.liu
 */
public class SnowflakeIdGenerator {
  // 起始时间戳（自定义一个过去时间）
  private static final long START_TIMESTAMP = 1640995200000L; // 2022-01-01 00:00:00

  // 每部分占用的位数
  private static final long SEQUENCE_BITS = 12L;
  private static final long MACHINE_BITS = 5L;
  private static final long DATACENTER_BITS = 5L;

  // 最大值计算（例如最大支持31台机器）
  private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BITS);
  private static final long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BITS);
  private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

  // 位移偏移
  private static final long MACHINE_LEFT = SEQUENCE_BITS;
  private static final long DATACENTER_LEFT = SEQUENCE_BITS + MACHINE_BITS;
  private static final long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BITS;

  private final long datacenterId;
  private final long machineId;
  private long sequence = 0L;
  private long lastTimestamp = -1L;

  public SnowflakeIdGenerator(long datacenterId, long machineId) {
    if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
      throw new IllegalArgumentException(
          "Datacenter ID can't be greater than " + MAX_DATACENTER_NUM + " or less than 0");
    }
    if (machineId > MAX_MACHINE_NUM || machineId < 0) {
      throw new IllegalArgumentException("Machine ID can't be greater than " + MAX_MACHINE_NUM + " or less than 0");
    }
    this.datacenterId = datacenterId;
    this.machineId = machineId;
  }

  public synchronized String nextIdStr() {
    return String.valueOf(nextId());
  }

  public synchronized long nextId() {
    long currentTimestamp = currentTimeMillis();
    if (currentTimestamp < lastTimestamp) {
      throw new RuntimeException("Clock moved backwards. Refusing to generate id");
    }

    if (currentTimestamp == lastTimestamp) {
      sequence = (sequence + 1) & MAX_SEQUENCE;
      if (sequence == 0) {
        // 当前毫秒内序列号已满，等待下一毫秒
        currentTimestamp = waitNextMillis(currentTimestamp);
      }
    } else {
      sequence = 0L;
    }

    lastTimestamp = currentTimestamp;

    return ((currentTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT)
        | (datacenterId << DATACENTER_LEFT)
        | (machineId << MACHINE_LEFT)
        | sequence;
  }

  private long waitNextMillis(long currentMillis) {
    long timestamp = currentTimeMillis();
    while (timestamp <= currentMillis) {
      timestamp = currentTimeMillis();
    }
    return timestamp;
  }

  private long currentTimeMillis() {
    return System.currentTimeMillis();
  }
}

