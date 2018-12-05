package net.liuzd.spring.boot.v2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @copyright 2011-2017 jd.com
 * @author <a href="mailto:liuzidong@jd.com">liuzidong</a>
 * @version V1.0 上午9:56:31
 */
@Component
public class PropertiesConfig {

    @Value("${profiles.active}")
    private String profilesActive;

    public String getProfilesActive() {
        return profilesActive;
    }

    public void setProfilesActive(String profilesActive) {
        this.profilesActive = profilesActive;
    }

}
