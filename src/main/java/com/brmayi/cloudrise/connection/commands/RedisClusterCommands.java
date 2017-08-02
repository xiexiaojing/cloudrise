package com.brmayi.cloudrise.connection.commands;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.brmayi.cloudrise.connection.ClusterInfo;
import com.brmayi.cloudrise.connection.RedisClusterNode;
import com.brmayi.cloudrise.connection.RedisClusterNode.SlotRange;
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
 * 功能：提供对Redis的集群操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令
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
public interface RedisClusterCommands {

	/**
	 * 获取集群节点信息如id,host,port和slots
	 * 命令返回结果示例：
	 * <pre>
	 * 2fbaf010c5016f0f2ced27a59ce18d523626f6b2 10.127.95.184:6379 master - 0 1501574021059 4 connected 0-5460
	 * 1912ddc0efda6a717770fac0c04530a697094915 10.127.95.184:6380 master - 0 1501574018053 5 connected 10923-16383
	 * f0918af827d579b437047a6104073bb61a57ef6c 10.183.96.194:6381 slave 1912ddc0efda6a717770fac0c04530a697094915 0 1501574022060 5 connected
	 * 00935d600377ed74da9825b992295d1c811c1061 10.183.96.194:6379 myself,slave c20fcb762dfbe2678d4ac367f3fbc55a69a7ee69 0 0 1 connected
	 * 5eb34be9ec6d634345f708dba7654ba22dc8513c 10.183.96.194:6380 slave 2fbaf010c5016f0f2ced27a59ce18d523626f6b2 0 1501574020557 4 connected
	 * c20fcb762dfbe2678d4ac367f3fbc55a69a7ee69 10.127.95.184:6381 master - 0 1501574019055 7 connected 5461-10922
	 * </pre>
	 * 结果从左到右代表的含义是<id><ip:port><flags> <ping-sent><pong-recv><config-epoch> <link-state> <slot>
	 * @return 从不返回null
	 * @see <a href="https://redis.io/commands/cluster-nodes">Redis Documentation: CLUSTER NODES</a>
	 */
	Iterable<RedisClusterNode> clusterGetNodes();

	/**
	 * 获取指定master节点的slave节点信息
	 * 命令示例
	 * <pre>
	 * 10.183.96.194:6379> cluster slaves c20fcb762dfbe2678d4ac367f3fbc55a69a7ee69
	 * </pre>
	 * 返回结果示例
	 * <pre>
	 * 00935d600377ed74da9825b992295d1c811c1061 10.183.96.194:6379 myself,slave c20fcb762dfbe2678d4ac367f3fbc55a69a7ee69 0 0 1 connected
	 *  </pre>
	 *  结果从左到右代表的含义是<id><ip:port><flags> <ping-sent><pong-recv><config-epoch> <link-state>
	 * @param master 不能为空
	 * @return 从不返回null
	 * @see <a href="https://redis.io/commands/cluster-slaves">Redis Documentation: CLUSTER SLAVES</a>
	 */
	Collection<RedisClusterNode> clusterGetSlaves(RedisClusterNode master);

	/**
	 * 获取所有的master和对应的slave节点信息
	 * 
	 * @return 从不返回null
	 * @see <a href="https://redis.io/commands/cluster-slaves">Redis Documentation: CLUSTER SLAVES</a>
	 */
	Map<RedisClusterNode, Collection<RedisClusterNode>> clusterGetMasterSlaveMap();

	/**
	 * 获取指定key所在的slot槽
	 * 
	 * @param key 指定key
	 * @return 所在的slot槽
	 * @see <a href="https://redis.io/commands/cluster-keyslot">Redis Documentation: CLUSTER KEYSLOT</a>
	 */
	Integer clusterGetSlotForKey(byte[] key);

	/**
	 * 取得slot槽所在的节点
	 * 
	 * @param slot slot槽
	 * @return slot槽所在的节点
	 */
	RedisClusterNode clusterGetNodeForSlot(int slot);

	/**
	 * 取得key所在的节点
	 * 
	 * @param key 键
	 * @return 所在的节点
	 */
	RedisClusterNode clusterGetNodeForKey(byte[] key);

	/**
	 * 获取集群信息
	 * 命令结果示例
	 * <pre>
	 * cluster_state:ok
	 * cluster_slots_assigned:16384
	 * cluster_slots_ok:16384
	 * cluster_slots_pfail:0
	 * cluster_slots_fail:0
	 * cluster_known_nodes:6
	 * cluster_size:3
	 * cluster_current_epoch:7
	 * cluster_my_epoch:7
	 * cluster_stats_messages_sent:2784001
	 * cluster_stats_messages_received:2779119
	 * </pre>
	 * @return 集群信息
	 * @see <a href="https://redis.io/commands/cluster-info">Redis Documentation: CLUSTER INFO</a>
	 */
	ClusterInfo clusterGetClusterInfo();

