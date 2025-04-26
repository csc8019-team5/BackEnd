package uk.ac.ncl.team5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Class: WebConfig
 * File: WebConfig.java
 * Created on: 2025/4/25
 * Author: yaomenghui
 *
 * Description:
 * <pre>
 *     Function: This class configures the web application's interceptor logic by
 *               implementing the WebMvcConfigurer interface. Specifically,
 *               it registers the JwtInterceptor to protect certain REST endpoints, enforcing JWT-based authentication.
 *
 *     Interface Description:
 *         - Calling Sequence: Spring automatically detects and uses this configuration class at runtime.
 *                             The addInterceptors method is called during the initialization phase.
 *         - Argument Description:
 *                              InterceptorRegistry registry: A registry object provided by Spring to
 *                              register custom interceptors and define their applicable URL patterns.
 *         - List of Subordinate Classes:
 *                              JwtInterceptor: validates JWT
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: menghui yao
 *     Reviewer: menghui yao
 *     Review Date: 2025/4/25
 *     Modification Date: 2025/4/25
 *     Modification Description: none
 * </pre>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns( "v1/reviews/selectReviewByUserId","v1/reviews/updateReviewByBookIdUserId","v1/reviews/deleteReviewByBookIdUserId") // intercept backend interface
                .excludePathPatterns( "v1/reviews/selectReviewByBookId"); // exclude intercept backend interface
    }
}