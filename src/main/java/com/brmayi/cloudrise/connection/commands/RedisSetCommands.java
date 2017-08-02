package com.brmayi.cloudrise.connection.commands;

import java.util.List;
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
 * 功能：提供对Redis的set操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令
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
public interface RedisSetCommands {

	/**
	 * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
	 * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
	 * 当 key 不是集合类型时，返回一个错误。
	 *
	 * @param key must not be {@literal null}.
	 * @param values member 元素
	 * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
	 * @see <a href="http://redis.io/commands/sadd">Redis Documentation: SADD</a>
	 */
	Long sAdd(byte[] key, byte[]... values);

	/**
	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
	 * 当 key 不是集合类型，返回一个错误。
	 *
	 * @param key must not be {@literal null}.
	 * @param values member 元素
	 * @return 被成功移除的元素的数量，不包括被忽略的元素。
	 * @see <a href="http://redis.io/commands/srem">Redis Documentation: SREM</a>
	 */
	Long sRem(byte[] key, byte[]... values);

	/**
	 * 移除并返回集合中的一个随机元素。
	 * 如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用 SRANDMEMBER 命令。
	 *
	 * @param key must not be {@literal null}.
	 * @return 被移除的随机元素。当 key 不存在或 key 是空集时，返回 nil 。
	 * @see <a href="http://redis.io/commands/spop">Redis Documentation: SPOP</a>
	 */
	byte[] sPop(byte[] key);

	/**
	 * 移除并返回集合中的count个随机元素。
	 *
	 * @param key must not be {@literal null}.
	 * @param count number of random members to pop from the set.
	 * @return 被移除的随机元素。当 key 不存在或 key 是空集时，返回 nil 。
	 * @see <a href="http://redis.io/commands/spop">Redis Documentation: SPOP</a>
	 * @since 2.0
	 */
	List<byte[]> sPop(byte[] key, long count);

	/**
	 * 将 member 元素从 source 集合移动到 destination 集合。
	 * SMOVE 是原子性操作。
	 * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
	 * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
	 * 当 source 或 destination 不是集合类型时，返回一个错误。
	 *
	 * @param srcKey member 元素从 source 集合移动
	 * @param destKey destination 集合
	 * @param value member 元素
	 * @return
	 * @see <a href="http://redis.io/commands/smove">Redis Documentation: SMOVE</a>
	 */
	Boolean sMove(byte[] srcKey, byte[] destKey, byte[] value);

	/**
	 * 返回集合 key 的基数(集合中元素的数量)。
	 *
	 * @param key must not be {@literal null}.
	 * @return 集合的基数。当 key 不存在时，返回 0 。
	 * @see <a href="http://redis.io/commands/scard">Redis Documentation: SCARD</a>
	 */
	Long sCard(byte[] key);

	/**
	 * 判断 member 元素是否集合 key 的成员。
	 *
	 * @param key must not be {@literal null}.
	 * @param value  member 元素
	 * @return 如果 member 元素是集合的成员，返回 1 。如果 member 元素不是集合的成员，或 key 不存在，返回 0 。
	 * @see <a href="http://redis.io/commands/sismember">Redis Documentation: SISMEMBER</a>
	 */
	Boolean sIsMember(byte[] key, byte[] value);

	/**
	 * 判断 member 元素是否集合 key 的成员。
	 *
	 * @param keys must not be {@literal null}.
	 * @return 如果 member 元素是集合的成员，返回 1 。如果 member 元素不是集合的成员，或 key 不存在，返回 0 。
	 * @see <a href="http://redis.io/commands/sinter">Redis Documentation: SINTER</a>
	 */
	Set<byte[]> sInter(byte[]... keys);

	/**
	 * 这个命令类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
	 * 如果 destination 集合已经存在，则将其覆盖。
	 * destination 可以是 key 本身。
	 *
	 * @param destKey destination 集合 must not be {@literal null}.
	 * @param keys must not be {@literal null}.
	 * @return
	 * @see <a href="http://redis.io/commands/sinterstore">Redis Documentation: SINTERSTORE</a>
	 */
	Long sInterStore(byte[] destKey, byte[]... keys);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合的并集。不存在的 key 被视为空集。
	 *
	 * @param keys must not be {@literal null}.
	 * @return 并集成员的列表。
	 * @see <a href="http://redis.io/commands/sunion">Redis Documentation: SUNION</a>
	 */
	Set<byte[]> sUnion(byte[]... keys);

