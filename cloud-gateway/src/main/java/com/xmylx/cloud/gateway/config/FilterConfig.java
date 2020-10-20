package com.xmylx.cloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FilterConfig {

    @Bean
    public RouteLocator addRequestHeaderRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("add_request_header_route", r->r.path("/cloud-web/test")
                        .filters(f->(f.addRequestHeader("X-Request-Acme","ValueB")))
                        .uri("lb:cloud-web"))
                .build();
    }

    //@Bean
    public RouteLocator addRequestParameterLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("add_request_parameter_route",r->r.path("/addParameter")
                        .filters(f->(f.addRequestParameter("example","ValueB")))
                        .uri("http://httpbin.org:80/get"))
                .build();
    }

    @Bean
    public RouteLocator rewritePathLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("rewrite_path_route",r->r.path("/foo/**")
                        .filters(f->(f.rewritePath("/foo/(?<segment>.*)","/$\\{segment}")))
                        .uri("http://www.baidu.com"))
                .build();
    }

    @Bean
    public RouteLocator addResponseHeaderRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("add_response_header_route", r->r.path("/response/test")
                        .filters(f->(f.addResponseHeader("X-Request-Foo","Bar")))
                        .uri("http://www.baidu.com"))
                .build();
    }

    @Bean
    public RouteLocator retryRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("retry_route", r->r.path("/retry/test")
                        .filters(f->f.retry(config->config.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
                        .uri("http://localhost:8001/retry?key=123&count=3"))
                .build();
    }
}
