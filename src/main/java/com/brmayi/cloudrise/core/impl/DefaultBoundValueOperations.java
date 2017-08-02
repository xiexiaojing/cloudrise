package com.brmayi.cloudrise.core.impl;

import java.util.concurrent.TimeUnit;

import com.brmayi.cloudrise.connection.DataType;
import com.brmayi.cloudrise.core.BoundValueOperations;
import com.brmayi.cloudrise.core.RedisOperations;
import com.brmayi.cloudrise.core.ValueOperations;

public class DefaultBoundValueOperations<K, V> extends DefaultBoundKeyOperations<K> implements BoundValueOperations<K, V> {

	private final ValueOperations<K, V> ops;

	/**
	 * Constructs a new <code>DefaultBoundValueOperations</code> instance.
	 *
	 * @param key
	 * @param operations
	 */
	public DefaultBoundValueOperations(K key, RedisOperations<K, V> operations) {

		super(key, operations);
		this.ops = operations.opsForValue();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#get()
	 */
	@Override
	public V get() {
		return ops.get(getKey());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#getAndSet(java.lang.Object)
	 */
	@Override
	public V getAndSet(V value) {
		return ops.getAndSet(getKey(), value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#increment(long)
	 */
	@Override
	public Long increment(long delta) {
		return ops.increment(getKey(), delta);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#increment(double)
	 */
	@Override
	public Double increment(double delta) {
		return ops.increment(getKey(), delta);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#append(java.lang.String)
	 */
	@Override
	public Integer append(String value) {
		return ops.append(getKey(), value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#get(long, long)
	 */
	@Override
	public String get(long start, long end) {
		return ops.get(getKey(), start, end);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#set(java.lang.Object, long, java.util.concurrent.TimeUnit)
	 */
	@Override
	public void set(V value, long timeout, TimeUnit unit) {
		ops.set(getKey(), value, timeout, unit);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#set(java.lang.Object)
	 */
	@Override
	public void set(V value) {
		ops.set(getKey(), value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#setIfAbsent(java.lang.Object)
	 */
	@Override
	public Boolean setIfAbsent(V value) {
		return ops.setIfAbsent(getKey(), value);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#set(java.lang.Object, long)
	 */
	@Override
	public void set(V value, long offset) {
		ops.set(getKey(), value, offset);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#size()
	 */
	@Override
	public Long size() {
		return ops.size(getKey());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundValueOperations#getOperations()
	 */
	@Override
	public RedisOperations<K, V> getOperations() {
		return ops.getOperations();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.BoundKeyOperations#getType()
	 */
	@Override
	public DataType getType() {
		return DataType.STRING;
	}
}