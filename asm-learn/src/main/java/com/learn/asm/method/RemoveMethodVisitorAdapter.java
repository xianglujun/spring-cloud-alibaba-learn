package com.learn.asm.method;

import org.objectweb.asm.*;

import java.io.IOException;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * 通过代理的方式删除方法
 *
 * @author xianglujun
 * @date 2023/4/23 10:51
 */
public class RemoveMethodVisitorAdapter extends ClassVisitor {

    private String methodName;
    private String mDesc;

    public RemoveMethodVisitorAdapter(String methodName, String mDesc) {
        super(ASM4);
        this.methodName = methodName;
        this.mDesc = mDesc;
    }

    public RemoveMethodVisitorAdapter(ClassVisitor cv, String methodName, String mDesc) {
        super(ASM4, cv);
        this.methodName = methodName;
        this.mDesc = mDesc;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        // 当我们需要删除某个方法的时候，就直接返回null即可，不继续调用以下的visitor方法
        if (name.equals(this.methodName) && desc.equals(this.mDesc)) {
            return null;
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    public static void main(String[] args) {
        String methodName = "run";
        String desc = "()V";
        try {
            ClassReader classReader = new ClassReader(Runnable.class.getName());
            ClassWriter classWriter = new ClassWriter(classReader, 0);
            RemoveMethodVisitorAdapter adapter = new RemoveMethodVisitorAdapter(classWriter, methodName, desc);
            classReader.accept(adapter, 0);
            MethodVisitor methodVisitor = adapter.visitMethod(Opcodes.ACC_PUBLIC, "run", desc, null, null);
            System.out.println(methodVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
