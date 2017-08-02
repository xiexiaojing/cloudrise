package com.brmayi.cloudrise.connection.commands;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * 功能：提供对Redis的hash操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令
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
public interface RedisHashCommands {
	/**
	 * 将哈希表 key 中的域 field 的值设为 value 。
	 * 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
	 * 如果域 field 已经存在于哈希表中，旧值将被覆盖。
	 * 
	 * @param key 键
	 * @param field 域(一个哈希中的key)
	 * @param value 值
	 * @return 如果 field 是哈希表中的一个新建域，并且值设置成功，返回 1 。如果哈希表中域 field 已经存在且旧值已被新值覆盖，返回 0 。
	 * @see <a href="http://redis.io/commands/hset">Redis Documentation: HSET</a>
	 */
	Boolean hSet(byte[] key, byte[] field, byte[] value);

	/**
	 * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
	 * 若域 field 已经存在，该操作无效。
	 * 
	 * @param key 键
	 * @param field 域(一个哈希中的key)
	 * @param value 值
	 * @return 设置成功，返回 1 。如果给定域已经存在且没有操作被执行，返回 0 。
	 * @see <a href="http://redis.io/commands/hsetnx">Redis Documentation: HSETNX</a>
	 */
	Boolean hSetNX(byte[] key, byte[] field, byte[] value);

	/**
	 * 返回哈希表 key 中给定域 field 的值。
	 * 
	 * @param key 键
	 * @param field 域(一个哈希中的key)
	 * @return 给定域的值。当给定域不存在或是给定 key 不存在时，返回 nil 。
	 * @see <a href="http://redis.io/commands/hget">Redis Documentation: HGET</a>
	 */
	byte[] hGet(byte[] key, byte[] field);

	/**
	 * 返回哈希表 key 中，一个或多个给定域的值。
	 * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
	 * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
	 * 
	 * @param key 键
	 * @param fields 域(一个哈希中的key)
	 * @return 一个包含多个给定域的关联值的表，表值的排列顺序和给定域参数的请求顺序一样。
	 * @see <a href="http://redis.io/commands/hmget">Redis Documentation: HMGET</a>
	 */
	List<byte[]> hMGet(byte[] key, byte[]... fields);

	/**
	 * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
	 * 此命令会覆盖哈希表中已存在的域。
	 * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
	 * 
	 * @param key 键
	 * @param hashes 哈希表
	 * @see <a href="http://redis.io/commands/hmset">Redis Documentation: HMSET</a>
	 */
	void hMSet(byte[] key, Map<byte[], byte[]> hashes);

	/**
	 * 为哈希表 key 中的域 field 的值加上增量 increment 。
	 * 增量也可以为负数，相当于对给定域进行减法操作。
	 * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
	 * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
	 * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
	 * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
	 * 
	 * @param key 键
	 * @param field 域(一个哈希中的key)
	 * @param delta 增量
	 * @return 执行加法操作之后 field 域的值。
	 * @see <a href="http://redis.io/commands/hincrby">Redis Documentation: HINCRBY</a>
	 */
	Long hIncrBy(byte[] key, byte[] field, long delta);

	/**
	 * 为哈希表 key 中的域 field 加上浮点数增量 increment 。
	 * 如果哈希表中没有域 field ，那么 HINCRBYFLOAT 会先将域 field 的值设为 0 ，然后再执行加法操作。
	 * 如果键 key 不存在，那么 HINCRBYFLOAT 会先创建一个哈希表，再创建域 field ，最后再执行加法操作。
	 * 当以下任意一个条件发生时，返回一个错误：
	 * 域 field 的值不是字符串类型(因为 redis 中的数字和浮点数都以字符串的形式保存，所以它们都属于字符串类型）
	 * 域 field 当前的值或给定的增量 increment 不能解释(parse)为双精度浮点数(double precision floating point number)
	 * HINCRBYFLOAT 命令的详细功能和 INCRBYFLOAT 命令类似，请查看 INCRBYFLOAT 命令获取更多相关信息。
	 * 
	 * @param key 键
	 * @param field 域(一个哈希中的key)
	 * @param delta 增量
	 * @return 执行加法操作之后 field 域的值。
	 * @see <a href="http://redis.io/commands/hincrbyfloat">Redis Documentation: HINCRBYFLOAT</a>
	 */
	Double hIncrBy(byte[] key, byte[] field, double delta);

