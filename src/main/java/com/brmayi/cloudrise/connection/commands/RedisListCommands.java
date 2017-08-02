package com.brmayi.cloudrise.connection.commands;

import java.util.List;

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
 * 功能：提供对Redis的list操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令
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
public interface RedisListCommands {

	/**
	 * 列出插入位置
	 */
	public enum Position {
		BEFORE, AFTER
	}

	/**
	 * 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
	 * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表尾：比如对一个空列表 mylist 执行 RPUSH mylist a b c ，
	 * 得出的结果列表为 a b c ，等同于执行命令 RPUSH mylist a 、 RPUSH mylist b 、 RPUSH mylist c 。
	 * 如果 key 不存在，一个空列表会被创建并执行 RPUSH 操作。
	 * 当 key 存在但不是列表类型时，返回一个错误。
	 *
	 * @param key 键
	 * @param values 值 
	 * @return 执行 命令后，列表的长度。
	 * @see <a href="http://redis.io/commands/rpush">Redis Documentation: RPUSH</a>
	 */
	Long rPush(byte[] key, byte[]... values);

	/**
	 * 将一个或多个值 value 插入到列表 key 的表头
	 * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，
	 * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
	 * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
	 * 当 key 存在但不是列表类型时，返回一个错误。
	 *
	 * @param key 键
	 * @param values 值 
	 * @return 执行 LPUSH 命令后，列表的长度。
	 * @see <a href="http://redis.io/commands/lpush">Redis Documentation: LPUSH</a>
	 */
	Long lPush(byte[] key, byte[]... values);

	/**
	 * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
	 * 和 RPUSH 命令相反，当 key 不存在时， RPUSHX 命令什么也不做。
	 *
	 * @param key 键
	 * @param values 值 
	 * @return 执行 命令后，列表的长度。
	 * @see <a href="http://redis.io/commands/rpushx">Redis Documentation: RPUSHX</a>
	 */
	Long rPushX(byte[] key, byte[] value);

	/**
	 * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
	 * 和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
	 *
	 * @param key 键
	 * @param values 值 
	 * @return 执行 命令后，列表的长度。
	 * @see <a href="http://redis.io/commands/lpushx">Redis Documentation: LPUSHX</a>
	 */
	Long lPushX(byte[] key, byte[] value);

	/**
	 * 返回列表 key 的长度。
	 * 如果 key 不存在，则 key 被解释为一个空列表，返回 0 .
	 * 如果 key 不是列表类型，返回一个错误。
	 *
	 * @param key 键
	 * @return 返回列表 key 的长度
	 * @see <a href="http://redis.io/commands/llen">Redis Documentation: LLEN</a>
	 */
	Long lLen(byte[] key);

	/**
	 *返回列表 key 中指定区间内的元素，区间以偏移量 start 和 end 指定。
	 *下标(index)参数 start 和 end 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
	 *你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
	 *
	 * @param key 键
	 * @param start 开始
	 * @param end 结束
	 * @return 返回列表 key 中指定区间内的元素
	 * @see <a href="http://redis.io/commands/lrange">Redis Documentation: LRANGE</a>
	 */
	List<byte[]> lRange(byte[] key, long start, long end);

	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
	 * 举个例子，执行命令 LTRIM list 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。
	 * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
	 * 当 key 不是列表类型时，返回一个错误。
	 *
	 * @param key 键
	 * @param start 开始
	 * @param end 结束
	 * @see <a href="http://redis.io/commands/ltrim">Redis Documentation: LTRIM</a>
	 */
	void lTrim(byte[] key, long start, long end);

	/**
	 * 返回列表 key 中，下标为 index 的元素。
	 * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
	 * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
	 * 如果 key 不是列表类型，返回一个错误。
	 *
	 * @param key 键
	 * @param index 下标
	 * @return 返回列表 key 中，下标为 index 的元素
	 * @see <a href="http://redis.io/commands/lindex">Redis Documentation: LINDEX</a>
	 */
	byte[] lIndex(byte[] key, long index);

