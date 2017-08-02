package com.brmayi.cloudrise.connection.commands;

import java.util.Set;

import org.springframework.util.Assert;

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
 * 支持：jdk1.7及以上 redis2.8.0及以上
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
public interface RedisZSetCommands {
	/**
	 * 添加到一个有序Set中，如果已存在则更新其评分
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
	 * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
	 * score 值可以是整数值或双精度浮点数。
	 * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
	 * 当 key 存在但不是有序集类型时，返回一个错误。
	 * 对有序集的更多介绍请参见 sorted set 。
	 *  时间复杂度:O(M*log(N))， N 是有序集的基数， M 为成功添加的新成员的数量。
	 * 在 Redis 2.4 版本以前， ZADD 每次只能添加一个元素。
	 * @param key 键
	 * @param score 用以排序的评分
	 * @param member 成员
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
	 */
	Boolean zAdd(byte[] key, double score, byte[] member);
	
	/**
	 * 添加到一个有序Set中，如果已存在则更新其评分
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
	 * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
	 * score 值可以是整数值或双精度浮点数。
	 * 如果 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
	 * 当 key 存在但不是有序集类型时，返回一个错误。
	 * 对有序集的更多介绍请参见 sorted set 。
	 *  时间复杂度:O(M*log(N))， N 是有序集的基数， M 为成功添加的新成员的数量。
	 * 在 Redis 2.4 版本以前， ZADD 每次只能添加一个元素。
	 * @param key 键
	 * @param tuples 成员元组
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
	 */
	Long zAdd(byte[] key, Set<Tuple> tuples);

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。当 key 存在但不是有序集类型时，返回一个错误。
	 * 时间复杂度:O(M*log(N))， N 为有序集的基数， M 为被成功移除的成员的数量。
	 * @param key 键
	 * @param values 有序集 key 中的一个或多个成员
	 * @return 被成功移除的成员的数量，不包括被忽略的成员。
	 */
	Long zRem(byte[] key, byte[]... members);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment 。
	 * 可以通过传递一个负数值 increment ，让 score 减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
	 * 当 key 不存在，或 member 不是 key 的成员时， ZINCRBY key increment member 等同于 ZADD key increment member 。
	 * 当 key 不是有序集类型时，返回一个错误。
	 * score 值可以是整数值或双精度浮点数。
	 * @param key 有序集 key
	 * @param increment 增量
	 * @param member 有序集 key 的成员 member
	 * @return member 成员的新 score 值，以字符串形式表示。
	 */
	Double zIncrBy(byte[] key, double increment, byte[] member);

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大)顺序排列。
	 * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
	 * 使用 ZREVRANK 命令可以获得成员按 score 值递减(从大到小)排列的排名。
	 * 时间复杂度:O(log(N))
	 * @param key 有序集 key
	 * @param member 成员
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名。如果 member 不是有序集 key 的成员，返回 nil 。
	 */
	Long zRank(byte[] key, byte[] member);

	/**
	 * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小)排序。
	 * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
	 * 使用 ZRANK 命令可以获得成员按 score 值递增(从小到大)排列的排名。
	 * 时间复杂度:O(log(N))
	 * @param key 有序集 key
	 * @param member 成员
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名。如果 member 不是有序集 key 的成员，返回 nil 。
	 */
	Long zRevRank(byte[] key, byte[] member);

	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param start 开始
	 * @param end 结束
	 * @return 指定区间内，有序集成员的列表。
	 */
	Set<byte[]> zRange(byte[] key, long start, long end);

	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param start 开始
	 * @param end 结束
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRangeWithScores(byte[] key, long start, long end);

	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param start 开始
	 * @param end 结束
	 * @return 指定区间内，有序集成员的列表。
	 */
	Set<byte[]> zRangeByScore(byte[] key, long start, long end);

	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param start 开始
	 * @param end 结束
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRangeByScoreWithScores(byte[] key, long start, long end);
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRangeByScoreWithScores(byte[] key, double min, double max);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @return 指定区间内，有序集成员的列表。
	 */
	Set<byte[]> zRangeByScore(byte[] key, double min, double max);
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 偏移量
	 * @param count 取值数量
	 * @return 指定区间内，有序集成员的列表。
	 */
	Set<byte[]> zRangeByScore(byte[] key, double min, double max, long offset, long count);
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 偏移量
	 * @param count 取值数量
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRangeByScoreWithScores(byte[] key, double min, double max, long offset, long count);
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param range 范围
	 * @param count 上限
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRangeByScoreWithScores(byte[] key, Range range, Limit limit);

	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param start 开始
	 * @param end 结束
	 * @return 指定区间内，有序集成员的列表。
	 */
	Set<byte[]> zRevRange(byte[] key, long start, long end);

	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param start 开始
	 * @param end 结束
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRevRangeWithScores(byte[] key, long start, long end);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从小到大)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 如果你需要成员按 score 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<byte[]> zRevRangeByScore(byte[] key, double min, double max);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param range 范围
	 * @return 指定区间内，有序集成员的列表。
	 */
	Set<byte[]> zRevRangeByScore(byte[] key, Range range);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRevRangeByScoreWithScores(byte[] key, double min, double max);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 偏移量
	 * @param count 取值数量
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<byte[]> zRevRangeByScore(byte[] key, double min, double max, long offset, long count);

	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param range 范围
	 * @param count 上限
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<byte[]> zRevRangeByScore(byte[] key, Range range, Limit limit);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 偏移量
	 * @param count 取值数量
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRevRangeByScoreWithScores(byte[] key, double min, double max, long offset, long count);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
     * @param range 范围
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRevRangeByScoreWithScores(byte[] key, Range range);
	
	/**
	 * 返回有序集 key 中，指定区间内的成员。
	 * 其中成员的位置按 score 值递增(从大到小)来排序。
	 * 具有相同 score 值的成员按字典序(lexicographical order )来排列。
	 * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
	 * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
	 * 超出范围的下标并不会引起错误。
	 * 比如说，当 start 的值比有序集的最大下标还要大，或是 start > stop 时， ZRANGE 命令只是简单地返回一个空列表。
	 * 另一方面，假如 stop 参数的值比有序集的最大下标还要大，那么 Redis 将 stop 当作最大下标来处理。
	 * 可以通过使用 WITHSCORES 选项，来让成员和它的 score 值一并返回，返回列表以 value1,score1, ..., valueN,scoreN 的格式表示。
	 * 客户端库可能会返回一些更复杂的数据类型，比如数组、元组等。
	 * 时间复杂度:O(log(N)+M)， N 为有序集的基数，而 M 为结果集的基数。
	 * @param key 有序集 key
	 * @param range 范围
	 * @param count 上限
	 * @return 指定区间内，带有 score 值(可选)的有序集成员的列表。
	 */
	Set<Tuple> zRevRangeByScoreWithScores(byte[] key, Range range, Limit limit);
	
	/**
	 * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
	 * 关于参数 min 和 max 的详细使用方法，请参考 ZRANGEBYSCORE 命令。
	 * 时间复杂度:O(log(N))， N 为有序集的基数。
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @return score 值在 min 和 max 之间的成员的数量。
	 */
	Long zCount(byte[] key, double min, double max);
	
	/**
	 * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max )的成员的数量。
	 * 关于参数 min 和 max 的详细使用方法，请参考 ZRANGEBYSCORE 命令。
	 * 时间复杂度:O(log(N))， N 为有序集的基数。
	 * @param key 有序集 key
	 * @param range 范围
	 * @return score 值在 min 和 max 之间的成员的数量。
	 */
	Long zCount(byte[] key, Range range);
    
	/**
	 * Redis Zcard 命令用于计算集合中元素的数量。
	 * @param key 有序集 key
	 * @return
	 */
	Long zCard(byte[] key);

	/**
	 * Redis Zscore 命令返回有序集中，成员的分数值。 如果成员元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
	 * @param key 有序集 key
	 * @param member 成员
	 * @return 成员的分数值
	 */
	Double zScore(byte[] key, byte[] member);

	/**
	 * 删除有序集中从start到end的成员
	 * @param key 有序集 key
	 * @param start 开始
	 * @param end 结束
	 * @return 删除成员的分数值
	 */
	Long zRemRange(byte[] key, long start, long end);

	/**
	 * 删除有序集中从min到max的成员
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @return 删除成员的分数值
	 */
	Long zRemRangeByScore(byte[] key, double min, double max);

	/**
	 * 删除有序集中指定范围的成员
	 * @param key 有序集 key
	 * @param min 最小值
	 * @param max 最大值
	 * @return 删除成员的分数值
	 */
	Long zRemRangeByScore(byte[] key, Range range);

	/**
	 * Redis ZUNIONSTORE命令计算sets联合排序按指定键，并将结果存储在目的地。它是强制性的传递的输入键和其他(任选)参数之前，以提供输入键(sets)的数量。
	 * 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
	 * 时间复杂度:O(N*K)+O(M*log(M))， N 为给定 key 中基数最小的有序集， K 为给定有序集的数量， M 为结果集的基数。
	 * @param destKey 结果集存储的key
	 * @param sets 其中给定 key
	 * @return 返回整数，在目标中所得到的排序的集合中的元素的数量。
	 */
	Long zUnionStore(byte[] destKey, byte[]... sets);

	/**
	 * Redis ZUNIONSTORE命令计算sets联合排序按指定键，并将结果存储在目的地。它是强制性的传递的输入键和其他(任选)参数之前，以提供输入键(sets)的数量。
	 * @param destKey 结果集存储的key
	 * @param aggregate 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
	 * @param weights 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
	 * @param sets 其中给定 key
	 * @return 返回整数，在目标中所得到的排序的集合中的元素的数量。
	 */
	Long zUnionStore(byte[] destKey, Aggregate aggregate, int[] weights, byte[]... sets);

	/**
	 * 计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 sets 参数指定，并将该交集(结果集)储存到 destKey 。
	 * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之和.
	 * 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
	 * 时间复杂度:O(N*K)+O(M*log(M))， N 为给定 key 中基数最小的有序集， K 为给定有序集的数量， M 为结果集的基数。
	 * @param destKey 结果集存储的key
	 * @param sets 其中给定 key
	 * @return 保存到 destination 的结果集的基数。
	 */
	Long zInterStore(byte[] destKey, byte[]... sets);

	/**
	 * 计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 sets 参数指定，并将该交集(结果集)储存到 destKey 。
	 * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之和.
	 * 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
	 * 时间复杂度:O(N*K)+O(M*log(M))， N 为给定 key 中基数最小的有序集， K 为给定有序集的数量， M 为结果集的基数。
	 * @param destKey 结果集存储的key
	 * @param aggregate 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
	 * @param weights 关于 WEIGHTS 和 AGGREGATE 选项的描述，参见 ZUNIONSTORE 命令。
	 * @param sets 其中给定 key
	 * @return 保存到 destination 的结果集的基数。
	 */
	Long zInterStore(byte[] destKey, Aggregate aggregate, int[] weights, byte[]... sets);

	/**
	 * Redis Zscan 命令用于迭代有序集合中的元素（包括元素成员和元素分值）
	 * @param key 有序集合
	 * @param options 增量式迭代命令选项
	 * @return 返回的每个元素都是一个有序集合元素，一个有序集合元素由一个成员（member）和一个分值（score）组成。
	 */
	Cursor<Tuple> zScan(byte[] key, ScanOptions options);

	/**
	 * 取得在min和max之间的成员
	 * @param key 有序集合
	 * @param min 最小值
	 * @param max 最大值
	 * @return 在min和max之间的成员
	 */
	Set<byte[]> zRangeByScore(byte[] key, String min, String max);

	/**
	 * 取得在 range min和range max之间的成员
	 * @param key 有序集合
	 * @param range 范围
	 * @return 在min和max之间的成员
	 */
	Set<byte[]> zRangeByScore(byte[] key, Range range);


	/**
	 * 取得在min和max之间从offset开始count个成员
	 * @param key 有序集合
	 * @param min 最小值
	 * @param max 最大值
	 * @param offset 偏移量
	 * @param count 上限
	 * @return 在min和max之间的成员
	 */
	Set<byte[]> zRangeByScore(byte[] key, String min, String max, long offset, long count);

	/**
	 * 取得在range min和range max之间limit个成员
	 * @param key 有序集合
	 * @param range 范围
	 * @param limit 上限
	 * @return
	 */
	Set<byte[]> zRangeByScore(byte[] key, Range range, Limit limit);

	/**
	 * Redis Zrangebylex 通过字典区间返回有序集合的成员。
	 * @param key 有序集合
	 * @return 成员
	 */
	Set<byte[]> zRangeByLex(byte[] key);

	/**
	 * Redis Zrangebylex 通过字典区间返回有序集合的成员。
	 * @param key 有序集合
	 * @param range 范围
	 * @return 成员
	 */
	Set<byte[]> zRangeByLex(byte[] key, Range range);

	/**
	 * Redis Zrangebylex 通过字典区间返回有序集合的成员。
	 * @param key 有序集合
	 * @param range 范围
	 * @param limit 上限
	 * @return 成员
	 */
	Set<byte[]> zRangeByLex(byte[] key, Range range, Limit limit);

	public static class Limit {
		int offset;
		int count;

		static Limit limit() {
			return new Limit();
		}

		Limit offset(int offset) {
			this.offset = offset;
			return this;
		}

		Limit count(int count) {
			this.count = count;
			return this;
		}

		int getCount() {
			return this.count;
		}

		int getOffset() {
			return this.offset;
		}
	}

	public static class Range {
		Boundary min;
		Boundary max;

		static Range range() {
			return new Range();
		}

		static Range unbounded() {
			Range range = new Range();
			range.min = Boundary.infinite();
			range.max = Boundary.infinite();
			return range;
		}

		Range gte(Object min) {
			Assert.notNull(min, "Min already set for range.");
			this.min = new Boundary(min, true);
			return this;
		}

		Range gt(Object min) {
			Assert.notNull(min, "Min already set for range.");
			this.min = new Boundary(min, false);
			return this;
		}

		Range lte(Object max) {
			Assert.notNull(max, "Max already set for range.");
			this.max = new Boundary(max, true);
			return this;
		}

		Range lt(Object max) {
			Assert.notNull(max, "Max already set for range.");
			this.max = new Boundary(max, false);
			return this;
		}

		Boundary getMin() {
			return this.min;
		}

		Boundary getMax() {
			return this.max;
		}

		static class Boundary {
			Object value;
			boolean including;

			static Boundary infinite() {
				return new Boundary(null, true);
			}

			Boundary(Object value, boolean including) {
				this.value = value;
				this.including = including;
			}

			Object getValue() {
				return this.value;
			}

			boolean isIncluding() {
				return this.including;
			}
		}
	}

	public static interface Tuple extends Comparable<Double> {
		byte[] getValue();

		Double getScore();
	}

	static enum Aggregate {
		SUM, MIN, MAX;
	}
}