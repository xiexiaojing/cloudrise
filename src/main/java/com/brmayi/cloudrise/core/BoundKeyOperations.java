package com.brmayi.cloudrise.core;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.brmayi.cloudrise.connection.DataType;

public interface BoundKeyOperations<K> {

	/**
	 * Returns the key associated with this entity.
	 * 
	 * @return key associated with the implementing entity
	 */
	K getKey();

	/**
	 * Returns the associated Redis type.
	 * 
	 * @return key type
	 */
	DataType getType();

	/**
	 * Returns the expiration of this key.
	 * 
	 * @return expiration value (in seconds)
	 */
	Long getExpire();

	/**
	 * Sets the key time-to-live/expiration.
	 * 
	 * @param timeout expiration value
	 * @param unit expiration unit
	 * @return true if expiration was set, false otherwise
	 */
	Boolean expire(long timeout, TimeUnit unit);

	/**
	 * Sets the key time-to-live/expiration.
	 * 
	 * @param date expiration date
	 * @return true if expiration was set, false otherwise
	 */
	Boolean expireAt(Date date);

	/**
	 * Removes the expiration (if any) of the key.
	 * 
	 * @return true if expiration was removed, false otherwise
	 */
	Boolean persist();

	/**
	 * Renames the key. <br>
	 * <b>Note:</b> The new name for empty collections will be propagated on add of first element.
	 * 
	 * @param newKey new key
	 */
	void rename(K newKey);
}
