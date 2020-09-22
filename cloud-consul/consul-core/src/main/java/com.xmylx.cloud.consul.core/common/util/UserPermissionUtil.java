package com.xmylx.cloud.consul.core.common.util;

import com.xmylx.cloud.consul.core.common.vo.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserPermissionUtil {

    /**
     * 模拟权限校验, 可以根据自己项目需要定制不同的策略,如查询数据库获取具体的菜单url或者角色等等
     * @param user
     * @param request
     * @return
     */
    public static boolean verify(User user, HttpServletRequest request) {
        String url=request.getHeader("x-user-serviceName");
        if(StringUtils.isEmpty(user)){
            return false;
        }else{
            List<String> str=user.getAllowPermissionService();
            for(String permissionService:str){
                if(url.equalsIgnoreCase(permissionService)){
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * 服务访问权限设置
     * @param user
     */
    public static void permission(User user) {
        List<String> allowPermissionService=new ArrayList<>();
        if("admin".equals(user.getUserName())){
            allowPermissionService.add("consul-consumer");
            allowPermissionService.add("consul-provider");
        }else if("spring".equals(user.getUserName())){
            allowPermissionService.add("consul-consumer");
        }
        user.setAllowPermissionService(allowPermissionService);
    }
}
