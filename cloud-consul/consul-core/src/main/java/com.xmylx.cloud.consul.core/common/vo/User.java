package com.xmylx.cloud.consul.core.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -1795480940170829150L;

    public final static String CONTEXT_KEY_USERID = "x-user-id";

    private String userId;
    private String userName;

    private List<String > allowPermissionService;
}
