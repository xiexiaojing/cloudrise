package com.brmayi.cloudrise;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.brmayi.cloudrise.connection.RedisConnectionFactory;

public class RedisAccessor implements InitializingBean {

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	private RedisConnectionFactory connectionFactory;

	public void afterPropertiesSet() {
		Assert.notNull(getConnectionFactory(), "RedisConnectionFactory is required");
	}

	/**
	 * Returns the connectionFactory.
	 * 
	 * @return Returns the connectionFactory
	 */
	public RedisConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	/**
	 * Sets the connection factory.
	 * 
	 * @param connectionFactory The connectionFactory to set.
	 */
	public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
}