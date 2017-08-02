package com.brmayi.cloudrise.core;

import com.brmayi.cloudrise.connection.RedisConnection;
import com.brmayi.cloudrise.exception.CloudRiseException;

public interface RedisCallback<T> {
  public abstract T doInRedis(RedisConnection paramRedisConnection) throws CloudRiseException;
}
