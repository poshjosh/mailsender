package com.looseboxes.mailsender.google;

import com.looseboxes.fileclient.FileHandler;
import com.looseboxes.gmailapi.config.Api;
import com.looseboxes.gmailapi.config.GmailConfig;
import com.looseboxes.gmailapi.config.OAuth2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author hp
 */
public class GmailConfigImpl extends com.looseboxes.gmailapi.config.GmailConfig{

    private final FileHandler fileHandler;

    private final GmailConfig gmailConfig;

    public GmailConfigImpl(FileHandler fileHandler, GmailConfig gmailConfig) {
        this.fileHandler = Objects.requireNonNull(fileHandler);
        this.gmailConfig = Objects.requireNonNull(gmailConfig);
    }

    @Override
    public InputStream getCredentialsFileInputStream() throws IOException {
        return fileHandler.read(Paths.get(getApi().getCredentialsFilePath()));
    }

    @Override
    public Api getApi() {
        return gmailConfig.getApi();
    }

    @Override
    public void setApi(Api api) {
        gmailConfig.setApi(api);
    }

    @Override
    public GmailConfig api(Api api) {
        return gmailConfig.api(api);
    }

    @Override
    public OAuth2 getoAuth2() {
        return gmailConfig.getoAuth2();
    }

    @Override
    public void setoAuth2(OAuth2 oAuth2) {
        gmailConfig.setoAuth2(oAuth2);
    }

    @Override
    public GmailConfig oAuth2(OAuth2 oAuth2) {
        return gmailConfig.oAuth2(oAuth2);
    }

    @Override
    public String toString() {
        return gmailConfig.toString();
    }
}
