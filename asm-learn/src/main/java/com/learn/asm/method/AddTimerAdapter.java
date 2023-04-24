package com.learn.asm.method;

import com.learn.asm.MyClassLoader;
import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author xianglujun
 * @date 2023/4/24 14:40
 */
public class AddTimerAdapter extends ClassVisitor {

    private boolean isInterface;
    private List<String> addedMethods = new ArrayList<>();
    private String ownerName;

    public AddTimerAdapter() {
        super(ASM4);
    }

    public AddTimerAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.ownerName = name;
        this.isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (!isInterface
                && Objects.nonNull(mv)
                && !name.equals("<init>")) {
            mv = new AddTimerMethodAdapter(mv, this.ownerName);
            addedMethods.add(name);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        if (!isInterface) {
            FieldVisitor fv = super.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "timer", "J", null, null);
            if (Objects.nonNull(fv)) {
                fv.visitEnd();
            }
        }
        super.visitEnd();
    }

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        ClassReader cr = new ClassReader(Demo.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(cw, new PrintWriter(System.out));
        cr.accept(new AddTimerAdapter(traceClassVisitor), 0);

        byte[] bytes = cw.toByteArray();
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.defineClass(Demo.class.getName(), bytes);
        Object obj = clazz.newInstance();
        Method invoke = clazz.getDeclaredMethod("invoke", null);
        invoke.invoke(obj, null);

        Field timer = clazz.getDeclaredField("timer");
        System.out.println("时间: " + timer.get(null));
    }

    public static class Demo {
        public void invoke() {
            for (int i = 100; i < 100000; i++) {
                System.out.println(i);
            }
        }
    }
}
