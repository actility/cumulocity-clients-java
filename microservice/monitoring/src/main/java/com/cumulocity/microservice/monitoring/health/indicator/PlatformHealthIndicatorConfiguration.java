package com.cumulocity.microservice.monitoring.health.indicator;

import com.cumulocity.microservice.context.ContextService;
import com.cumulocity.microservice.context.credentials.MicroserviceCredentials;
import com.cumulocity.microservice.monitoring.health.annotation.EnableHealthIndicator;
import com.cumulocity.microservice.monitoring.health.indicator.platform.PlatformHealthIndicator;
import com.cumulocity.microservice.monitoring.health.indicator.platform.PlatformHealthIndicatorProperties;
import com.cumulocity.microservice.subscription.model.core.PlatformProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnBean(value = Object.class, annotation = EnableHealthIndicator.class)
@ConditionalOnProperty(value = "management.health.platform.enabled", havingValue = "true")
@EnableConfigurationProperties(PlatformHealthIndicatorProperties.class)
public class PlatformHealthIndicatorConfiguration {

    @Bean
    @ConditionalOnBean({PlatformProperties.class, ContextService.class})
    public PlatformHealthIndicator platformHealthIndicator(
            ContextService<MicroserviceCredentials> microservice,
            PlatformProperties properties,
            PlatformHealthIndicatorProperties configuration) {
        return new PlatformHealthIndicator(microservice, properties, configuration);
    }
}
