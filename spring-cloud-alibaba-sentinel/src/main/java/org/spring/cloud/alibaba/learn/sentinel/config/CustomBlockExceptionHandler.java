package org.spring.cloud.alibaba.learn.sentinel.config;

import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.spring.cloud.alibaba.learn.sentinel.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * @author xianglujun
 * @date 2023/5/9 15:20
 */
@Service
public class CustomBlockExceptionHandler implements BlockExceptionHandler {

    private SentinelProperties sentinelProperties;

    public CustomBlockExceptionHandler(SentinelProperties sentinelProperties) {
        this.sentinelProperties = sentinelProperties;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        if (isAjax(request)) {
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(R.fail("Oops, 被限流了"));
        } else {
            response.sendRedirect(this.sentinelProperties.getBlockPage());
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (Objects.nonNull(header) && header.indexOf("XMLHttpRequest") > -1) {
            return true;
        }

        header = request.getHeader("accept");
        return Objects.nonNull(header)
                && header.indexOf("application/json") > -1;
    }
}
