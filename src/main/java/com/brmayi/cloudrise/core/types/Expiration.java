package com.brmayi.cloudrise.core.types;

import java.util.concurrent.TimeUnit;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

public class Expiration {
	private long expirationTime;
	private TimeUnit timeUnit;

	protected Expiration(long expirationTime, TimeUnit timeUnit) {
		this.expirationTime = expirationTime;
		this.timeUnit = ((timeUnit != null) ? timeUnit : TimeUnit.SECONDS);
	}

	public long getExpirationTimeInMilliseconds() {
		return getConverted(TimeUnit.MILLISECONDS);
	}

	public long getExpirationTimeInSeconds() {
		return getConverted(TimeUnit.SECONDS);
	}

	public long getExpirationTime() {
		return this.expirationTime;
	}

	public TimeUnit getTimeUnit() {
		return this.timeUnit;
	}

	public long getConverted(TimeUnit targetTimeUnit) {
		Assert.notNull(targetTimeUnit, "TargetTimeUnit must not be null!");
		return targetTimeUnit.convert(this.expirationTime, this.timeUnit);
	}

	public static Expiration seconds(long expirationTime) {
		return new Expiration(expirationTime, TimeUnit.SECONDS);
	}

	public static Expiration milliseconds(long expirationTime) {
		return new Expiration(expirationTime, TimeUnit.MILLISECONDS);
	}

	public static Expiration from(long expirationTime, TimeUnit timeUnit) {
		if ((ObjectUtils.nullSafeEquals(timeUnit, TimeUnit.MICROSECONDS))
				|| (ObjectUtils.nullSafeEquals(timeUnit, TimeUnit.NANOSECONDS))
				|| (ObjectUtils.nullSafeEquals(timeUnit, TimeUnit.MILLISECONDS))) {
			return new Expiration(timeUnit.toMillis(expirationTime),
					TimeUnit.MILLISECONDS);
		}

		if (timeUnit != null) {
			return new Expiration(timeUnit.toSeconds(expirationTime),
					TimeUnit.SECONDS);
		}

		return new Expiration(expirationTime, TimeUnit.SECONDS);
	}

	public static Expiration persistent() {
		return new Expiration(-1L, TimeUnit.SECONDS);
	}

	public boolean isPersistent() {
		return (this.expirationTime == -1L);
	}
}
