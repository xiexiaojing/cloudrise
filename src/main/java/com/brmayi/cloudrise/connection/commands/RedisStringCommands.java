package com.brmayi.cloudrise.connection.commands;

import java.util.List;
import java.util.Map;

import com.brmayi.cloudrise.core.types.Expiration;
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
 * 功能：提供对Redis的操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令
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
public interface RedisStringCommands {
	/**
	 * 通过键取值操作
	 * @param key 键
	 * @return 值
	 */
	public byte[] get(byte[] key);

	/**
	 * 设置新值，并返回老值
	 * @param key 键
	 * @param value 设置的新值
	 * @return 设置前的老值
	 */
	public byte[] getSet(byte[] key, byte[] value);

	/**
	 * 多键取值，通过管道操作提高效率
	 * @param keys 键
	 * @return 值列表
	 */
	public List<byte[]> mGet(byte[]... keys);

	/**
	 * 赋值操作
	 * @param key 键
	 * @param value 值
	 */
	public void set(byte[] key, byte[] value);

	/**
	 * 如果赋值和插入更新值取决于一个选项时应用超时机制赋值
	 * @param key 键
	 * @param value 值
	 * @param expiration 超时机制
	 * @param option 操作选项
	 */
	public void set(byte[] key, byte[] value, Expiration expiration, RedisStringCommands.SetOption option);

	/**
	 * 在此键不存之时设置此值
	 * @param key 键
	 * @param value 值
	 * @return 是否成功
	 */
	public Boolean setNX(byte[] key, byte[] value);

	/**
	 * 设置值和以秒为单位的超时时间
	 * @param key 键
	 * @param seconds 以秒为单位的超时时间
	 * @param value 值
	 */
	public void setEx(byte[] key, long seconds, byte[] value);

	/**
	 * 设置值和以微秒为单位的超时时间
	 * @param key 键
	 * @param milliseconds 以微秒为单位的超时时间
	 * @param value 值
	 */
	public void pSetEx(byte[] key, long milliseconds, byte[] value);

	/**
	 * 在此键不存之时以元组方式用键值对来多个键设置多个值
	 * @param tuple 键值对元组
	 */
	public void mSet(Map<byte[], byte[]> tuple);

	/**
	 * 以元组方式用键值对来多个键设置多个值
	 * @param tuple 键值对元组
	 */
	public Boolean mSetNX(Map<byte[],byte[]> tuple);

	/**
	 * 对于一个字符串形式存储的整型加1操作
	 * @param key 键
	 * @return 操作后的值
	 */
	public Long incr(byte[] key);

	/**
	 * 对于一个字符串形式存储的整型加value操作
	 * @param key 键
	 * @param value 要增加的差值
	 * @return 操作后的值
	 */
	public Long incrBy(byte[] key,long value);
	
	/**
	 * 对于一个字符串形式存储的浮点型加value操作
	 * @param key 键
	 * @param value 要增加的差值
	 * @return 操作后的值
	 */
	public Double incrBy(byte[] key, double value);
	
	/**
	 * 对于一个字符串形式存储的整型减1操作
	 * @param key 键
	 * @return 操作后的值
	 */
	public abstract Long decr(byte[] key);

	/**
	 * 对于一个字符串形式存储的整型减value操作
	 * @param key 键
	 * @param value 要减的差值
	 * @return 操作后的值
	 */
	public Long decrBy(byte[] key, long value);

	/**
	 * 用于在键中添加一些值
	 * @param key 键
	 * @param value 要添加的值
	 * @return 在追加操作后的字符串的长度
	 */
	public Long append(byte[] key, byte[] value);
	
	/**
	 * 用于获取存储在键的字符串值的子字符串，由偏移量的开始和结束(两者都包括)确定。 可以使用负偏移，以便从字符串的末尾开始计算偏移。
	 * @param key 键
	 * @param begin 开始
	 * @param end 结束
	 * @return 存储在键的字符串值的子字符串
	 */
	public byte[] getRange(byte[] key, long begin, long end);

	/**
	 * 用于覆盖键的值，从指定偏移处开始的一部分字符串。
	 * @param key 键
	 * @param value 新值
	 * @param offset 指定偏移处
	 */
	public void setRange(byte[] key, byte[] value, long offset);

	/**
	 * 用于获取存储在键处的字符串值中的偏移处的位值。
	 * @param key 键
	 * @param offset 指定偏移处
	 * @return 取得到的比特位
	 */
	public Boolean getBit(byte[] key, long offset);

	/**
	 * 设置在键处存储的字符串值中偏移处的位值。
	 * @param key 键
	 * @param offset 指定偏移处
	 * @param value 比特位
	 * @return 存储在偏移量的旧位值
	 */
	public Boolean setBit(byte[] key, long offset, boolean value);

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量。
	 * Bitmap 对于一些特定类型的计算非常有效。
	 * 假设现在我们希望记录自己网站上的用户的上线频率，比如说，计算用户 A 上线了多少天，用户 B 上线了多少天，诸如此类，以此作为数据，从而决定让哪些用户参加 beta 测试等活动 —— 这个模式可以使用 SETBIT 和 BITCOUNT 来实现。
	 * 比如说，每当用户在某一天上线的时候，我们就使用 SETBIT ，以用户名作为 key ，将那天所代表的网站的上线日作为 offset 参数，并将这个 offset 上的为设置为 1 。
	 * 举个例子，如果今天是网站上线的第 100 天，而用户 peter 在今天阅览过网站，那么执行命令 SETBIT peter 100 1 ；如果明天 peter 也继续阅览网站，那么执行命令 SETBIT peter 101 1 ，以此类推。
	 * 当要计算 peter 总共以来的上线次数时，就使用 BITCOUNT 命令：执行 BITCOUNT peter ，得出的结果就是 peter 上线的总天数。
	 * @param key 键
	 * @return 被设置为 1 的位的数量。
	 */
	public Long bitCount(byte[] key);

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量。
	 * @param key 键
	 * @param begin 开始
	 * @param end 结束
	 * @return 被设置为 1 的位的数量。
	 */
	public Long bitCount(byte[] key, long begin, long end);

	/**
	 * 对一个或多个保存二进制位的字符串 key 进行位元操作，并将结果保存到 destination上。
	 * BITOP 的复杂度为 O(N) ，当处理大型矩阵(matrix)或者进行大数据量的统计时，最好将任务指派到附属节点(slave)进行，避免阻塞主节点。
	 * @param op 元操作
	 * @param destination 目标键
	 * @param keys 要操作的key
	 * @return 保存到 destination 的字符串的长度，和输入 key 中最长的字符串长度相等。
	 */
	public Long bitOp(RedisStringCommands.BitOperation op, byte[] destination, byte[]... keys);

	/**
	 * 返回 key 所储存的字符串值的长度。
	 * 当 key 储存的不是字符串值时，返回一个错误。复杂度：O(1)
	 * @param key 键
	 * @return 字符串值的长度。当 key 不存在时，返回 0 。
	 */
	public Long strLen(byte[] key);

	public static enum SetOption {
		UPSERT, SET_IF_ABSENT, SET_IF_PRESENT;

		public static SetOption upsert() {
			return UPSERT;
		}

		public static SetOption ifPresent() {
			return SET_IF_PRESENT;
		}

		public static SetOption ifAbsent() {
			return SET_IF_ABSENT;
		}
	}

	public static enum BitOperation {
		AND, OR, XOR, NOT;
	}
}