package com.looseboxes.mailsender.google;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Maps;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.AbstractDataStore;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.DataStoreUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author com.google.api.client.util.store.AbstractMemoryDataStore
 */
public class AbstractMemoryDataStore<V extends Serializable> extends AbstractDataStore<V> {

  /** Lock on access to the store. */
  private final Lock lock = new ReentrantLock();

  /** Data store map from the key to the value. */
  protected HashMap<String, byte[]> keyValueMap = Maps.newHashMap();

  /**
   * @param dataStoreFactory data store factory
   * @param id data store ID
   */
  protected AbstractMemoryDataStore(DataStoreFactory dataStoreFactory, String id) {
    super(dataStoreFactory, id);
  }

  public final Set<String> keySet() throws IOException {
    lock.lock();
    try {
      return Collections.unmodifiableSet(keyValueMap.keySet());
    } finally {
      lock.unlock();
    }
  }

  public final Collection<V> values() throws IOException {
    lock.lock();
    try {
      List<V> result = Lists.newArrayList();
      for (byte[] bytes : keyValueMap.values()) {
        result.add(IOUtils.<V>deserialize(bytes));
      }
      return Collections.unmodifiableList(result);
    } finally {
      lock.unlock();
    }
  }

  public final V get(String key) throws IOException {
    if (key == null) {
      return null;
    }
    lock.lock();
    try {
      return IOUtils.deserialize(keyValueMap.get(key));
    } finally {
      lock.unlock();
    }
  }

  public final DataStore<V> set(String key, V value) throws IOException {
    Preconditions.checkNotNull(key);
    Preconditions.checkNotNull(value);
    lock.lock();
    try {
      keyValueMap.put(key, IOUtils.serialize(value));
      save();
    } finally {
      lock.unlock();
    }
    return this;
  }

  public DataStore<V> delete(String key) throws IOException {
    if (key == null) {
      return this;
    }
    lock.lock();
    try {
      keyValueMap.remove(key);
      save();
    } finally {
      lock.unlock();
    }
    return this;
  }

  public final DataStore<V> clear() throws IOException {
    lock.lock();
    try {
      keyValueMap.clear();
      save();
    } finally {
      lock.unlock();
    }
    return this;
  }

  @Override
  public boolean containsKey(String key) throws IOException {
    if (key == null) {
      return false;
    }
    lock.lock();
    try {
      return keyValueMap.containsKey(key);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean containsValue(V value) throws IOException {
    if (value == null) {
      return false;
    }
    lock.lock();
    try {
      byte[] serialized = IOUtils.serialize(value);
      for (byte[] bytes : keyValueMap.values()) {
        if (Arrays.equals(serialized, bytes)) {
          return true;
        }
      }
      return false;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean isEmpty() throws IOException {
    lock.lock();
    try {
      return keyValueMap.isEmpty();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int size() throws IOException {
    lock.lock();
    try {
      return keyValueMap.size();
    } finally {
      lock.unlock();
    }
  }

  /**
   * Persist the key-value map into storage at the end of {@link #set}, {@link #delete(String)}, and
   * {@link #clear()}.
   */
  @SuppressWarnings("unused")
  public void save() throws IOException {}

  @Override
  public String toString() {
    return DataStoreUtils.toString(this);
  }
}
