package com.brmayi.cloudrise.connection;

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
 * 模块：cloudrise SortParameters
 * 功能：Redis SORT命令的参数
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
public interface SortParameters {

	/**
	 * 排序顺序
	 */
	public enum Order {
		ASC, DESC
	}

	/**
	 * 包装了LIMIT设置的工具类
	 */
	public static class Range {
		private final long start;
		private final long count;

		public Range(long start, long count) {
			this.start = start;
			this.count = count;
		}

		public long getStart() {
			return start;
		}

		public long getCount() {
			return count;
		}
	}

	/**
	 * 返回排序顺序，可以为null
	 * 
	 * @return 排序顺序
	 */
	Order getOrder();

	/**
	 * 指示了是按照数字还是字母排序，可以为null
	 * 
	 * @return 排序类型
	 */
	Boolean isAlphabetic();

	/**
	 * 如果设置了以外部键值排序时返回，可以为null
	 * 
	 * @return <tt>BY</tt> pattern.
	 */
	byte[] getByPattern();

	/**
	 * Returns the pattern (if set) for retrieving external keys (<tt>GET</tt>). Can be null if nothing is specified.
	 * 
	 * @return <tt>GET</tt> pattern.
	 */
	byte[][] getGetPattern();

	/**
	 * 返回排序的上限或者分页大小，可以为null
	 * 
	 * @return 排序范围
	 */
	Range getLimit();
}