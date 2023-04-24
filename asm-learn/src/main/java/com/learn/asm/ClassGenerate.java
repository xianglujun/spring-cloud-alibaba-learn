package com.learn.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

/**
 * @author xianglujun
 * @date 2023/4/21 15:41
 */
public class ClassGenerate {
    /**
     * 生成类似以下类型的class:<br/>
     * <p>
     * package pkg;
     * public interface Comparable extends Mesurable {
     * int LESS = -1;
     * int EQUAL = 0;
     * int GREATER = 1;
     * int compareTo(Object o);
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        TraceClassVisitor visitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));

        // 检查class是否合法
//        CheckClassAdapter checkClassAdapter = new CheckClassAdapter(visitor);

        visitor.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object", new String[]{}); // "pkg/Mesurable"
        visitor.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "LESS", "I", null, new Integer(-1));
        visitor.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "EQUAL", "I", null, new Integer(0));
        visitor.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "GREATER", "I", null, new Integer(1));

        visitor.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null);
        visitor.visitEnd();

        byte[] classBytes = classWriter.toByteArray();
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> clazz = classLoader.defineClass("pkg.Comparable", classBytes);
        System.out.println(clazz.getName());
    }
}
