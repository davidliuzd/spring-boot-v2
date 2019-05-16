package net.liuzd.spring.boot.v2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {

    @Value("${profiles.active}")
    private String      profilesActive;

    @Value("${spring.profiles.active}")
    private String      springProfilesActive;

    @Autowired
    private Environment env;

    public String getProfilesActive() {
        return profilesActive;
    }

    public void setProfilesActive(String profilesActive) {
        this.profilesActive = profilesActive;
    }

    public String getSpringProfilesActive() {
        return springProfilesActive;
    }

    public void setSpringProfilesActive(String springProfilesActive) {
        this.springProfilesActive = springProfilesActive;
    }

    public String getEnv() {
        return env.getProperty("profiles.active") + " - " + env.getProperty("spring.profiles.active");
    }

    public String getAll() {
        return getProfilesActive() + " | " + getSpringProfilesActive() + " | " + getEnv();
    }

}
