package com.looseboxes.mailsender.google;

import com.looseboxes.fileclient.FileHandler;
import com.looseboxes.gmailapi.GmailConfig;
import com.looseboxes.mailsender.config.GmailProperties;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * @author hp
 */
public class GmailConfigImpl implements GmailConfig{
    
    private final GmailProperties properties;
    
    private final FileHandler fileHandler;

    public GmailConfigImpl(FileHandler fileHandler, GmailProperties properties) {
        this.fileHandler = Objects.requireNonNull(fileHandler);
        this.properties = Objects.requireNonNull(properties);
    }

    @Override
    public InputStream getCredentialsFileInputStream() throws IOException {
        return fileHandler.read(Paths.get(properties.getCredentialsFilePath()));
    }

    @Override
    public Path getTokensDirectory() throws IOException {
        return Paths.get(properties.getTokensDirectoryPath());
    }

    @Override
    public List<String> getAccessTokenScopes() {
        return properties.getAccessTokenScopes();
    }
}
