package com.looseboxes.mailsender.google;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.looseboxes.fileclient.FileHandler;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
* File data store that inherits from the abstract memory data store because the key-value pairs
* are stored in a memory cache, and saved in the file (see {@link #save()} when changing values.
*
* @param <V> serializable type of the mapped value
*/
class GmailDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {

    /** File to store data. */
    private final File dataFile;
    
    private final FileHandler fileHandler;

    GmailDataStore(FileDataStoreFactory dataStore, FileHandler fileHandler, File dataDirectory, String id)throws IOException {
        
      super(dataStore, id);
      
      this.fileHandler = Objects.requireNonNull(fileHandler);
      
      this.dataFile = new File(dataDirectory, id);
      
      // error if it is a symbolic link
      if (IOUtils.isSymbolicLink(dataFile)) {
        throw new IOException("unable to use a symbolic link: " + dataFile);
      }
      
      // create new file (if necessary)
      if (dataFile.createNewFile()) {
        keyValueMap = Maps.newHashMap();
        // save the credentials to create a new file
        save();
      } else {
        // load credentials from existing file
        keyValueMap = (HashMap)fileHandler.readObject(dataFile.toPath());
//        keyValueMap = IOUtils.deserialize(new FileInputStream(dataFile));
      }
    }

    @Override
    public void save() throws IOException {
      fileHandler.writeObject(keyValueMap, dataFile.toPath());
    }

    @Override
    public FileDataStoreFactory getDataStoreFactory() {
      return (FileDataStoreFactory) super.getDataStoreFactory();
    }
}
