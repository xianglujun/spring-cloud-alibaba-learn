package org.spring.cloud.alibaba.learn.sentinel.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.ClusterFlowConfig;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author xianglujun
 * @date 2023/5/9 17:11
 */
@Service
public class BlockSentinelRuleLifecycle implements SmartLifecycle {

    @Value("${spring.application.name}")
    private String resourceName;

    @Override
    public void start() {
        System.out.println("加载sentinel流控制");
        FlowRule flowRule = new FlowRule();
        flowRule.setClusterMode(false);
        // 设置qps的值为3
        flowRule.setCount(3);
        // 设置qps
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 限制app, 可以指定某个app限流，对所有生效可以设置为default
        flowRule.setLimitApp("default");
        flowRule.setClusterConfig(new ClusterFlowConfig());
        // 设置需要限流的资源名称
        flowRule.setResource("sayHello");
        flowRule.setRefResource(resourceName);

        FlowRuleManager.loadRules(Arrays.asList(flowRule));
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
