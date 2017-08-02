package com.brmayi.cloudrise.core;

import org.springframework.util.StringUtils;


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
 * 模块：cloudrise ScanOptions
 * 功能：Redis SCAN命令的选项
 *     SCAN 命令是一个基于游标的迭代器（cursor based iterator）： 
 *     SCAN 命令每次被调用之后， 都会向用户返回一个新的游标， 用户在下次迭代时需要使用这个新游标作为 SCAN 命令的游标参数， 以此来延续之前的迭代过程。
 *     当 SCAN 命令的游标参数被设置为 0 时， 服务器将开始一次新的迭代， 而当服务器向用户返回值为 0 的游标时， 表示迭代已结束。
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
public class ScanOptions {
	public static ScanOptions NONE = new ScanOptions();
	private Long count;
	private String pattern;

	public static ScanOptionsBuilder scanOptions() {
		return new ScanOptionsBuilder();
	}

	/**
	 * 虽然增量式迭代命令不保证每次迭代所返回的元素数量， 但我们可以使用 COUNT 选项， 对命令的行为进行一定程度上的调整。
	 * 基本上， COUNT 选项的作用就是让用户告知迭代命令， 在每次迭代中应该从数据集里返回多少元素。
	 * 虽然 COUNT 选项只是对增量式迭代命令的一种提示（hint）， 但是在大多数情况下， 这种提示都是有效的。
	 * COUNT 参数的默认值为 10 。
	 * 在迭代一个足够大的、由哈希表实现的数据库、集合键、哈希键或者有序集合键时， 如果用户没有使用 MATCH 选项， 那么命令返回的元素数量通常和 COUNT 选项指定的一样， 或者比 COUNT 选项指定的数量稍多一些。
	 * 在迭代一个编码为整数集合（intset，一个只由整数值构成的小集合）、 或者编码为压缩列表（ziplist，由不同值构成的一个小哈希或者一个小有序集合）时， 增量式迭代命令通常会无视 COUNT 选项指定的值， 在第一次迭代就将数据集包含的所有元素都返回给用户。
	 * @return 在每次迭代中应该从数据集里返回多少元素
	 */
	public Long getCount() {
		return this.count;
	}

	/**
	 * 和 KEYS 命令一样， 增量式迭代命令也可以通过提供一个 glob 风格的模式参数， 让命令只返回和给定模式相匹配的元素， 这一点可以通过在执行增量式迭代命令时， 通过给定 MATCH <pattern> 参数来实现。
	 * @return 和给定模式相匹配的元素
	 */
	public String getPattern() {
		return this.pattern;
	}

	public String toOptionString() {
		if (super.equals(NONE)) {
			return "";
		}

		String params = "";

		if (this.count != null) {
			params = params + ", 'count', " + this.count;
		}
		if (StringUtils.hasText(this.pattern)) {
			params = params + ", 'match' , '" + this.pattern + "'";
		}

		return params;
	}

	public static class ScanOptionsBuilder {
		ScanOptions options;

		public ScanOptionsBuilder() {
			this.options = new ScanOptions();
		}

		/**
		 * Returns the current {@link ScanOptionsBuilder} configured with the
		 * given {@code count}.
		 * 
		 * @param count
		 * @return
		 */
		public ScanOptionsBuilder count(long count) {
			options.count = count;
			return this;
		}

		/**
		 * Returns the current {@link ScanOptionsBuilder} configured with the
		 * given {@code pattern}.
		 * 
		 * @param pattern
		 * @return
		 */
		public ScanOptionsBuilder match(String pattern) {
			options.pattern = pattern;
			return this;
		}

		public ScanOptions build() {
			return this.options;
		}
	}
}
