package com.brmayi.cloudrise.connection.commands;

import java.util.Collection;

import com.brmayi.cloudrise.connection.NamedNode;
import com.brmayi.cloudrise.connection.RedisServer;

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
 * 功能：提供对Redis的哨兵操作抽象，类似于zookeeper的集群机制
 * 编码：静儿(xiexiaojing@qq.com)
 * 时间: 2017.08.02
 * *********************************************************************************************************
 * 修改历史
 * *********************************************************************************************************
 * 修改者                            									           修改内容                      修改时间 
 * 静儿(987489055@qq.com)                            新建                             2017.08.02
 * *********************************************************************************************************
 * </pre>
 */
public interface RedisSentinelCommands {

	/**
	 * 如果master不可用，则启动一次failover
	 * failover过程:
	 *  在Leader触发failover之前，首先wait数秒(随即0~5)，以便让其他sentinel实例准备和调整(有可能多个leader??),如果一切正常，那么leader就需要开始将一个salve提升为master，此slave必须为状态良好(不能处于SDOWN/ODOWN状态)且权重值最低(redis.conf中)的，当master身份被确认后，开始failover
	 *  A）“+failover-triggered”: Leader开始进行failover，此后紧跟着“+failover-state-wait-start”，wait数秒。
	 *  B）“+failover-state-select-slave”: Leader开始查找合适的slave
	 *  C）“+selected-slave”: 已经找到合适的slave
	 *  D） “+failover-state-sen-slaveof-noone”: Leader向slave发送“slaveof no one”指令，此时slave已经完成角色转换，此slave即为master
	 *  E） “+failover-state-wait-promotition”: 等待其他sentinel确认slave
	 *  F）“+promoted-slave”：确认成功
	 *  G）“+failover-state-reconf-slaves”: 开始对slaves进行reconfig操作。
	 *  H）“+slave-reconf-sent”:向指定的slave发送“slaveof”指令，告知此slave跟随新的master
	 *  I）“+slave-reconf-inprog”: 此slave正在执行slaveof + SYNC过程，如过slave收到“+slave-reconf-sent”之后将会执行slaveof操作。
	 *  J）“+slave-reconf-done”: 此slave同步完成，此后leader可以继续下一个slave的reconfig操作。循环G）
	 *  K）“+failover-end”: 故障转移结束
	 *  L）“+switch-master”：故障转移成功后，各个sentinel实例开始监控新的master。
	 * 
	 * @param master must not be {@literal null}.
	 */
	void failover(NamedNode master);

	/**
	 * 获取被监视的master及其状态
	 * 
	 * @return Collection of {@link RedisServer}s. Never {@literal null}.
	 */
	Collection<RedisServer> masters();

	/**
	 * 获取被master监视的slaves
	 * 
	 * @param master must not be {@literal null}.
	 * @return Collection of {@link RedisServer}s. Never {@literal null}.
	 */
	Collection<RedisServer> slaves(NamedNode master);

	/**
	 * 删除这台master，此master将不再受到监控
	 * 
	 * @param master must not be {@literal null}.
	 */
	void remove(NamedNode master);

	/**
	 * 告诉哨兵来开启一个新的master(包含IP，端口和getQuorum
	 * 
	 * @param master must not be {@literal null}.
	 */
	void monitor(RedisServer master);

}