	/**
	 * 指定一个slot槽到当前节点
	 * 
	 * @param node 节点不能为null
	 * @param slots 槽至少为1个
	 * @see <a href="https://redis.io/commands/cluster-addslots">Redis Documentation: CLUSTER ADDSLOTS</a>
	 */
	void clusterAddSlots(RedisClusterNode node, int... slots);

	/**
	 * 指定一个slot槽范围到当前节点
	 * 
	 * @param node 节点不能为null
	 * @param range 槽至少为1个
	 * @see <a href="https://redis.io/commands/cluster-addslots">Redis Documentation: CLUSTER ADDSLOTS</a>
	 */
	void clusterAddSlots(RedisClusterNode node, SlotRange range);

	/**
	 * 返回一个slot槽的key数量
	 * 
	 * @param slot slot槽
	 * @return 返回一个slot槽的key数量
	 * @see <a href="https://redis.io/commands/cluster-countkeysinslot">Redis Documentation: CLUSTER COUNTKEYSINSLOT</a>
	 */
	Long clusterCountKeysInSlot(int slot);

	/**
	 * 从指定节点中删除一些slot槽。请谨慎操作
	 * 
	 * @param node  节点不能为null
	 * @param slots 槽至少为1个
	 * @see <a href="https://redis.io/commands/cluster-delslots">Redis Documentation: CLUSTER DELSLOTS</a>
	 */
	void clusterDeleteSlots(RedisClusterNode node, int... slots);

	/**
	 *  从指定节点中删除一些slot槽。请谨慎操作
	 * 
	 * @param node 节点不能为null
	 * @param range 槽至少为1个
	 * @see <a href="https://redis.io/commands/cluster-delslots">Redis Documentation: CLUSTER DELSLOTS</a>
	 */
	void clusterDeleteSlotsInRange(RedisClusterNode node, SlotRange range);

	/**
	 * 删除指定节点
	 * 
	 * @param node 节点不能为null
	 * @see <a href="https://redis.io/commands/cluster-forget">Redis Documentation: CLUSTER FORGET</a>
	 */
	void clusterForget(RedisClusterNode node);

	/**
	 * 添加一个节点到集群
	 * 命令示例如下：
	 *  cluster meet 10.127.95.184 6379
	 * @param node 节点必须包含IP和端口
	 * @see <a href="https://redis.io/commands/cluster-meet">Redis Documentation: CLUSTER MEET</a>
	 */
	void clusterMeet(RedisClusterNode node);

	/**
	 * 转移slot槽到指定节点
	 * 命令示例如下：
	 * cluster setslot 16383 node 6f5cd78ee644c1df9756fc11b3595403f51216cc
	 * @param node must not be {@literal null}.
	 * @param slot 节点不能为null
	 * @param mode 模式不能为null
	 * @see <a href="https://redis.io/commands/cluster-setslot">Redis Documentation: CLUSTER SETSLOT</a>
	 */
	void clusterSetSlot(RedisClusterNode node, int slot, AddSlots mode);

	/**
	 * 取得slot槽中的key
	 * 
	 * @param slot slot槽
	 * @param count 上限
	 * @return slot槽中的key
	 * @see <a href="https://redis.io/commands/cluster-getkeysinslot">Redis Documentation: CLUSTER GETKEYSINSLOT</a>
	 */
	List<byte[]> clusterGetKeysInSlot(int slot, Integer count);

	/**
	 * 将当前slave节点设置为master指定的节点的从节点。
	 * 
	 * @param master master节点
	 * @param slave slave节点
	 * @see <a href="https://redis.io/commands/cluster-replicate">Redis Documentation: CLUSTER REPLICATE</a>
	 */
	void clusterReplicate(RedisClusterNode master, RedisClusterNode slave);

	/**
	 *   CLUSTER SETSLOT命令对应的模式
	 * 
	 *            
	 *            .==.       .==.
	 *           //'^\\     //^'\\
	 *          // ^^\(\__/)/^ ^^\\
	 *         //^ ^^ ^/6  6\ ^^^ \\
	 *        //^ ^^ ^/( .. )\^ ^^ \\
	 *       // ^^  ^/\|v""v|/\^^ ^ \\
	 *      // ^^/\/  / '~~' \ \/\^ ^\\
	 *      ----------------------------------------
	 *      HERE BE DRAGONS WHICH CAN CREATE MIRACLE
	 *       
	 *      @author 静儿(987489055@qq.com)
	 *
	 */
	public enum AddSlots {
		MIGRATING, IMPORTING, STABLE, NODE
	}

}
