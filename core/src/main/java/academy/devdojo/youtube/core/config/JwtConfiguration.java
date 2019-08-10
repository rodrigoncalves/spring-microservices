package academy.devdojo.youtube.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.config")
@Getter
@Setter
public class JwtConfiguration {

    private String loginUrl = "/login/**";
    @NestedConfigurationProperty
    private Header header = new Header();
    private int expiration = 3600; // seconds
    private String privateKey = "qxBEEQv7E8aviX1KUcdOiF5ve5COUPAr";
    private String type = "encrypted";

    @Getter
    @Setter
    public static class Header {
        private String name = "Authorization";
        private String prefix = "Bearer ";
    }
}
