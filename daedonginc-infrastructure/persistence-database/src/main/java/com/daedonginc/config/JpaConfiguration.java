package com.daedonginc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.daedonginc.Persistence;

/**
 * @author domo
 * Created on 2023/03/23
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
		basePackageClasses = {Persistence.class}
)
public class JpaConfiguration {
}
