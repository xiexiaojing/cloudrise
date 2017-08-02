package com.brmayi.cloudrise.connection.commands;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.brmayi.cloudrise.connection.DataType;
import com.brmayi.cloudrise.connection.SortParameters;
import com.brmayi.cloudrise.core.Cursor;
import com.brmayi.cloudrise.core.ScanOptions;

/**
 * <pre>
 * *********************************************************************************************************
 * 版权所有（C） 2017 cloudrise 
 * 保留所有权利。
 *            .==.       .==.
 *           //'^\\     //^'\\
 *          // ^^\(\__/)/^ ^^\\
 *         //^ ^^ ^/6  6\ ^^^ \\
 *        //^ ^^ ^/( .. )\^ ^^ \\
 *       // ^^  ^/\|v""v|/\^^ ^ \\
 *      // ^^/\/  / '~~' \ \/\^ ^\\
 *      ----------------------------------------
 *      HERE BE DRAGONS WHICH CAN CREATE MIRACLE
 * *********************************************************************************************************
 * 基本信息
 * *********************************************************************************************************
 * 系统：cloudrise
 * 支持：jdk1.7及以上 redis2.6.0及以上
 * 模块：cloudrise commands
 * 功能：提供对Redis的key操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令
 * 编码：静儿(xiexiaojing@qq.com)
 * 时间: 2017.07.31
 * *********************************************************************************************************
 * 修改历史
 * *********************************************************************************************************
 * 修改者                            									           修改内容                      修改时间 
 * 静儿(987489055@qq.com)                            新建                             2017.07.31
 * *********************************************************************************************************
 * </pre>
 */
public interface RedisKeyCommands {

	/**
	 * 确定一个key是否存在
	 *
	 * @param key 键
	 * @return 是否存在
	 * @see <a href="http://redis.io/commands/exists">Redis Documentation: EXISTS</a>
	 */
	Boolean exists(byte[] key);

	/**
	 * 删除一个key
	 *
	 * @param keys 键
	 * @return 返回删除成功的key的数量
	 * @see <a href="http://redis.io/commands/del">Redis Documentation: DEL</a>
	 */
	Long del(byte[]... keys);

	/**
	 * Determine the type stored at {@code key}.
	 *
	 * @param key must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/type">Redis Documentation: TYPE</a>
	 */
	DataType type(byte[] key);

	/**
	 * Find all keys matching the given {@code pattern}.
	 *
	 * @param pattern must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/keys">Redis Documentation: KEYS</a>
	 */
	Set<byte[]> keys(byte[] pattern);

	/**
	 * Use a {@link Cursor} to iterate over keys.
	 *
	 * @param options must not be {@literal null}.
	 * @return
	 * @since 1.4
	 * @see <a href="http://redis.io/commands/scan">Redis Documentation: SCAN</a>
	 */
	Cursor<byte[]> scan(ScanOptions options);

	/**
	 * Return a random key from the keyspace.
	 *
	 * @return
	 * @see <a href="http://redis.io/commands/randomkey">Redis Documentation: RANDOMKEY</a>
	 */
	byte[] randomKey();

	/**
	 * Rename key {@code oldName} to {@code newName}.
	 *
	 * @param oldName must not be {@literal null}.
	 * @param newName must not be {@literal null}.
	 * @see <a href="http://redis.io/commands/rename">Redis Documentation: RENAME</a>
	 */
	void rename(byte[] oldName, byte[] newName);

	/**
	 * Rename key {@code oldName} to {@code newName} only if {@code newName} does not exist.
	 *
	 * @param oldName must not be {@literal null}.
	 * @param newName must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/renamenx">Redis Documentation: RENAMENX</a>
	 */
	Boolean renameNX(byte[] oldName, byte[] newName);

	/**
	 * Set time to live for given {@code key} in seconds.
	 *
	 * @param key must not be {@literal null}.
	 * @param seconds
	 * @return
	 * @see <a href="http://redis.io/commands/expire">Redis Documentation: EXPIRE</a>
	 */
	Boolean expire(byte[] key, long seconds);