	/**
	 * Redis LINSERT命令插入值在存储在key之前或参考值支点后。如果key不存在，它被认为是一个空列表，并没有进行任何操作。当存在key但不持有列表值，则返回一个错误。
	 *
	 * @param key 键
	 * @param where 前或后
	 * @param pivot 参考值支点
	 * @param value 值
	 * @return 回复整数，列表插入操作后的长度，或-1时没有找到该值枢轴。
	 * @see <a href="http://redis.io/commands/linsert">Redis Documentation: LINSERT</a>
	 */
	Long lInsert(byte[] key, Position where, byte[] pivot, byte[] value);

	/**
	 * 将列表 key 下标为 index 的元素的值设置为 value 。
	 * 当 index 参数超出范围，或对一个空列表( key 不存在)进行 LSET 时，返回一个错误。
	 * 关于列表下标的更多信息，请参考 LINDEX 命令。
	 *
	 * @param key 键
	 * @param index 下标
	 * @param value 值
	 * @see <a href="http://redis.io/commands/lset">Redis Documentation: LSET</a>
	 */
	void lSet(byte[] key, long index, byte[] value);

	/**
	 * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
	 *
	 * @param key 键
	 * @param count count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。count = 0 : 移除表中所有与 value 相等的值。
	 * @param value 值
	 * @return 被移除元素的数量。因为不存在的 key 被视作空表(empty list)，所以当 key 不存在时， LREM 命令总是返回 0 。
	 * @see <a href="http://redis.io/commands/lrem">Redis Documentation: LREM</a>
	 */
	Long lRem(byte[] key, long count, byte[] value);

	/**
	 * 移除并返回列表 key 的头元素。
	 *
	 * @param key 键
	 * @return 列表的头元素。当 key 不存在时，返回 nil 。
	 * @see <a href="http://redis.io/commands/lpop">Redis Documentation: LPOP</a>
	 */
	byte[] lPop(byte[] key);

	/**
	 * 移除并返回列表 key 的尾元素。
	 *
	 * @param key 键
	 * @return 列表的尾元素。当 key 不存在时，返回 nil 。
	 * @see <a href="http://redis.io/commands/rpop">Redis Documentation: RPOP</a>
	 */
	byte[] rPop(byte[] key);

	/**
	 * BLPOP 是列表的阻塞式(blocking)弹出原语。
	 * 它是 LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，连接将被 BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
	 * 当给定多个 key 参数时，按参数 key 的先后顺序依次检查各个列表，弹出第一个非空列表的头元素。
	 * 非阻塞行为
	 * 当 BLPOP 被调用时，如果给定 key 内至少有一个非空列表，那么弹出遇到的第一个非空列表的头元素，并和被弹出元素所属的列表的名字一起，组成结果返回给调用者。
	 * 当存在多个给定 key 时， BLPOP 按给定 key 参数排列的先后顺序，依次检查各个列表。
	 * 假设现在有 job 、 command 和 request 三个列表，其中 job 不存在， command 和 request 都持有非空列表。考虑以下命令：
	 * BLPOP job command request 0
	 * BLPOP 保证返回的元素来自 command ，因为它是按”查找 job -> 查找 command -> 查找 request “这样的顺序，第一个找到的非空列表。
	 * 阻塞行为
	 * 如果所有给定 key 都不存在或包含空列表，那么 BLPOP 命令将阻塞连接，直到等待超时，或有另一个客户端对给定 key 的任意一个执行 LPUSH 或 RPUSH 命令为止。
	 * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。
	 * 相同的key被多个客户端同时阻塞
	 * 相同的 key 可以被多个客户端同时阻塞。
	 * 不同的客户端被放进一个队列中，按『先阻塞先服务』(first-BLPOP，first-served)的顺序为 key 执行 BLPOP 命令。
	 * 在MULTI/EXEC事务中的BLPOP
	 * BLPOP 可以用于流水线(pipline,批量地发送多个命令并读入多个回复)，但把它用在 MULTI / EXEC 块当中没有意义。因为这要求整个服务器被阻塞以保证块执行时的原子性，该行为阻止了其他客户端执行 LPUSH 或 RPUSH 命令。
	 * 因此，一个被包裹在 MULTI / EXEC 块内的 BLPOP 命令，行为表现得就像 LPOP 一样，对空列表返回 nil ，对非空列表弹出列表元素，不进行任何阻塞操作。
	 * 
	 * @param timeout 超时时间
	 * @param keys 键
	 * @return 阻塞弹出的元素
	 * @see <a href="http://redis.io/commands/blpop">Redis Documentation: BLPOP</a>
	 * @see #lPop(byte[])
	 */
	List<byte[]> bLPop(int timeout, byte[]... keys);

