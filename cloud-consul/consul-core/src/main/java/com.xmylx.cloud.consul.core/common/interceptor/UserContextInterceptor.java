package com.xmylx.cloud.consul.core.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.xmylx.cloud.consul.core.common.util.UserPermissionUtil;
import com.xmylx.cloud.consul.core.common.vo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserContextInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user=getUser(request);
        UserPermissionUtil.permission(user);
        if(!UserPermissionUtil.verify(user,request)){
            response.addHeader("Content-Type","application/json");
            String jsonStr= JSON.toJSONString("no permission access service,please check");
            response.getWriter().write(jsonStr);
            response.getWriter().flush();
            response.getWriter().close();
            throw new PermissionException("no permisson access service, please check");
        }
        UserContextHolder.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }


    private User getUser(HttpServletRequest request){
        String id=request.getHeader("x-user-id");
        String name=request.getHeader("x-user-name");
        return User.builder()
                .userId(id)
                .userName(name)
                .build();
    }


    static class PermissionException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public PermissionException(String msg) {
            super(msg);
        }
    }
}
