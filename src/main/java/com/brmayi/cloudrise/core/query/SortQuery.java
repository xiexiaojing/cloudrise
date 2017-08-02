package com.brmayi.cloudrise.core.query;

import java.util.List;



import com.brmayi.cloudrise.connection.SortParameters.Order;
import com.brmayi.cloudrise.connection.SortParameters.Range;

public interface SortQuery<K> {

	/**
	 * Returns the sorting order. Can be null if nothing is specified.
	 * 
	 * @return sorting order
	 */
	Order getOrder();

	/**
	 * Indicates if the sorting is numeric (default) or alphabetical (lexicographical). Can be null if nothing is
	 * specified.
	 * 
	 * @return the type of sorting
	 */
	Boolean isAlphabetic();

	/**
	 * Returns the sorting limit (range or pagination). Can be null if nothing is specified.
	 * 
	 * @return sorting limit/range
	 */
	Range getLimit();

	/**
	 * Return the target key for sorting.
	 * 
	 * @return the target key
	 */
	K getKey();

	/**
	 * Returns the pattern of the external key used for sorting.
	 * 
	 * @return the external key pattern
	 */
	String getBy();

	/**
	 * Returns the external key(s) whose values are returned by the sort.
	 * 
	 * @return the (list of) keys used for GET
	 */
	List<String> getGetPattern();
}
