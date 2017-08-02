package com.brmayi.cloudrise.connection.commands;

import java.util.Collection;

import com.brmayi.cloudrise.connection.NamedNode;
import com.brmayi.cloudrise.connection.RedisServer;

public interface RedisSentinelCommands {

	/**
	 * Force a failover as if the {@literal master} was not reachable.
	 * 
	 * @param master must not be {@literal null}.
	 */
	void failover(NamedNode master);

	/**
	 * Get a {@link Collection} of monitored masters and their state.
	 * 
	 * @return Collection of {@link RedisServer}s. Never {@literal null}.
	 */
	Collection<RedisServer> masters();

	/**
	 * Show list of slaves for given {@literal master}.
	 * 
	 * @param master must not be {@literal null}.
	 * @return Collection of {@link RedisServer}s. Never {@literal null}.
	 */
	Collection<RedisServer> slaves(NamedNode master);

	/**
	 * Removes given {@literal master}. The server will no longer be monitored and will no longer be returned by
	 * {@link #masters()}.
	 * 
	 * @param master must not be {@literal null}.
	 */
	void remove(NamedNode master);

	/**
	 * Tell sentinel to start monitoring a new {@literal master} with the specified {@link RedisServer#getName()},
	 * {@link RedisServer#getHost()}, {@link RedisServer#getPort()}, and {@link RedisServer#getQuorum()}.
	 * 
	 * @param master must not be {@literal null}.
	 */
	void monitor(RedisServer master);

}