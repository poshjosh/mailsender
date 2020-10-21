package com.looseboxes.mailsender.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author hp
 */
public class GmailProperties{
    
    private String tokensDirectoryPath;
    private String credentialsFilePath;
    private List<String> accessTokenScopes;
    
    public Path getTokensFilePath() {
        final String dir = getTokensDirectoryPath();
        return Paths.get(dir, "StoredCredential");
    }
    
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    public void setTokensDirectoryPath(String tokensDirectoryPath) {
        this.tokensDirectoryPath = tokensDirectoryPath;
    }

    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    public void setCredentialsFilePath(String credentialsFilePath) {
        this.credentialsFilePath = credentialsFilePath;
    }

    public List<String> getAccessTokenScopes() {
        return accessTokenScopes;
    }

    public void setAccessTokenScopes(List<String> accessTokenScopes) {
        this.accessTokenScopes = accessTokenScopes;
    }

    @Override
    public String toString() {
        return "GmailProperties{" + "tokensDirectoryPath=" + tokensDirectoryPath + ", credentialsFilePath=" + credentialsFilePath + ", accessTokenScopes=" + accessTokenScopes + '}';
    }
}
