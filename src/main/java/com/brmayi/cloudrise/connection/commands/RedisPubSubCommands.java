package com.brmayi.cloudrise.connection.commands;

import javax.jms.MessageListener;

import com.brmayi.cloudrise.connection.Subscription;

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
 * 功能：提供对Redis的发布订阅操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令
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
public interface RedisPubSubCommands {

	/**
	 * 显示是否当前连接至少有一个订阅
	 * 
	 * @return 连接被订阅为true
	 */
	boolean isSubscribed();

	/**
	 * 返回当前连接的订阅，没有订阅返回null
	 * 
	 * @return 当前订阅，没被订阅返回null
	 */
	Subscription getSubscription();

	/**
	 * 将信息 message 发送到指定的频道 channel 。
	 * 时间复杂度：O(N+M)，其中 N 是频道 channel 的订阅者数量，而 M 则是使用模式订阅(subscribed patterns)的客户端的数量。
	 * @param channel 频道
	 * @param message 信息
	 * @return 接收到信息 message 的订阅者数量。
	 * @see <a href="http://redis.io/commands/publish">Redis Documentation: PUBLISH</a>
	 */
	Long publish(byte[] channel, byte[] message);

	/**
	 * 订阅给定的一个或多个频道的信息。
	 * 一旦订阅，连接进入监听模式，只能订阅其他频道或者取消，不接收其他命令
	 * <p>
	 * 这是一个阻塞操作，将立即开始监听消息
	 * 
	 * @param listener 消息监听器
	 * @param channels 频道
	 * @see <a href="http://redis.io/commands/subscribe">Redis Documentation: SUBSCRIBE</a>
	 */
	void subscribe(MessageListener listener, byte[]... channels);

	/**
	 * 订阅一个或多个符合给定模式的频道。
	 * 每个模式以 * 作为匹配符，比如 it* 匹配所有以 it 开头的频道( it.news 、 it.blog 、 it.tweets 等等)，
	 *  news.* 匹配所有以 news. 开头的频道( news.it 、 news.global.today 等等)，诸如此类。
	 * 一旦订阅，连接进入监听模式，只能订阅其他频道或者取消，不接收其他命令
	 * <p>
	 * 这是一个阻塞操作，将立即开始监听消息
	 * 
	 * @param listener 消息监听器
	 * @param channels 频道
	 * @see <a href="http://redis.io/commands/psubscribe">Redis Documentation: PSUBSCRIBE</a>
	 */
	void pSubscribe(MessageListener listener, byte[]... patterns);
}