	/**
	 * Set time to live for given {@code key} in milliseconds.
	 *
	 * @param key must not be {@literal null}.
	 * @param millis
	 * @return
	 * @see <a href="http://redis.io/commands/pexpire">Redis Documentation: PEXPIRE</a>
	 */
	Boolean pExpire(byte[] key, long millis);

	/**
	 * Set the expiration for given {@code key} as a {@literal UNIX} timestamp.
	 *
	 * @param key must not be {@literal null}.
	 * @param unixTime
	 * @return
	 * @see <a href="http://redis.io/commands/expireat">Redis Documentation: EXPIREAT</a>
	 */
	Boolean expireAt(byte[] key, long unixTime);

	/**
	 * Set the expiration for given {@code key} as a {@literal UNIX} timestamp in milliseconds.
	 *
	 * @param key must not be {@literal null}.
	 * @param unixTimeInMillis
	 * @return
	 * @see <a href="http://redis.io/commands/pexpireat">Redis Documentation: PEXPIREAT</a>
	 */
	Boolean pExpireAt(byte[] key, long unixTimeInMillis);

	/**
	 * Remove the expiration from given {@code key}.
	 *
	 * @param key must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/persist">Redis Documentation: PERSIST</a>
	 */
	Boolean persist(byte[] key);

	/**
	 * Move given {@code key} to database with {@code index}.
	 *
	 * @param key must not be {@literal null}.
	 * @param dbIndex
	 * @return
	 * @see <a href="http://redis.io/commands/move">Redis Documentation: MOVE</a>
	 */
	Boolean move(byte[] key, int dbIndex);

	/**
	 * Get the time to live for {@code key} in seconds.
	 *
	 * @param key must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/ttl">Redis Documentation: TTL</a>
	 */
	Long ttl(byte[] key);

	/**
	 * Get the time to live for {@code key} in and convert it to the given {@link TimeUnit}.
	 *
	 * @param key must not be {@literal null}.
	 * @param timeUnit must not be {@literal null}.
	 * @return
	 * @since 1.8
	 * @see <a href="http://redis.io/commands/ttl">Redis Documentation: TTL</a>
	 */
	Long ttl(byte[] key, TimeUnit timeUnit);

	/**
	 * Get the precise time to live for {@code key} in milliseconds.
	 *
	 * @param key must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/pttl">Redis Documentation: PTTL</a>
	 */
	Long pTtl(byte[] key);

	/**
	 * Get the precise time to live for {@code key} in and convert it to the given {@link TimeUnit}.
	 *
	 * @param key must not be {@literal null}.
	 * @param timeUnit must not be {@literal null}.
	 * @return
	 * @since 1.8
	 * @see <a href="http://redis.io/commands/pttl">Redis Documentation: PTTL</a>
	 */
	Long pTtl(byte[] key, TimeUnit timeUnit);

	/**
	 * Sort the elements for {@code key}.
	 *
	 * @param key must not be {@literal null}.
	 * @param params must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/sort">Redis Documentation: SORT</a>
	 */
	List<byte[]> sort(byte[] key, SortParameters params);

	/**
	 * Sort the elements for {@code key} and store result in {@code storeKey}.
	 *
	 * @param key must not be {@literal null}.
	 * @param params must not be {@literal null}.
	 * @param storeKey must not be {@literal null}.
	 * @return number of values.
	 * @see <a href="http://redis.io/commands/sort">Redis Documentation: SORT</a>
	 */
	Long sort(byte[] key, SortParameters params, byte[] storeKey);

	/**
	 * Retrieve serialized version of the value stored at {@code key}.
	 *
	 * @param key must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/dump">Redis Documentation: DUMP</a>
	 */
	byte[] dump(byte[] key);

	/**
	 * Create {@code key} using the {@code serializedValue}, previously obtained using {@link #dump(byte[])}.
	 *
	 * @param key must not be {@literal null}.
	 * @param ttlInMillis
	 * @param serializedValue must not be {@literal null}.
	 * @see <a href="http://redis.io/commands/restore">Redis Documentation: RESTORE</a>
	 */
	void restore(byte[] key, long ttlInMillis, byte[] serializedValue);
}