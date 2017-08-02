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
	 * 确定键存储值的类型
	 *
	 * @param key 键
	 * @return
	 * @see <a href="http://redis.io/commands/type">Redis Documentation: TYPE</a>
	 */
	DataType type(byte[] key);

	/**
	 * 获取所有指定匹配模式的键
	 *
	 * @param pattern 匹配模式，全匹配为*
	 * @return
	 * @see <a href="http://redis.io/commands/keys">Redis Documentation: KEYS</a>
	 */
	Set<byte[]> keys(byte[] pattern);

	/**
	 * 使用游标来进行键的迭代
	 * SCAN 命令及其相关的 SSCAN 命令、 HSCAN 命令和 ZSCAN 命令都用于增量地迭代（incrementally iterate）一集元素（a collection of elements）：
	 * SCAN 命令用于迭代当前数据库中的数据库键。
	 * SSCAN 命令用于迭代集合键中的元素。
	 * HSCAN 命令用于迭代哈希键中的键值对。
	 * ZSCAN 命令用于迭代有序集合中的元素（包括元素成员和元素分值）。
	 * 以上列出的四个命令都支持增量式迭代， 它们每次执行都只会返回少量元素， 所以这些命令可以用于生产环境， 
	 * 而不会出现像 KEYS 命令、 SMEMBERS 命令带来的问题 —— 当 KEYS 命令被用于处理一个大的数据库时， 又或者 SMEMBERS 命令被用于处理一个大的集合键时， 它们可能会阻塞服务器达数秒之久。
	 * 不过， 增量式迭代命令也不是没有缺点的： 举个例子， 使用 SMEMBERS 命令可以返回集合键当前包含的所有元素，
	 *  但是对于 SCAN 这类增量式迭代命令来说， 因为在对键进行增量式迭代的过程中， 键可能会被修改， 所以增量式迭代命令只能对被返回的元素提供有限的保证 （offer limited guarantees about the returned elements）。
	 *  因为 SCAN 、 SSCAN 、 HSCAN 和 ZSCAN 四个命令的工作方式都非常相似， 所以这个文档会一并介绍这四个命令， 但是要记住：
	 *  SSCAN 命令、 HSCAN 命令和 ZSCAN 命令的第一个参数总是一个数据库键。
	 *  而 SCAN 命令则不需要在第一个参数提供任何数据库键 —— 因为它迭代的是当前数据库中的所有数据库键。
	 *  SCAN 命令返回的每个元素都是一个数据库键。
	 * @param options 迭代选项
	 * @return
	 * @since 1.4
	 * @see <a href="http://redis.io/commands/scan">Redis Documentation: SCAN</a>
	 */
	Cursor<byte[]> scan(ScanOptions options);

	/**
	 * Redis RANDOMKEY 命令从当前数据库中随机返回一个 key 。
	 *
	 * @return 随机返回一个 key 。
	 * @see <a href="http://redis.io/commands/randomkey">Redis Documentation: RANDOMKEY</a>
	 */
	byte[] randomKey();

	/**
	 * Redis Rename 命令用于修改 key 的名称 。
	 *
	 * @param oldName 原名称
	 * @param newName 新名称
	 * @see <a href="http://redis.io/commands/rename">Redis Documentation: RENAME</a>
	 */
	void rename(byte[] oldName, byte[] newName);

	/**
	 * Redis Renamenx 命令用于在新的 key 不存在时修改 key 的名称 。
	 *
	 * @param oldName 原名称
	 * @param newName 新名称
	 * @return
	 * @see <a href="http://redis.io/commands/renamenx">Redis Documentation: RENAMENX</a>
	 */
	Boolean renameNX(byte[] oldName, byte[] newName);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0 )，它会被自动删除。时间单位是秒
	 * 在 Redis 中，带有生存时间的 key 被称为『易失的』(volatile)。
	 * 生存时间可以通过使用 DEL 命令来删除整个 key 来移除，或者被 SET 和 GETSET 命令覆写(overwrite)，
	 * 这意味着，如果一个命令只是修改(alter)一个带生存时间的 key 的值而不是用一个新的 key 值来代替(replace)它的话，那么生存时间不会被改变。
	 * 比如说，对一个 key 执行 INCR 命令，对一个列表进行 LPUSH 命令，或者对一个哈希表执行 HSET 命令，这类操作都不会修改 key 本身的生存时间。
	 * 另一方面，如果使用 RENAME 对一个 key 进行改名，那么改名后的 key 的生存时间和改名前一样。
	 * RENAME 命令的另一种可能是，尝试将一个带生存时间的 key 改名成另一个带生存时间的 another_key ，
	 * 这时旧的 another_key (以及它的生存时间)会被删除，然后旧的 key 会改名为 another_key ，因此，新的 another_key 的生存时间也和原本的 key 一样。
	 * 使用 PERSIST 命令可以在不删除 key 的情况下，移除 key 的生存时间，让 key 重新成为一个『持久的』(persistent) key 。
	 *
	 * @param key 键
	 * @param seconds 过期时间
	 * @return 是否设置成功
	 * @see <a href="http://redis.io/commands/expire">Redis Documentation: EXPIRE</a>
	 */
	Boolean expire(byte[] key, long seconds);

	/**
	 * 这个命令和 EXPIRE 命令的作用类似，但是它以毫秒为单位设置 key 的生存时间，而不像 EXPIRE 命令那样，以秒为单位。
	 *
	 * @param key 键
	 * @param millis 毫秒
	 * @return 是否设置成功
	 * @see <a href="http://redis.io/commands/pexpire">Redis Documentation: PEXPIRE</a>
	 */
	Boolean pExpire(byte[] key, long millis);

	/**
	 * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
	 *
	 * @param key 键 
	 * @param unixTime  UNIX 时间戳(unix timestamp)。
	 * @return 是否设置成功
	 * @see <a href="http://redis.io/commands/expireat">Redis Documentation: EXPIREAT</a>
	 */
	Boolean expireAt(byte[] key, long unixTime);

	/**
	 * 这个命令和 EXPIREAT 命令类似，但它以毫秒为单位设置 key 的过期 unix 时间戳，而不是像 EXPIREAT 那样，以秒为单位。
	 *
	 * @param key 键 
	 * @param unixTimeInMillis 以毫秒为单位设置 key 的过期 unix 时间戳
	 * @return 是否设置成功
	 * @see <a href="http://redis.io/commands/pexpireat">Redis Documentation: PEXPIREAT</a>
	 */
	Boolean pExpireAt(byte[] key, long unixTimeInMillis);

	/**
	 * 移除给定 key 的生存时间，将这个 key 从『易失的』(带生存时间 key )转换成『持久的』(一个不带生存时间、永不过期的 key )。
	 *
	 * @param key 键 
	 * @return 是否设置成功
	 * @see <a href="http://redis.io/commands/persist">Redis Documentation: PERSIST</a>
	 */
	Boolean persist(byte[] key);

	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中。
	 * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，或者 key 不存在于当前数据库，那么 MOVE 没有任何效果。
	 * 因此，也可以利用这一特性，将 MOVE 当作锁(locking)原语(primitive)。
	 *
	 * @param key 键 
	 * @param dbIndex 数据库 db
	 * @return 移动成功返回 1 ，失败则返回 0 。
	 * @see <a href="http://redis.io/commands/move">Redis Documentation: MOVE</a>
	 */
	Boolean move(byte[] key, int dbIndex);

	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
	 *
	 * @param key 键 
	 * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key 的剩余生存时间。
	 * @see <a href="http://redis.io/commands/ttl">Redis Documentation: TTL</a>
	 */
	Long ttl(byte[] key);

	/**
	 * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)，并转换为要转换为的时间单位
	 *
	 * @param key 键 
	 * @param timeUnit 要转换为的时间单位
	 * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key 的剩余生存时间。
	 * @see <a href="http://redis.io/commands/ttl">Redis Documentation: TTL</a>
	 */
	Long ttl(byte[] key, TimeUnit timeUnit);

	/**
	 * 这个命令类似于 TTL 命令，但它以毫秒为单位返回 key 的剩余生存时间，而不是像 TTL 命令那样，以秒为单位。
	 *
	 * @param key 键 
	 * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key 的剩余生存时间。
	 * @see <a href="http://redis.io/commands/pttl">Redis Documentation: PTTL</a>
	 */
	Long pTtl(byte[] key);

	/**
	 * 以毫秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)，并转换为要转换为的时间单位
	 *
	 * @param key 键 
	 * @param timeUnit 要转换为的时间单位
	 * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key 的剩余生存时间。
	 * @see <a href="http://redis.io/commands/pttl">Redis Documentation: PTTL</a>
	 */
	Long pTtl(byte[] key, TimeUnit timeUnit);

	/**
	 * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。
	 * 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
	 * 最简单的 SORT 使用方法是 SORT key 和 SORT key DESC ：SORT key 返回键值从小到大排序的结果。SORT key DESC 返回键值从大到小排序的结果。
	 *
	 * @param key 键 
	 * @param params 升序或者降序
	 * @return 经过排序的元素。
	 * @see <a href="http://redis.io/commands/sort">Redis Documentation: SORT</a>
	 */
	List<byte[]> sort(byte[] key, SortParameters params);

	/**
	 * 返回或保存给定列表、集合、有序集合 key 中经过排序的元素。
	 * 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较。
	 * 最简单的 SORT 使用方法是 SORT key 和 SORT key DESC ：SORT key 返回键值从小到大排序的结果。SORT key DESC 返回键值从大到小排序的结果。
	 *
	 * @param key 键 
	 * @param params 升序或者降序
	 * @param storeKey 排序后结果的保存key
	 * @return 值的个数
	 * @see <a href="http://redis.io/commands/sort">Redis Documentation: SORT</a>
	 */
	Long sort(byte[] key, SortParameters params, byte[] storeKey);

	/**
	 * 序列化给定 key ，并返回被序列化的值，使用 RESTORE 命令可以将这个值反序列化为 Redis 键。
	 * 序列化生成的值有以下几个特点：
	 * 它带有 64 位的校验和，用于检测错误， RESTORE 在进行反序列化之前会先检查校验和。
	 * 值的编码格式和 RDB 文件保持一致。
	 * RDB 版本会被编码在序列化值当中，如果因为 Redis 的版本不同造成 RDB 格式不兼容，那么 Redis 会拒绝对这个值进行反序列化操作。
	 * 序列化的值不包括任何生存时间信息。
	 *
	 * @param key 键 
	 * @return 如果 key 不存在，那么返回 nil 。否则，返回序列化之后的值。
	 * @see <a href="http://redis.io/commands/dump">Redis Documentation: DUMP</a>
	 */
	byte[] dump(byte[] key);

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联。
	 * 参数 ttl 以毫秒为单位为 key 设置生存时间；如果 ttl 为 0 ，那么不设置生存时间。
	 * RESTORE 在执行反序列化之前会先对序列化值的 RDB 版本和数据校验和进行检查，如果 RDB 版本不相同或者数据不完整的话，那么 RESTORE 会拒绝进行反序列化，并返回一个错误。
	 * @param key 键 
	 * @param ttlInMillis 以毫秒为单位的过期时间
	 * @param serializedValue 序列化后的值
	 * @see <a href="http://redis.io/commands/restore">Redis Documentation: RESTORE</a>
	 */
	void restore(byte[] key, long ttlInMillis, byte[] serializedValue);
}