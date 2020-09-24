package com.xmylx.cloud.sleuth.consumer;

import brave.baggage.BaggageField;
import brave.baggage.BaggagePropagation;
import brave.propagation.ExtraFieldPropagation;
import org.springframework.cloud.sleuth.instrument.web.SleuthWebProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 5+1)
public class SessionFilter extends GenericFilterBean {

    private Pattern skipPattern=Pattern.compile(SleuthWebProperties.DEFAULT_SKIP_PATTERN);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if(!(request instanceof HttpServletRequest)||!(response instanceof HttpServletResponse)) {
            throw new ServletException("Filter just supports HTTP requests");
        }
        HttpServletRequest httpRequest=(HttpServletRequest)request;
        boolean skip=skipPattern.matcher(httpRequest.getRequestURI()).matches();
        if(!skip){
            ExtraFieldPropagation.set("SessionId", httpRequest.getSession().getId());
        }
        filterChain.doFilter(request,response);
    }
}
