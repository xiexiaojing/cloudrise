package com.brmayi.cloudrise.core.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.brmayi.cloudrise.core.BoundKeyOperations;
import com.brmayi.cloudrise.core.RedisOperations;

public abstract class DefaultBoundKeyOperations<K> implements BoundKeyOperations<K> {

	private K key;
	private final RedisOperations<K, ?> ops;

	DefaultBoundKeyOperations(K key, RedisOperations<K, ?> operations) {

		setKey(key);
		this.ops = operations;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundKeyOperations#getKey()
	 */
	@Override
	public K getKey() {
		return key;
	}

	protected void setKey(K key) {
		this.key = key;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundKeyOperations#expire(long, java.util.concurrent.TimeUnit)
	 */
	@Override
	public Boolean expire(long timeout, TimeUnit unit) {
		return ops.expire(key, timeout, unit);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundKeyOperations#expireAt(java.util.Date)
	 */
	@Override
	public Boolean expireAt(Date date) {
		return ops.expireAt(key, date);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundKeyOperations#getExpire()
	 */
	@Override
	public Long getExpire() {
		return ops.getExpire(key);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundKeyOperations#persist()
	 */
	@Override
	public Boolean persist() {
		return ops.persist(key);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundKeyOperations#rename(java.lang.Object)
	 */
	@Override
	public void rename(K newKey) {
		if (ops.hasKey(key)) {
			ops.rename(key, newKey);
		}
		key = newKey;
	}
}