	/**
	 * 查看哈希表 key 中，给定域 field 是否存在。
	 * 
	 * @param key 键
	 * @param field 域(一个哈希中的key)
	 * @return 如果哈希表含有给定域，返回 1 。如果哈希表不含有给定域，或 key 不存在，返回 0 。
	 * @see <a href="http://redis.io/commands/hexits">Redis Documentation: HEXISTS</a>
	 */
	Boolean hExists(byte[] key, byte[] field);

	/**
	 * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
	 *
	 * @param key 键
	 * @param fields 域(一个哈希中的key)
	 * @return 被成功移除的域的数量，不包括被忽略的域。
	 * @see <a href="http://redis.io/commands/hdel">Redis Documentation: HDEL</a>
	 */
	Long hDel(byte[] key, byte[]... fields);

	/**
	 * 返回哈希表 key 中域的数量。
	 * 
	 * @param key 键
	 * @return 哈希表中域的数量。当 key 不存在时，返回 0 。
	 * @see <a href="http://redis.io/commands/hlen">Redis Documentation: HLEN</a>
	 */
	Long hLen(byte[] key);

	/**
	 * 返回哈希表 key 中的所有域。
	 * 时间复杂度：O(N)， N 为哈希表的大小。
	 * @param key 键
	 * @return 一个包含哈希表中所有域的表。当 key 不存在时，返回一个空表。
	 * @see <a href="http://redis.io/commands/hkeys">Redis Documentation: HKEYS</a>
	 */
	Set<byte[]> hKeys(byte[] key);

	/**
	 * 返回哈希表 key 中所有域的值。
	 * 
	 * @param key 键
	 * @return 一个包含哈希表中所有值的表。当 key 不存在时，返回一个空表。
	 * @see <a href="http://redis.io/commands/hvals">Redis Documentation: HVALS</a>
	 */
	List<byte[]> hVals(byte[] key);

	/**
	 * 返回哈希表 key 中，所有的域和值。在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是哈希表大小的两倍。
	 * 
	 * @param key 键
	 * @return 以列表形式返回哈希表的域和域的值。若 key 不存在，返回空列表。
	 * @see <a href="http://redis.io/commands/hgetall">Redis Documentation: HGETALL</a>
	 */
	Map<byte[], byte[]> hGetAll(byte[] key);

	/**
	 * SCAN 命令及其相关的 SSCAN 命令、 HSCAN 命令和 ZSCAN 命令都用于增量地迭代（incrementally iterate）一集元素（a collection of elements）：
	 * SCAN 命令用于迭代当前数据库中的数据库键。
	 * SSCAN 命令用于迭代集合键中的元素。
	 * HSCAN 命令用于迭代哈希键中的键值对。
	 * ZSCAN 命令用于迭代有序集合中的元素（包括元素成员和元素分值）。
	 * 以上列出的四个命令都支持增量式迭代， 它们每次执行都只会返回少量元素， 所以这些命令可以用于生产环境， 而不会出现像 KEYS 命令、 SMEMBERS 命令带来的问题 —— 当 KEYS 命令被用于处理一个大的数据库时， 又或者 SMEMBERS 命令被用于处理一个大的集合键时， 它们可能会阻塞服务器达数秒之久。
	 * 不过， 增量式迭代命令也不是没有缺点的： 举个例子， 使用 SMEMBERS 命令可以返回集合键当前包含的所有元素， 但是对于 SCAN 这类增量式迭代命令来说， 因为在对键进行增量式迭代的过程中， 键可能会被修改， 所以增量式迭代命令只能对被返回的元素提供有限的保证 （offer limited guarantees about the returned elements）。
	 * 因为 SCAN 、 SSCAN 、 HSCAN 和 ZSCAN 四个命令的工作方式都非常相似， 所以这个文档会一并介绍这四个命令， 但是要记住：
	 * SSCAN 命令、 HSCAN 命令和 ZSCAN 命令的第一个参数总是一个数据库键。
	 * 而 SCAN 命令则不需要在第一个参数提供任何数据库键 —— 因为它迭代的是当前数据库中的所有数据库键。
	 * @param key 键
	 * @param options must not be {@literal null}.
	 * @return 迭代选项
	 * @since 1.4
	 * @see <a href="http://redis.io/commands/hscan">Redis Documentation: HSCAN</a>
	 */
	Cursor<Map.Entry<byte[], byte[]>> hScan(byte[] key, ScanOptions options);
}
