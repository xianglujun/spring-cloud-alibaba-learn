package org.spring.cloud.alibaba.learn.resource.server.entrypoint;

import cn.hutool.json.JSONUtil;
import org.spring.cloud.alibaba.learn.resource.server.data.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author xianglujun
 * @date 2023/6/12 10:50
 */
public class CustomEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (Objects.isNull(authException)) {
            return;
        }
        authException.printStackTrace();
        R<String> r = R.of(1, authException.getMessage(), null);
        response.getWriter().print(JSONUtil.toJsonStr(r));
    }
}
