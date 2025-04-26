package uk.ac.ncl.team5project.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @file MyBatisPlusConfig.java
 * @date 2025-04-01
 * @function_description: Configuration class for MyBatis-Plus, enabling pagination support.
 * @interface_description: Defines MybatisPlusInterceptor bean and enables automatic scanning for mapper interfaces.
 * @calling_sequence: Spring Boot auto-configuration → Load this config → Enable interceptor
 * @arguments_description: None
 * @list_of_subordinate_classes: MybatisPlusInterceptor, PaginationInnerInterceptor
 * @discussion: Ensures that MyBatis-Plus pagination plugin works correctly for MySQL.
 * @development_history: Created on 2025-04-01as part of database configuration module
 * @designer: wensi huang
 * @reviewer: wensi huang
 * @review_date: 2025-04-18
 * @modification_date: 2025-04-18
 * @description: Registers MyBatis-Plus interceptor for pagination with MySQL support.
 */
@Configuration
@MapperScan("uk.ac.ncl.team5project.mapper")
public class MyBatisPlusConfig {

    /**
     * Registers the MyBatis-Plus interceptor bean with pagination support.
     * Make sure pagination is added last if combining with other interceptors.
     * @return configured MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
