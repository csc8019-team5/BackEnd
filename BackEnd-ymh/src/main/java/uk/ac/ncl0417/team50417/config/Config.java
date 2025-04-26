package uk.ac.ncl0417.team50417.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * Class: Config
 * File: Config.java
 * Created on: 2025/4/25
 * Author: menghui yao
 *
 * Description: We need to configure CORS to allow frontend access.
 * <pre>
 *     Function: CORS Configuration
 *     Interface Description:
 *         - Calling Sequence:
 *                              This configuration is automatically applied by Spring at runtime. It doesn't require
 *                              explicit invocation; the CORS filter is registered as a Spring Bean.
 *         - Argument Description: none
 *         - List of Subordinate Classes:
 *                              CorsFilter: A filter that handles the actual CORS logic, enforcing the CORS policy.
 *                              CorsConfiguration: The configuration class that defines the allowed origins, headers,
 *                                                 and methods for CORS requests.
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
public class Config {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
