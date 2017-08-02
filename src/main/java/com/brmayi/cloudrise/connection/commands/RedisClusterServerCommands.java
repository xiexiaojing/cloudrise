package com.brmayi.cloudrise.connection.commands;

import java.util.List;
import java.util.Properties;

import com.brmayi.cloudrise.connection.RedisClusterNode;
import com.brmayi.cloudrise.core.types.RedisClientInfo;
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
 * 功能：提供对Redis的服务器操作抽象(所有的key和value都不能为null),每一个方式对应于一个redis命令。需指定节点
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
public interface RedisClusterServerCommands extends RedisServerCommands {


	/**
	 * 执行一个 AOF文件 重写操作。重写会创建一个当前 AOF 文件的体积优化版本。
	 * 即使 BGREWRITEAOF 执行失败，也不会有任何数据丢失，因为旧的 AOF 文件在 BGREWRITEAOF 成功之前不会被修改。
	 * 重写操作只会在没有其他持久化工作在后台执行时被触发，也就是说：
	 * 如果 Redis 的子进程正在执行快照的保存工作，那么 AOF 重写的操作会被预定(scheduled)，等到保存工作完成之后再执行 AOF 重写。
	 * 在这种情况下， BGREWRITEAOF 的返回值仍然是 OK ，但还会加上一条额外的信息，说明 BGREWRITEAOF 要等到保存操作完成之后才能执行。
	 * 在 Redis 2.6 或以上的版本，可以使用 INFO 命令查看 BGREWRITEAOF 是否被预定。
	 * 如果已经有别的 AOF 文件重写在执行，那么 BGREWRITEAOF 返回一个错误，并且这个新的 BGREWRITEAOF 请求也不会被预定到下次执行。
	 * 从 Redis 2.4 开始， AOF 重写由 Redis 自行触发， BGREWRITEAOF 仅仅用于手动触发重写操作。
	 * 时间复杂度：O(N)， N 为要追加到 AOF 文件中的数据数量。
	 * @param node 节点
	 * @since 1.3
	 * @see <a href="http://redis.io/commands/bgrewriteaof">Redis Documentation: BGREWRITEAOF</a>
	 */
	void bgReWriteAof(RedisClusterNode node);

	/**
	 * 在后台异步(Asynchronously)保存当前数据库的数据到磁盘。
	 * BGSAVE 命令执行之后立即返回 OK ，然后 Redis fork 出一个新子进程，原来的 Redis 进程(父进程)继续处理客户端请求，而子进程则负责将数据保存到磁盘，然后退出。
	 * 客户端可以通过 LASTSAVE 命令查看相关信息，判断 BGSAVE 命令是否执行成功。
	 * @param node 节点
	 * @see <a href="http://redis.io/commands/bgsave">Redis Documentation: BGSAVE</a>
	 */
	void bgSave(RedisClusterNode node);


	/**
	 * 返回最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示。
	 * 时间复杂度：O(1)
	 * @param node 节点
	 * @return 一个 UNIX 时间戳。
	 * @see <a href="http://redis.io/commands/lastsave">Redis Documentation: LASTSAVE</a>
	 */
	Long lastSave(RedisClusterNode node);


	/**
	 * SAVE 命令执行一个同步保存操作，将当前 Redis 实例的所有数据快照(snapshot)以 RDB 文件的形式保存到硬盘。
	 * 一般来说，在生产环境很少执行 SAVE 操作，因为它会阻塞所有客户端，保存数据库的任务通常由 BGSAVE 命令异步地执行。
	 * 然而，如果负责保存数据的后台子进程不幸出现问题时， SAVE 可以作为保存数据的最后手段来使用。
	 * 时间复杂度:O(N)， N 为要保存到数据库中的 key 的数量。
	 * @param node 节点
	 * @see <a href="http://redis.io/commands/save">Redis Documentation: SAVE</a>
	 */
	void save(RedisClusterNode node);


