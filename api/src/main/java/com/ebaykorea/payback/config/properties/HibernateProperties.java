package com.ebaykorea.payback.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Data
@Configuration
@ConfigurationProperties("spring.jpa.hibernate")
public class HibernateProperties {
    private String defaultSchema;
    private Boolean showSql;
    private Boolean formatSql;
    private Boolean inspectStacktrace = false;
    private String namingPhysicalStrategy;

    public Properties getInfo() {
        final Properties properties = new Properties();
        properties.setProperty("hibernate.default_schema", this.defaultSchema);
        properties.setProperty("hibernate.show_sql", this.showSql.toString());
        properties.setProperty("hibernate.format_sql", this.formatSql.toString());
        properties.setProperty("hibernate.naming.physical-strategy", this.namingPhysicalStrategy);
        return properties;
    }
}