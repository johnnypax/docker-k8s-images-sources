package dev.archety.studentmanager.dto;

public class VersionResponse {

    private String app;
    private String version;
    private String profile;

    public VersionResponse() {
    }

    public VersionResponse(String app, String version, String profile) {
        this.app = app;
        this.version = version;
        this.profile = profile;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}