	/**
	 * 这个命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集。
	 * 如果 destination 已经存在，则将其覆盖。
	 * destination 可以是 key 本身。
	 *
	 * @param destKey must not be {@literal null}.
	 * @param keys must not be {@literal null}.
	 * @return 结果集中的元素数量。
	 * @see <a href="http://redis.io/commands/sunionstore">Redis Documentation: SUNIONSTORE</a>
	 */
	Long sUnionStore(byte[] destKey, byte[]... keys);

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。
	 * 不存在的 key 被视为空集。
	 *
	 * @param keys must not be {@literal null}.
	 * @return 交集成员的列表。
	 * @see <a href="http://redis.io/commands/sdiff">Redis Documentation: SDIFF</a>
	 */
	Set<byte[]> sDiff(byte[]... keys);

	/**
	 * 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地返回结果集。
	 * 如果 destination 集合已经存在，则将其覆盖。
	 * destination 可以是 key 本身。
	 *
	 * @param destKey must not be {@literal null}.
	 * @param keys must not be {@literal null}.
	 * @return 结果集中的元素数量。
	 * @see <a href="http://redis.io/commands/sdiffstore">Redis Documentation: SDIFFSTORE</a>
	 */
	Long sDiffStore(byte[] destKey, byte[]... keys);

	/**
	 * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
	 *
	 * @param key must not be {@literal null}.
	 * @return 集合中的所有成员。
	 * @see <a href="http://redis.io/commands/smembers">Redis Documentation: SMEMBERS</a>
	 */
	Set<byte[]> sMembers(byte[] key);

	/**
	 * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。
	 * 从 Redis 2.6 版本开始， SRANDMEMBER 命令接受可选的 count 参数：
	 * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。
	 * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。
	 * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。
	 *
	 * @param key must not be {@literal null}.
	 * @return 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组。
	 * @see <a href="http://redis.io/commands/srandmember">Redis Documentation: SRANDMEMBER</a>
	 */
	byte[] sRandMember(byte[] key);

	/**
	 * 如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。
	 * 从 Redis 2.6 版本开始， SRANDMEMBER 命令接受可选的 count 参数：
	 * 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。
	 * 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。
	 * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。
	 *
	 * @param key must not be {@literal null}.
	 * @param count 返回元素数量
	 * @return 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组。
	 * @see <a href="http://redis.io/commands/srandmember">Redis Documentation: SRANDMEMBER</a>
	 */
	List<byte[]> sRandMember(byte[] key, long count);

	/**
	 * SCAN 命令及其相关的 SSCAN 命令、 HSCAN 命令和 ZSCAN 命令都用于增量地迭代（incrementally iterate）一集元素（a collection of elements）：
	 * SCAN 命令用于迭代当前数据库中的数据库键。
	 * SSCAN 命令用于迭代集合键中的元素。
	 * HSCAN 命令用于迭代哈希键中的键值对。
	 * ZSCAN 命令用于迭代有序集合中的元素（包括元素成员和元素分值）。
	 * 以上列出的四个命令都支持增量式迭代， 它们每次执行都只会返回少量元素， 所以这些命令可以用于生产环境， 而不会出现像 KEYS 命令、 SMEMBERS 命令带来的问题 —— 
	 * 当 KEYS 命令被用于处理一个大的数据库时， 又或者 SMEMBERS 命令被用于处理一个大的集合键时， 它们可能会阻塞服务器达数秒之久。
	 * 不过， 增量式迭代命令也不是没有缺点的： 举个例子， 使用 SMEMBERS 命令可以返回集合键当前包含的所有元素， 但是对于 SCAN 这类增量式迭代命令来说， 因为在对键进行增量式迭代的过程中， 键可能会被修改， 所以增量式迭代命令只能对被返回的元素提供有限的保证 （offer limited guarantees about the returned elements）。
	 * 因为 SCAN 、 SSCAN 、 HSCAN 和 ZSCAN 四个命令的工作方式都非常相似， 所以这个文档会一并介绍这四个命令， 但是要记住：
	 * SSCAN 命令、 HSCAN 命令和 ZSCAN 命令的第一个参数总是一个数据库键。
	 * 而 SCAN 命令则不需要在第一个参数提供任何数据库键 —— 因为它迭代的是当前数据库中的所有数据库键。
	 *
	 * @param key must not be {@literal null}.
	 * @param options must not be {@literal null}.
	 * @return  SCAN 命令的回复是一个包含两个元素的数组， 第一个数组元素是用于进行下一次迭代的新游标， 而第二个数组元素则是一个数组， 这个数组中包含了所有被迭代的元素。
	 * @since 1.4
	 * @see <a href="http://redis.io/commands/scan">Redis Documentation: SSCAN</a>
	 */
	Cursor<byte[]> sScan(byte[] key, ScanOptions options);
}