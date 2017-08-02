package com.brmayi.cloudrise.core;

import java.io.IOException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

public class ConvertingCursor<S, T> implements Cursor<T> {

	private Cursor<S> delegate;
	private Converter<S, T> converter;

	/**
	 * @param cursor Cursor must not be {@literal null}.
	 * @param converter Converter must not be {@literal null}.
	 */
	public ConvertingCursor(Cursor<S> cursor, Converter<S, T> converter) {

		Assert.notNull(cursor, "Cursor delegate must not be 'null'.");
		Assert.notNull(cursor, "Converter must not be 'null'.");
		this.delegate = cursor;
		this.converter = converter;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return delegate.hasNext();
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		return converter.convert(delegate.next());
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		delegate.remove();
	}

	/*
	 * (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		delegate.close();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.Cursor#getCursorId()
	 */
	@Override
	public long getCursorId() {
		return delegate.getCursorId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.Cursor#isClosed()
	 */
	@Override
	public boolean isClosed() {
		return delegate.isClosed();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.Cursor#open()
	 */
	@Override
	public Cursor<T> open() {
		this.delegate = delegate.open();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.redis.core.Cursor#getPosition()
	 */
	@Override
	public long getPosition() {
		return delegate.getPosition();
	}

}