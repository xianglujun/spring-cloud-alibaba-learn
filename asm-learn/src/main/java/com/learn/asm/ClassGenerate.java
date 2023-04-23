package com.learn.asm;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

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
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object", new String[]{"pkg/Mesurable"});
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "LESS", "I", null, new Integer(-1));
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "EQUAL", "I", null, new Integer(0));
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL, "GREATER", "I", null, new Integer(1));

        classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null);
        classWriter.visitEnd();

        System.out.println(Arrays.toString(classWriter.toByteArray()));
    }
}
