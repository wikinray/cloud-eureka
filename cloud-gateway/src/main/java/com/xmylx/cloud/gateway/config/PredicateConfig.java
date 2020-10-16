package com.xmylx.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
public class PredicateConfig {

    /**
     * After Route Predicate Factory
     * @param builder
     * @return
     */
    //@Bean
    public RouteLocator afterRouteLocator(RouteLocatorBuilder builder){
        ZonedDateTime zonedDateTime= LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        return builder.routes()
                .route("after_route",r->r.after(zonedDateTime).uri("http://www.baidu.com"))
                .build();
    }

    /**
     * Before Route Predicate Factory
     * @param builder
     * @return
     */
    //@Bean
    public RouteLocator beforeRouteLocator(RouteLocatorBuilder builder){
        ZonedDateTime zonedDateTime= LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault());
        return builder.routes()
                .route("before_route",r->r.before(zonedDateTime).uri("http://www.jd.com"))
                .build();
    }

    /**
     * Between Route Predicate Factory
     * @param builder
     * @return
     */
    //@Bean
    public RouteLocator betweenRouteLocator(RouteLocatorBuilder builder){
        ZonedDateTime zonedDateTime1= LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2= LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault());
        return builder.routes()
                .route("between_route",r->r.between(zonedDateTime1,zonedDateTime2).uri("http://www.jd.com"))
                .build();
    }

    /**
     * Cookie Route Predicate Factory
     * @param builder
     * @return
     */
    //@Bean
    public RouteLocator cookieRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("cookie_route",r->r.cookie("chocolate","ch.p").uri("http://www.jd.com"))
                .build();
    }

    /**
     * Header Route Predicate Factory
     * @param builder
     * @return
     */
    //@Bean
    public RouteLocator headerRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("header_route",r->r.header("X-request-Id","token").uri("http://www.jd.com"))
                .build();
    }

    /**
     * Host Route Predicate Factory
     * @param builder
     * @return
     */
    //@Bean
    public RouteLocator hostRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("host_route",r->r.host("**.baidu.com:8080").uri("http://www.jd.com"))
                .build();
    }

    /**
     * Method Route Predicate Factory
     * @param builder
     * @return
     */
    //@Bean
    public RouteLocator methodRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("method_route",r->r.method("POST").uri("http://www.jd.com"))
                .build();
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()));
    }
}
