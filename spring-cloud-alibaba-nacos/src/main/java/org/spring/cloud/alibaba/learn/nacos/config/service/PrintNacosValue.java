package org.spring.cloud.alibaba.learn.nacos.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author xianglujun
 * @date 2023/4/28 10:05
 */
@Service
public class PrintNacosValue {

    @Autowired
    private NacosValueService nacosValueService;
    private Thread thread;

    @PostConstruct
    public void print() {
        System.out.println("执行初始化函数....");
        thread = new Thread(() -> {
            while (true) {
                System.out.println("age值为:" + nacosValueService.getAge());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        });
        thread.start();
    }
}
