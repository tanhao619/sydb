package com.cdyoue.oddJobs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by cdyoue on 2016/10/12.
 */
@Component
public class DynamicSetting {
    @Value("${default.commonUpload.driver}")
    public String driver;
    @Value("${default.commonUpload.server}")
    public String server;
    @Value("${default.commonUpload.fileAddress}")
    public String fileAddress;
    @Value("${default.eventTracking.remoteUrl}")
    public String eventRemoteUrl;

    @Value("${default.commonUpload.compress}")
    public String compress;

    public StringBuffer getUploadPath(){
        StringBuffer bf = new StringBuffer(driver)
                .append(fileAddress);
        return bf;
    }

    public StringBuffer getremoteUploadPath(){
        StringBuffer bf = new StringBuffer(server)
                .append(fileAddress);
        return bf;
    }

    public String getEventRemoteUrl() {
        return eventRemoteUrl;
    }

    public void setEventRemoteUrl(String eventRemoteUrl) {
        this.eventRemoteUrl = eventRemoteUrl;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getCompress() {
        return compress;
    }

    public void setCompress(final String compress) {
        this.compress = compress;
    }
}
