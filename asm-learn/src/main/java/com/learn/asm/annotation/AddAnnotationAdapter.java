package com.learn.asm.annotation;

import com.learn.asm.MyClassLoader;
import org.objectweb.asm.*;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static org.objectweb.asm.Opcodes.ASM4;

/**
 * 实现向注解中加入注解
 *
 * @author xianglujun
 * @date 2023/4/25 14:22
 */
public class AddAnnotationAdapter extends ClassVisitor {

    private String annotationDesc;
    private boolean isAnnotationPresent;

    public AddAnnotationAdapter(String annotationDesc) {
        super(ASM4);
        this.annotationDesc = annotationDesc;
    }

    public AddAnnotationAdapter(ClassVisitor cv, String annotationDesc) {
        super(ASM4, cv);
        this.annotationDesc = annotationDesc;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        // 判断版本是否小于1.5
        int ver = (version & 0xFF) < Opcodes.V1_5 ? Opcodes.V1_5 : version;
        super.visit(ver, access, name, signature, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (visible && desc.equals(this.annotationDesc)) {
            isAnnotationPresent = true;
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        addAnnotation();
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        addAnnotation();
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        addAnnotation();
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        addAnnotation();
        super.visitEnd();
    }

    public void addAnnotation() {
        if (!isAnnotationPresent) {
            AnnotationVisitor annotationVisitor = super.visitAnnotation(this.annotationDesc, true);
            annotationVisitor.visit("desc", "test1111");
            if (annotationVisitor != null) {
                annotationVisitor.visitEnd();
            }
            this.isAnnotationPresent = true;
        }
    }

    public static void main(String[] args) {
        try {
            // 通过ClassReader获取String的class文件二进制
            ClassReader classReader = new ClassReader(AnnotationTest.class.getName());
            ClassWriter cw = new ClassWriter(classReader, 0);
            TraceClassVisitor traceClassVisitor = new TraceClassVisitor(cw, new PrintWriter(System.out));
            AddAnnotationAdapter addAnnotationAdapter = new AddAnnotationAdapter(traceClassVisitor, "Lcom/learn/asm/annotation/Anno;");
            classReader.accept(addAnnotationAdapter, 0);
            byte[] bytes = cw.toByteArray();
            MyClassLoader myClassLoader = new MyClassLoader();
            Class<?> clazz = myClassLoader.defineClass(AnnotationTest.class.getName(), bytes);
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.toString());
                if (annotation instanceof Anno) {
                    String desc = ((Anno) annotation).desc();
                    System.out.println(desc);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//@Anno(desc = "test")
class AnnotationTest {

    private Object obj;
}

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
@interface Anno {
    String desc();
}
