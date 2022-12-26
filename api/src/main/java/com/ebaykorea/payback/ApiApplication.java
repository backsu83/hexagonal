package com.ebaykorea.payback;

import com.ebaykorea.payback.config.properties.ApiInfoProperties;
import com.ebaykorea.saturn.component.core.config.properties.SaturnApplicationProperties;
import com.ebaykorea.saturn.datasource.EnableSaturnDataSource;
import com.ebaykorea.saturn.moa.EnableMoA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableMoA
@EnableSaturnDataSource
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties({
    ApiInfoProperties.class,
    SaturnApplicationProperties.class
})
public class ApiApplication {
    @Autowired
    SaturnApplicationProperties saturnApplicationProperties;

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}


