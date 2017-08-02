package com.brmayi.cloudrise.core;

import java.util.Collection;
import java.util.Set;

import com.brmayi.cloudrise.connection.RedisClusterNode;
import com.brmayi.cloudrise.connection.RedisClusterNode.SlotRange;
import com.brmayi.cloudrise.connection.RedisConnection;

public interface ClusterOperations<K, V> {

	/**
	 * Get all keys located at given node.
	 * 
	 * @param node must not be {@literal null}.
	 * @param pattern
	 * @return never {@literal null}.
	 * @see RedisConnection#keys(byte[])
	 */
	Set<K> keys(RedisClusterNode node, K pattern);

	/**
	 * Ping the given node;
	 * 
	 * @param node must not be {@literal null}.
	 * @return
	 * @see RedisConnection#ping()
	 */
	String ping(RedisClusterNode node);

	/**
	 * Get a random key from the range served by the given node.
	 * 
	 * @param node must not be {@literal null}.
	 * @return
	 * @see RedisConnection#randomKey()
	 */
	K randomKey(RedisClusterNode node);

	/**
	 * Add slots to given node;
	 * 
	 * @param node must not be {@literal null}.
	 * @param slots must not be {@literal null}.
	 */
	void addSlots(RedisClusterNode node, int... slots);

	/**
	 * Add slots in {@link SlotRange} to given node.
	 * 
	 * @param node must not be {@literal null}.
	 * @param range must not be {@literal null}.
	 */
	void addSlots(RedisClusterNode node, SlotRange range);

	/**
	 * Start an {@literal Append Only File} rewrite process on given node.
	 * 
	 * @param node must not be {@literal null}.
	 * @see RedisConnection#bgReWriteAof()
	 */
	void bgReWriteAof(RedisClusterNode node);

	/**
	 * Start background saving of db on given node.
	 * 
	 * @param node must not be {@literal null}.
	 * @see RedisConnection#bgSave()
	 */
	void bgSave(RedisClusterNode node);

	/**
	 * Add the node to cluster.
	 * 
	 * @param node must not be {@literal null}.
	 */
	void meet(RedisClusterNode node);

	/**
	 * Remove the node from the cluster.
	 * 
	 * @param node must not be {@literal null}.
	 */
	void forget(RedisClusterNode node);

	/**
	 * Flush db on node.
	 * 
	 * @param node must not be {@literal null}.
	 * @see RedisConnection#flushDb()
	 */
	void flushDb(RedisClusterNode node);

	/**
	 * @param node must not be {@literal null}.
	 * @return
	 */
	Collection<RedisClusterNode> getSlaves(RedisClusterNode node);

	/**
	 * Synchronous save current db snapshot on server.
	 * 
	 * @param node must not be {@literal null}.
	 * @see RedisConnection#save()
	 */
	void save(RedisClusterNode node);

	/**
	 * Shutdown given node.
	 * 
	 * @param node must not be {@literal null}.
	 * @see RedisConnection#shutdown()
	 */
	void shutdown(RedisClusterNode node);

	/**
	 * Move slot assignment from one source to target node and copy keys associated with the slot.
	 * 
	 * @param source must not be {@literal null}.
	 * @param slot
	 * @param target must not be {@literal null}.
	 */
	void reshard(RedisClusterNode source, int slot, RedisClusterNode target);
}
