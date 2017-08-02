package com.brmayi.cloudrise.connection;

import java.util.Properties;

import org.springframework.util.Assert;

import com.brmayi.cloudrise.core.types.RedisClientInfo.INFO;

public class ClusterInfo {

	public static enum Info {
		STATE("cluster_state"), SLOTS_ASSIGNED("cluster_slots_assigned"), SLOTS_OK("cluster_slots_ok"), SLOTS_PFAIL(
				"cluster_slots_pfail"), SLOTS_FAIL("cluster_slots_fail"), KNOWN_NODES("cluster_known_nodes"), SIZE(
				"cluster_size"), CURRENT_EPOCH("cluster_current_epoch"), MY_EPOCH("cluster_my_epoch"), MESSAGES_SENT(
				"cluster_stats_messages_sent"), MESSAGES_RECEIVED("cluster_stats_messages_received");

		String key;

		Info(String key) {
			this.key = key;
		}
	}

	private final Properties clusterProperties;

	/**
	 * Creates new {@link ClusterInfo} for given {@link Properties}.
	 * 
	 * @param clusterProperties must not be {@literal null}.
	 */
	public ClusterInfo(Properties clusterProperties) {

		Assert.notNull(clusterProperties, "ClusterProperties must not be null!");
		this.clusterProperties = clusterProperties;
	}

	/**
	 * @see Info#STATE
	 * @return
	 */
	public String getState() {
		return get(Info.STATE);
	}

	/**
	 * @see Info#SLOTS_ASSIGNED
	 * @return
	 */
	public Long getSlotsAssigned() {
		return getLongValueOf(Info.SLOTS_ASSIGNED);
	}

	/**
	 * @see Info#SLOTS_OK
	 * @return
	 */
	public Long getSlotsOk() {
		return getLongValueOf(Info.SLOTS_OK);
	}

	/**
	 * @see Info#SLOTS_PFAIL
	 * @return
	 */
	public Long getSlotsPfail() {
		return getLongValueOf(Info.SLOTS_PFAIL);
	}

	/**
	 * @see Info#SLOTS_FAIL
	 * @return
	 */
	public Long getSlotsFail() {
		return getLongValueOf(Info.SLOTS_FAIL);
	}

	/**
	 * @see Info#KNOWN_NODES
	 * @return
	 */
	public Long getKnownNodes() {
		return getLongValueOf(Info.KNOWN_NODES);
	}

	/**
	 * @see Info#SIZE
	 * @return
	 */
	public Long getClusterSize() {
		return getLongValueOf(Info.SIZE);
	}

	/**
	 * @see Info#CURRENT_EPOCH
	 * @return
	 */
	public Long getCurrentEpoch() {
		return getLongValueOf(Info.CURRENT_EPOCH);
	}

	/**
	 * @see Info#MESSAGES_SENT
	 * @return
	 */
	public Long getMessagesSent() {
		return getLongValueOf(Info.MESSAGES_SENT);
	}

	/**
	 * @see Info#MESSAGES_RECEIVED
	 * @return
	 */
	public Long getMessagesReceived() {
		return getLongValueOf(Info.MESSAGES_RECEIVED);
	}

	/**
	 * @param info must not be null
	 * @return {@literal null} if no entry found for requested {@link INFO}.
	 */
	public String get(Info info) {

		Assert.notNull(info, "Cannot retrieve cluster information for 'null'.");
		return get(info.key);
	}

	/**
	 * @param key must not be {@literal null} or {@literal empty}.
	 * @return {@literal null} if no entry found for requested {@code key}.
	 */
	public String get(String key) {

		Assert.hasText(key, "Cannot get cluster information for 'empty' / 'null' key.");
		return this.clusterProperties.getProperty(key);
	}

	private Long getLongValueOf(Info info) {

		String value = get(info);
		return value == null ? null : Long.valueOf(value);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.clusterProperties.toString();
	}

}