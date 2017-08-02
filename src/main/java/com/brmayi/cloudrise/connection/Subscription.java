package com.brmayi.cloudrise.connection;

import java.util.Collection;

import javax.jms.MessageListener;

import com.brmayi.cloudrise.exception.CloudRiseException;

public interface Subscription {
	/**
	 * Adds the given channels to the current subscription.
	 * 
	 * @param channels channel names
	 */
	void subscribe(byte[]... channels) throws CloudRiseException;

	/**
	 * Adds the given channel patterns to the current subscription.
	 * 
	 * @param patterns channel patterns
	 */
	void pSubscribe(byte[]... patterns) throws CloudRiseException;

	/**
	 * Cancels the current subscription for all channels given by name.
	 */
	void unsubscribe();

	/**
	 * Cancels the current subscription for all given channels.
	 * 
	 * @param channels channel names
	 */
	void unsubscribe(byte[]... channels);

	/**
	 * Cancels the subscription for all channels matched by patterns.
	 */
	void pUnsubscribe();

	/**
	 * Cancels the subscription for all channels matching the given patterns.
	 * 
	 * @param patterns
	 */
	void pUnsubscribe(byte[]... patterns);

	/**
	 * Returns the (named) channels for this subscription.
	 * 
	 * @return collection of named channels
	 */
	Collection<byte[]> getChannels();

	/**
	 * Returns the channel patters for this subscription.
	 * 
	 * @return collection of channel patterns
	 */
	Collection<byte[]> getPatterns();

	/**
	 * Returns the listener used for this subscription.
	 * 
	 * @return the listener used for this subscription.
	 */
	MessageListener getListener();

	/**
	 * Indicates whether this subscription is still 'alive' or not.
	 * 
	 * @return true if the subscription still applies, false otherwise.
	 */
	boolean isAlive();
}
