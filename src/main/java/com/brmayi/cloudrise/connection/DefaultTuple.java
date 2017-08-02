package com.brmayi.cloudrise.connection;

import java.util.Arrays;

import com.brmayi.cloudrise.connection.commands.RedisZSetCommands.Tuple;

public class DefaultTuple implements Tuple {

	private final Double score;
	private final byte[] value;

	/**
	 * Constructs a new <code>DefaultTuple</code> instance.
	 * 
	 * @param value
	 * @param score
	 */
	public DefaultTuple(byte[] value, Double score) {
		this.score = score;
		this.value = value;
	}

	public Double getScore() {
		return score;
	}

	public byte[] getValue() {
		return value;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DefaultTuple))
			return false;
		DefaultTuple other = (DefaultTuple) obj;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (!Arrays.equals(value, other.value))
			return false;
		return true;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + Arrays.hashCode(value);
		return result;
	}

	public int compareTo(Double o) {
		Double d = (score == null ? Double.valueOf(0.0d) : score);
		Double a = (o == null ? Double.valueOf(0.0d) : o);
		return d.compareTo(a);
	}
}