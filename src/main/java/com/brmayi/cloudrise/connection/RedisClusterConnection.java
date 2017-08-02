package com.brmayi.cloudrise.connection;

import java.util.Set;

import com.brmayi.cloudrise.connection.commands.RedisClusterCommands;
import com.brmayi.cloudrise.connection.commands.RedisClusterServerCommands;
import com.brmayi.cloudrise.connection.commands.RedisConnectionCommands;
import com.brmayi.cloudrise.connection.commands.RedisKeyCommands;

public interface RedisClusterConnection extends RedisConnection, RedisClusterCommands, RedisClusterServerCommands {

	/**
	 * @param node must not be {@literal null}.
	 * @return
	 * @see RedisConnectionCommands#ping()
	 */
	String ping(RedisClusterNode node);

	/**
	 * @param node must not be {@literal null}.
	 * @param pattern must not be {@literal null}.
	 * @return
	 * @see RedisKeyCommands#keys(byte[])
	 */
	Set<byte[]> keys(RedisClusterNode node, byte[] pattern);

	/**
	 * @param node must not be {@literal null}.
	 * @return
	 * @see RedisKeyCommands#randomKey()
	 */
	byte[] randomKey(RedisClusterNode node);
}