	/**
	 * 返回当前数据库的 key 的数量。
	 * 时间复杂度：O(1)
	 * @param node 节点
	 * @return 返回当前数据库的 key 的数量。
	 * @see <a href="http://redis.io/commands/dbsize">Redis Documentation: DBSIZE</a>
	 */
	Long dbSize(RedisClusterNode node);


	/**
	 * 清空当前数据库中的所有 key。此命令从不失败。
	 * 时间复杂度：O(1)
	 * @param node 节点
	 * @see <a href="http://redis.io/commands/flushdb">Redis Documentation: FLUSHDB</a>
	 */
	void flushDb(RedisClusterNode node);


	/**
	 * 清空整个 Redis 服务器的数据(删除所有数据库的所有 key )。此命令从不失败。
	 * @param node 节点
	 * @see <a href="http://redis.io/commands/flushall">Redis Documentation: FLUSHALL</a>
	 */
	void flushAll(RedisClusterNode node);


	/**
	 * Redis Info 命令以一种易于理解和阅读的格式，返回关于 Redis 服务器的各种信息和统计数值。
	 * 命令返回信息示例：
	 * <pre>
	 * # Server
	 * redis_version:3.2.9
	 * redis_git_sha1:00000000
	 * redis_git_dirty:0
	 * redis_build_id:a6f5b91b81bb8d8c
	 * redis_mode:cluster
	 * os:Linux 2.6.32-926.504.30.3.letv.el6.x86_64 x86_64
	 * arch_bits:64
	 * multiplexing_api:epoll
	 * gcc_version:4.4.7
	 * process_id:17612
	 * run_id:5644a791339db3e67cfb4ad4c529fee624e83326
	 * tcp_port:6379
	 * uptime_in_seconds:1299970
	 * uptime_in_days:15
	 * hz:10
	 * lru_clock:8405316
	 * executable:/letv/apps_install/redis-3.2.9/redis0/./redis-server
	 * config_file:/letv/apps_install/redis-3.2.9/redis0/redis.conf
	 * # Clients
	 * connected_clients:5
	 * client_longest_output_list:0
	 * client_biggest_input_buf:0
	 * blocked_clients:0
	 * 
	 * # Memory
	 * used_memory:1452392
	 * used_memory_human:1.39M
	 * used_memory_rss:3137536
	 * used_memory_rss_human:2.99M
	 * used_memory_peak:1491800
	 * used_memory_peak_human:1.42M
	 * total_system_memory:135210921984
	 * total_system_memory_human:125.92G
	 * used_memory_lua:37888
	 * used_memory_lua_human:37.00K
	 * maxmemory:0
	 * maxmemory_human:0B
	 * maxmemory_policy:noeviction
	 * mem_fragmentation_ratio:2.16
	 * mem_allocator:jemalloc-4.0.3
	 * 
	 * # Persistence
	 * loading:0
	 * rdb_changes_since_last_save:0
	 * rdb_bgsave_in_progress:0
	 * rdb_last_save_time:1500277631
	 * rdb_last_bgsave_status:ok
	 * rdb_last_bgsave_time_sec:0
	 * rdb_current_bgsave_time_sec:-1
	 * aof_enabled:1
	 * aof_rewrite_in_progress:0
	 * aof_rewrite_scheduled:0
	 * aof_last_rewrite_time_sec:0
	 * aof_current_rewrite_time_sec:-1
	 * aof_last_bgrewrite_status:ok
	 * aof_last_write_status:ok
	 * aof_current_size:25302
	 * aof_base_size:25302
	 * aof_pending_rewrite:0
	 * aof_buffer_length:0
	 * aof_rewrite_buffer_length:0
	 * aof_pending_bio_fsync:0
	 * aof_delayed_fsync:0
	 * 
	 * # Stats
	 * total_connections_received:15305
	 * total_commands_processed:86898
	 * instantaneous_ops_per_sec:0
	 * total_net_input_bytes:2016530
	 * total_net_output_bytes:859156
	 * instantaneous_input_kbps:0.00
	 * instantaneous_output_kbps:0.00
	 * rejected_connections:0
	 * sync_full:0
	 * sync_partial_ok:0
	 * sync_partial_err:0
	 * expired_keys:0
	 * evicted_keys:0
	 * keyspace_hits:0
	 * keyspace_misses:0
	 * pubsub_channels:0
	 * pubsub_patterns:0
	 * latest_fork_usec:292
	 * migrate_cached_sockets:0
	 * 
	 * # Replication
	 * role:slave
	 * master_host:10.127.95.184
	 * master_port:6381
	 * master_link_status:down
	 * master_last_io_seconds_ago:-1
	 * master_sync_in_progress:0
	 * slave_repl_offset:1
	 * master_link_down_since_seconds:1298428
	 * slave_priority:100
	 * slave_read_only:1
	 * connected_slaves:0
	 * master_repl_offset:0
	 * repl_backlog_active:0
	 * repl_backlog_size:1048576
	 * repl_backlog_first_byte_offset:0
	 * repl_backlog_histlen:0
	 * 
	 * # CPU
	 * used_cpu_sys:1273.56
	 * used_cpu_user:715.02
	 * used_cpu_sys_children:0.00
	 * used_cpu_user_children:0.00
	 * 
	 * # Cluster
	 * cluster_enabled:1
	 * 
	 * # Keyspace
	 * db0:keys=6,expires=0,avg_ttl=0
	 * </pre>
	 * @param node 节点
	 * @return 信息如上
	 * @see <a href="http://redis.io/commands/info">Redis Documentation: INFO</a>
	 */
	Properties info(RedisClusterNode node);


