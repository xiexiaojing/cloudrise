package com.brmayi.cloudrise.core.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.brmayi.cloudrise.connection.DataType;
import com.brmayi.cloudrise.core.BoundHashOperations;
import com.brmayi.cloudrise.core.Cursor;
import com.brmayi.cloudrise.core.HashOperations;
import com.brmayi.cloudrise.core.RedisOperations;
import com.brmayi.cloudrise.core.ScanOptions;

public class DefaultBoundHashOperations<H, HK, HV> extends DefaultBoundKeyOperations<H> implements BoundHashOperations<H, HK, HV> {

	private final HashOperations<H, HK, HV> ops;

	/**
	 * Constructs a new <code>DefaultBoundHashOperations</code> instance.
	 *
	 * @param key
	 * @param operations
	 */
	public DefaultBoundHashOperations(H key, RedisOperations<H, ?> operations) {
		super(key, operations);
		this.ops = operations.opsForHash();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#delete(java.lang.
	 * Object[])
	 */
	@Override
	public Long delete(Object... keys) {
		return ops.delete(getKey(), keys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#get(java.lang.
	 * Object)
	 */
	@Override
	public HV get(Object key) {
		return ops.get(getKey(), key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#multiGet(java.
	 * util.Collection)
	 */
	@Override
	public List<HV> multiGet(Collection<HK> hashKeys) {
		return ops.multiGet(getKey(), hashKeys);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#getOperations()
	 */
	@Override
	public RedisOperations<H, ?> getOperations() {
		return ops.getOperations();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#hasKey(java.lang.
	 * Object)
	 */
	@Override
	public Boolean hasKey(Object key) {
		return ops.hasKey(getKey(), key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#increment(java.
	 * lang.Object, long)
	 */
	@Override
	public Long increment(HK key, long delta) {
		return ops.increment(getKey(), key, delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#increment(java.
	 * lang.Object, double)
	 */
	@Override
	public Double increment(HK key, double delta) {
		return ops.increment(getKey(), key, delta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.redis.core.BoundHashOperations#keys()
	 */
	@Override
	public Set<HK> keys() {
		return ops.keys(getKey());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.redis.core.BoundHashOperations#size()
	 */
	@Override
	public Long size() {
		return ops.size(getKey());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#putAll(java.util.
	 * Map)
	 */
	@Override
	public void putAll(Map<? extends HK, ? extends HV> m) {
		ops.putAll(getKey(), m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#put(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public void put(HK key, HV value) {
		ops.put(getKey(), key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.redis.core.BoundHashOperations#putIfAbsent(java.
	 * lang.Object, java.lang.Object)
	 */
	@Override
	public Boolean putIfAbsent(HK key, HV value) {
		return ops.putIfAbsent(getKey(), key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.redis.core.BoundHashOperations#values()
	 */
	@Override
	public List<HV> values() {
		return ops.values(getKey());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.redis.core.BoundHashOperations#entries()
	 */
	@Override
	public Map<HK, HV> entries() {
		return ops.entries(getKey());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.redis.core.BoundKeyOperations#getType()
	 */
	@Override
	public DataType getType() {
		return DataType.HASH;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.redis.core.BoundHashOperations#scan(org.
	 * springframework.data.redis.core.ScanOptions)
	 */
	@Override
	public Cursor<Entry<HK, HV>> scan(ScanOptions options) {
		return ops.scan(getKey(), options);
	}
}
