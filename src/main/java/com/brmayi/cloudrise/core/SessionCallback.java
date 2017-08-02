package com.brmayi.cloudrise.core;

import com.brmayi.cloudrise.exception.CloudRiseException;

public interface SessionCallback<T> {

	/**
	 * Executes all the given operations inside the same session.
	 * 
	 * @param operations Redis operations
	 * @return return value
	 */
	<K, V> T execute(RedisOperations<K, V> operations) throws CloudRiseException;
}
