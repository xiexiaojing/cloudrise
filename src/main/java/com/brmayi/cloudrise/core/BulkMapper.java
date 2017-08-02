package com.brmayi.cloudrise.core;

import java.util.List;

public interface BulkMapper<T, V> {
	T mapBulk(List<V> tuple);
}