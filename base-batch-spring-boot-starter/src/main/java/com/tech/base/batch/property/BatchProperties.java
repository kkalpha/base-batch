package com.tech.base.batch.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:base-config.properties"})
@Component
@ConfigurationProperties(prefix = "base.batch")
public class BatchProperties {

}
