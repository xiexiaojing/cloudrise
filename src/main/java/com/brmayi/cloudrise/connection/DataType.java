package com.brmayi.cloudrise.connection;

import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
 * 模块：cloudrise DataType
 * 功能：定义Redis支持的基本数据类型
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
public enum DataType {
	NONE, STRING, LIST, SET, ZSET, HASH;

	private static final Map<String, DataType> codeLookup;
	private final String code = this.name();

	public String code() {
		return this.code;
	}

	public static DataType fromCode(String code) {
		DataType data = (DataType) codeLookup.get(code);
		if (data == null)
			throw new IllegalArgumentException("不支持的数据类型");
		return data;
	}

	static {
		codeLookup = new ConcurrentHashMap<>(6);
		for (DataType type : EnumSet.allOf(DataType.class))
			codeLookup.put(type.code, type);
	}
	
	public static void main(String[] args) {
		System.out.println(DataType.NONE.code());
	}
}