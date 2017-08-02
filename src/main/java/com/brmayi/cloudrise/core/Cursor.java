package com.brmayi.cloudrise.core;

import java.io.Closeable;
import java.util.Iterator;

public interface Cursor<T> extends Iterator<T>, Closeable {
  public long getCursorId();

  public boolean isClosed();

  public Cursor<T> open();

  public long getPosition();
}