	/**
	 * Load server information for given {@code selection}.
	 * 命令示例如下：
	 * <pre>
	 * 10.183.96.194:6379> info memory
	 * </pre>
	 * 命令返回结果如下：
	 * # Memory
	 * used_memory:1452392
	 * used_memory_human:1.39M
	 * used_memory_rss:3137536
	 * used_memory_rss_human:2.99M
	 * used_memory_peak:1491800
	 * used_memory_peak_human:1.42M
	 * total_system_memory:135210921984
	 * total_system_memory_human:125.92G
	 * used_memory_lua:37888
	 * used_memory_lua_human:37.00K
	 * maxmemory:0
	 * maxmemory_human:0B
	 * maxmemory_policy:noeviction
	 * mem_fragmentation_ratio:2.16
	 * mem_allocator:jemalloc-4.0.3
	 * @param node 节点
	 * @return 信息如上
	 * @see <a href="http://redis.io/commands/info">Redis Documentation: INFO</a>
	 */
	Properties info(RedisClusterNode node, String section);


	/**
	 * SHUTDOWN 命令执行以下操作：
	 * 停止所有客户端
	 * 如果有至少一个保存点在等待，执行 SAVE 命令
	 * 如果 AOF 选项被打开，更新 AOF 文件
	 * 关闭 redis 服务器(server)
	 * 如果持久化被打开的话， SHUTDOWN 命令会保证服务器正常关闭而不丢失任何数据。
	 * 
	 * 另一方面，假如只是单纯地执行 SAVE 命令，然后再执行 QUIT 命令，则没有这一保证 —— 因为在执行 SAVE 之后、执行 QUIT 之前的这段时间中间，其他客户端可能正在和服务器进行通讯，这时如果执行 QUIT 就会造成数据丢失。
	 * 
	 * SAVE 和 NOSAVE 修饰符
	 * 通过使用可选的修饰符，可以修改 SHUTDOWN 命令的表现。比如说：
	 * 执行 SHUTDOWN SAVE 会强制让数据库执行保存操作，即使没有设定(configure)保存点
	 * 执行 SHUTDOWN NOSAVE 会阻止数据库执行保存操作，即使已经设定有一个或多个保存点(你可以将这一用法看作是强制停止服务器的一个假想的 ABORT 命令)
	 * @param node 节点
	 * @see <a href="http://redis.io/commands/shutdown">Redis Documentation: SHUTDOWN</a>
	 */
	void shutdown(RedisClusterNode node);


