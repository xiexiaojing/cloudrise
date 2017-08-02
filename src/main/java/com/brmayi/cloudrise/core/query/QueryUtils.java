package com.brmayi.cloudrise.core.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.brmayi.cloudrise.connection.DefaultSortParameters;
import com.brmayi.cloudrise.connection.SortParameters;
import com.brmayi.cloudrise.serializer.RedisSerializer;

public abstract class QueryUtils {

	public static <K> SortParameters convertQuery(SortQuery<K> query, RedisSerializer<String> stringSerializer) {

		return new DefaultSortParameters(stringSerializer.serialize(query.getBy()), query.getLimit(), serialize(
				query.getGetPattern(), stringSerializer), query.getOrder(), query.isAlphabetic());
	}

	private static byte[][] serialize(List<String> strings, RedisSerializer<String> stringSerializer) {
		List<byte[]> raw = null;

		if (strings == null) {
			raw = Collections.emptyList();
		} else {
			raw = new ArrayList<>(strings.size());
			for (String key : strings) {
				raw.add(stringSerializer.serialize(key));
			}
		}
		return raw.toArray(new byte[raw.size()][]);
	}
}
