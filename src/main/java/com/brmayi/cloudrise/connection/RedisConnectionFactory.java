package com.brmayi.cloudrise.connection;

import org.springframework.dao.support.PersistenceExceptionTranslator;

public interface RedisConnectionFactory extends PersistenceExceptionTranslator {

	/**
	 * Provides a suitable connection for interacting with Redis.
	 *
	 * @return connection for interacting with Redis.
	 */
	RedisConnection getConnection();

	/**
	 * Provides a suitable connection for interacting with Redis Cluster.
	 *
	 * @return
	 * @since 1.7
	 */
	RedisClusterConnection getClusterConnection();

	/**
	 * Specifies if pipelined results should be converted to the expected data type. If false, results of
	 * {@link RedisConnection#closePipeline()} and {RedisConnection#exec()} will be of the type returned by the underlying
	 * driver This method is mostly for backwards compatibility with 1.0. It is generally always a good idea to allow
	 * results to be converted and deserialized. In fact, this is now the default behavior.
	 *
	 * @return Whether or not to convert pipeline and tx results
	 */
	boolean getConvertPipelineAndTxResults();

	/**
	 * Provides a suitable connection for interacting with Redis Sentinel.
	 *
	 * @return connection for interacting with Redis Sentinel.
	 * @since 1.4
	 */
	RedisSentinelConnection getSentinelConnection();
}