	/**
	 * BRPOP 是一个阻塞的列表弹出原语。 它是 RPOP 的阻塞版本，因为这个命令会在给定list无法弹出任何元素的时候阻塞连接。 该命令会按照给出的 key 顺序查看 list，并在找到的第一个非空 list 的尾部弹出一个元素。
	 * 请在 BLPOP 文档 中查看该命令的准确语义，因为 BRPOP 和 BLPOP 基本是完全一样的，除了它们一个是从尾部弹出元素，而另一个是从头部弹出元素。
	 * 
	 * @param timeout 超时时间
	 * @param keys 键
	 * @return 阻塞弹出的元素
	 * @see <a href="http://redis.io/commands/brpop">Redis Documentation: BRPOP</a>
	 * @see #rPop(byte[])
	 */
	List<byte[]> bRPop(int timeout, byte[]... keys);

	/**
	 * 命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作：
	 * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端。
	 * 将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素。
	 * 举个例子，你有两个列表 source 和 destination ， source 列表有元素 a, b, c ， destination 列表有元素 x, y, z ，
	 * 执行 RPOPLPUSH source destination 之后， source 列表包含元素 a, b ， destination 列表包含元素 c, x, y, z ，并且元素 c 会被返回给客户端。
	 * 如果 source 不存在，值 nil 被返回，并且不执行其他动作。
	 * 如果 source 和 destination 相同，则列表中的表尾元素被移动到表头，并返回该元素，可以把这种特殊情况视作列表的旋转(rotation)操作。
	 *
	 * @param srcKey 要弹出最后一个元素的key
	 * @param dstKey 要被插入到第一个元素的key
	 * @return 被弹出的元素。
	 * @see <a href="http://redis.io/commands/rpoplpush">Redis Documentation: RPOPLPUSH</a>
	 */
	byte[] rPopLPush(byte[] srcKey, byte[] dstKey);

	/**
	 * BRPOPLPUSH 是 RPOPLPUSH 的阻塞版本，当给定列表 source 不为空时， BRPOPLPUSH 的表现和 RPOPLPUSH 一样。
	 * 当列表 source 为空时， BRPOPLPUSH 命令将阻塞连接，直到等待超时，或有另一个客户端对 source 执行 LPUSH 或 RPUSH 命令为止。
	 * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。
	 * 更多相关信息，请参考 RPOPLPUSH 命令。
	 * 
	 * <br>
	 * <b>Blocks connection</b> until element available or {@code timeout} reached.
	 *
	 * @param timeout 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely) 。
	 * @param srcKey 要弹出最后一个元素的key
	 * @param dstKey 要被插入到第一个元素的key
	 * @return 假如在指定时间内没有任何元素被弹出，则返回一个 nil 和等待时长。反之，返回一个含有两个元素的列表，第一个元素是被弹出元素的值，第二个元素是等待时长。
	 * @see <a href="http://redis.io/commands/brpoplpush">Redis Documentation: BRPOPLPUSH</a>
	 * @see #rPopLPush(byte[], byte[])
	 */
	byte[] bRPopLPush(int timeout, byte[] srcKey, byte[] dstKey);
}
