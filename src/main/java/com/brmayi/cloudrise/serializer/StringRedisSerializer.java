package com.brmayi.cloudrise.serializer;

import java.nio.charset.Charset;

import org.springframework.util.Assert;
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
 * 模块：cloudrise serializer
 * 功能：提供对字符串的spring序列化
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
public class StringRedisSerializer implements RedisSerializer<String> {
	  private final Charset charset;

	  public StringRedisSerializer() {
	    this(Charset.forName("UTF8"));
	  }

	  public StringRedisSerializer(Charset charset) {
	    Assert.notNull(charset, "Charset must not be null!");
	    this.charset = charset;
	  }

	  public String deserialize(byte[] bytes) {
	    return new String(bytes, this.charset);
	  }

	  public byte[] serialize(String string) {
	    return ((string == null) ? null : string.getBytes(this.charset));
	  }
}
