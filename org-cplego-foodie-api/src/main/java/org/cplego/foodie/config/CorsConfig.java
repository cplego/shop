package org.cplego.foodie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig(){}
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();

      //  config.addAllowedOrigin("http://localhost:8080");
       config.addAllowedOrigin("http://www.foodie.com");
       config.addAllowedOrigin("http://www.foodie.com:80");
        config.addAllowedOrigin("*");
        //设置是否发送cooies信息
        config.setAllowCredentials(true);
        //设置允许请求的参数
        config.addAllowedMethod("*");
        //设置允许的header
        config.addAllowedHeader("*");

        //为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);

        return new CorsFilter(corsSource);


    }

}