	/**
	 * CONFIG GET 命令用于取得运行中的 Redis 服务器的配置参数(configuration parameters)，
	 * 在 Redis 2.4 版本中， 有部分参数没有办法用 CONFIG GET 访问，但是在最新的 Redis 2.6 版本中，所有配置参数都已经可以用 CONFIG GET 访问了。
	 * CONFIG GET 接受单个参数 parameter 作为搜索关键字，查找所有匹配的配置参数，其中参数和值以“键-值对”(key-value pairs)的方式排列。
	 * 比如执行 CONFIG GET s* 命令，服务器就会返回所有以 s 开头的配置参数及参数的值：
	 *
	 * @param pattern 不能为空
	 * @return 结果示例如下：
	 * <pre>
	 *  1) "slave-announce-ip"
	 *  2) ""
	 *  3) "set-max-intset-entries"
	 *  4) "512"
	 *  5) "slowlog-log-slower-than"
	 *  6) "10000"
	 *  7) "slowlog-max-len"
	 *  8) "128"
	 *  9) "slave-priority"
	 * 10) "100"
	 * 11) "slave-announce-port"
	 * 12) "0"
	 * 13) "slave-serve-stale-data"
	 * 14) "yes"
	 * 15) "slave-read-only"
	 * 16) "yes"
	 * 17) "stop-writes-on-bgsave-error"
	 * 18) "yes"
	 * 19) "supervised"
	 * 20) "no"
	 * 21) "syslog-facility"
	 * 22) "local0"
	 * 23) "save"
	 * 24) "900 1 300 10 60 10000"
	 * 25) "slaveof"
	 * 26) "10.127.95.184 6381"
	 * </pre>
	 * @param node 节点
	 * @see <a href="http://redis.io/commands/config-get">Redis Documentation: CONFIG GET</a>
	 */
	Properties getConfig(RedisClusterNode node, String pattern);


	/**
	 * CONFIG SET 命令可以动态地调整 Redis 服务器的配置(configuration)而无须重启。
	 * 你可以使用它修改配置参数，或者改变 Redis 的持久化(Persistence)方式。
	 * CONFIG SET 可以修改的配置参数可以使用命令 CONFIG GET * 来列出，所有被 CONFIG SET 修改的配置参数都会立即生效。
	 * @param node 节点
	 * @param param 参数
	 * @param value 要设定的值
	 * @see <a href="http://redis.io/commands/config-set">Redis Documentation: CONFIG SET</a>
	 */
	void setConfig(RedisClusterNode node, String param, String value);


	/**
	 * 重置 INFO 命令中的某些统计数据，包括：
	 * Keyspace hits (键空间命中次数)
	 * Keyspace misses (键空间不命中次数)
	 * Number of commands processed (执行命令的次数)
	 * Number of connections received (连接服务器的次数)
	 * Number of expired keys (过期key的数量)
	 * Number of rejected connections (被拒绝的连接数量)
	 * Latest fork(2) time(最后执行 fork(2) 的时间)
	 * The aof_delayed_fsync counter(aof_delayed_fsync 计数器的值)
	 * @param node 节点
	 * @see <a href="http://redis.io/commands/config-resetstat">Redis Documentation: CONFIG RESETSTAT</a>
	 */
	void resetConfigStats(RedisClusterNode node);


	/**
	 * 返回当前服务器时间。
	 * @param node 节点
	 * @return TIME命令是一个包含两个字符串的列表： 第一个字符串是当前时间(以 UNIX 时间戳格式表示)，而第二个字符串是当前这一秒钟已经逝去的微秒数。返回值是前一个
	 * @since 1.1
	 * @see <a href="http://redis.io/commands/time">Redis Documentation: TIME</a>
	 */
	Long time(RedisClusterNode node);

	/**
	 * 以人类可读的格式，返回所有连接到服务器的客户端信息和统计数据。
	 * @param node 节点
	 * @return 所有连接到服务器的客户端信息和统计数据。
	 * @since 1.3
	 * @see <a href="http://redis.io/commands/client-list">Redis Documentation: CLIENT LIST</a>
	 */
	List<RedisClientInfo> getClientList(RedisClusterNode node);
}