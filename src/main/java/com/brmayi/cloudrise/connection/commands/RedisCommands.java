package com.brmayi.cloudrise.connection.commands;

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
 * 功能：提供对Redis命令统计执行接口
 * 编码：静儿(xiexiaojing@qq.com)
 * 时间: 2017.08.01
 * *********************************************************************************************************
 * 修改历史
 * *********************************************************************************************************
 * 修改者                            									           修改内容                      修改时间 
 * 静儿(987489055@qq.com)                            新建                             2017.08.01
 * *********************************************************************************************************
 * </pre>
 */
public interface RedisCommands extends RedisKeyCommands, RedisStringCommands, RedisListCommands, RedisSetCommands, RedisZSetCommands,
		RedisHashCommands, RedisTxCommands, RedisPubSubCommands, RedisConnectionCommands, RedisServerCommands {

	/**
	 * 本地化的或者原生的执行redis命令和参数，
	 * 
	 * @param command
	 *            命令
	 * @param args
	 *            可能的参数，可能为空
	 * @return 执行结果
	 */
	Object execute(String command, byte[]... args);
}
