package com.brmayi.cloudrise.connection;

import java.io.Closeable;

import com.brmayi.cloudrise.connection.commands.RedisSentinelCommands;

public interface RedisSentinelConnection extends RedisSentinelCommands, Closeable {

	/**
	 * @return true if connected to server
	 */
	boolean isOpen();

}
