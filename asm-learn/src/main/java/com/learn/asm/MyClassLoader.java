package com.learn.asm;

/**
 * @author xianglujun
 * @date 2023/4/23 10:01
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
