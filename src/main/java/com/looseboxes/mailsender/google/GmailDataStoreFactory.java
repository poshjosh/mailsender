package com.looseboxes.mailsender.google;

import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.looseboxes.fileclient.FileHandler;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author hp
 */
public class GmailDataStoreFactory extends FileDataStoreFactory{
    
    private final FileHandler fileHandler;

    public GmailDataStoreFactory(FileHandler fileHandler, File dataDirectory) throws IOException {
        super(dataDirectory);
        this.fileHandler = Objects.requireNonNull(fileHandler);
    }

    @Override
    protected <V extends Serializable> DataStore<V> createDataStore(String id) throws IOException {
        return new GmailDataStore<>(this, this.fileHandler, this.getDataDirectory(), id);
    }
}
