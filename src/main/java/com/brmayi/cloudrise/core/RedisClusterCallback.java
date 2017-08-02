package com.brmayi.cloudrise.core;


import org.springframework.dao.DataAccessException;

import com.brmayi.cloudrise.connection.RedisClusterConnection;

public interface RedisClusterCallback<T> {
	/**
	 * Gets called by {@link RedisClusterTemplate} with an active Redis connection. Does not need to care about activating
	 * or closing the connection or handling exceptions.
	 * 
	 * @param connection never {@literal null}.
	 * @return
	 * @throws DataAccessException
	 */
	T doInRedis(RedisClusterConnection connection) throws DataAccessException;
}
