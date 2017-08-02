package com.brmayi.cloudrise.connection;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.brmayi.cloudrise.connection.commands.RedisCommands;
import com.brmayi.cloudrise.connection.commands.RedisHashCommands;
import com.brmayi.cloudrise.connection.commands.RedisKeyCommands;
import com.brmayi.cloudrise.connection.commands.RedisListCommands;
import com.brmayi.cloudrise.connection.commands.RedisServerCommands;
import com.brmayi.cloudrise.connection.commands.RedisSetCommands;
import com.brmayi.cloudrise.connection.commands.RedisStringCommands;
import com.brmayi.cloudrise.connection.commands.RedisZSetCommands;
import com.brmayi.cloudrise.exception.CloudRiseException;

public interface RedisConnection extends RedisCommands {
	/**
	 * Get {@link RedisHashCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	RedisHashCommands hashCommands();

	/**
	 * Get {@link RedisKeyCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	RedisKeyCommands keyCommands();

	/**
	 * Get {@link RedisListCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	RedisListCommands listCommands();

	/**
	 * Get {@link RedisSetCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	RedisSetCommands setCommands();

	/**
	 * Get {@link RedisServerCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	RedisServerCommands serverCommands();

	/**
	 * Get {@link RedisStringCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	RedisStringCommands stringCommands();

	/**
	 * Get {@link RedisZSetCommands}.
	 *
	 * @return never {@literal null}.
	 * @since 2.0
	 */
	RedisZSetCommands zSetCommands();

	/**
	 * Closes (or quits) the connection.
	 *
	 * @throws DataAccessException
	 */
	void close() throws DataAccessException;

	/**
	 * Indicates whether the underlying connection is closed or not.
	 *
	 * @return true if the connection is closed, false otherwise.
	 */
	boolean isClosed();

	/**
	 * Returns the native connection (the underlying library/driver object).
	 *
	 * @return underlying, native object
	 */
	Object getNativeConnection();

	/**
	 * Indicates whether the connection is in "queue"(or "MULTI") mode or not. When queueing, all commands are postponed
	 * until EXEC or DISCARD commands are issued. Since in queueing no results are returned, the connection will return
	 * NULL on all operations that interact with the data.
	 *
	 * @return true if the connection is in queue/MULTI mode, false otherwise
	 */
	boolean isQueueing();

	/**
	 * Indicates whether the connection is currently pipelined or not.
	 *
	 * @return true if the connection is pipelined, false otherwise
	 * @see #openPipeline()
	 * @see #isQueueing()
	 */
	boolean isPipelined();

	/**
	 * Activates the pipeline mode for this connection. When pipelined, all commands return null (the reply is read at the
	 * end through {@link #closePipeline()}. Calling this method when the connection is already pipelined has no effect.
	 * Pipelining is used for issuing commands without requesting the response right away but rather at the end of the
	 * batch. While somewhat similar to MULTI, pipelining does not guarantee atomicity - it only tries to improve
	 * performance when issuing a lot of commands (such as in batching scenarios).
	 * <p>
	 * Note:
	 * </p>
	 * Consider doing some performance testing before using this feature since in many cases the performance benefits are
	 * minimal yet the impact on usage are not.
	 *
	 * @see #multi()
	 */
	void openPipeline();

	/**
	 * Executes the commands in the pipeline and returns their result. If the connection is not pipelined, an empty
	 * collection is returned.
	 *
	 * @throws RedisPipelineException if the pipeline contains any incorrect/invalid statements
	 * @return the result of the executed commands.
	 */
	List<Object> closePipeline() throws CloudRiseException;

	/**
	 * @return
	 * @since 1.4
	 */
	RedisSentinelConnection getSentinelConnection();
}