package com.mishelon.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mishelon
 * @since 5 feb 2022
 */

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
public class DemoConfiguration {

}
