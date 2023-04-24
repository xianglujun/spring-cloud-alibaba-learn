package com.learn.asm.field;

import org.objectweb.asm.*;

import java.io.IOException;
import java.util.Objects;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author xianglujun
 * @date 2023/4/23 11:27
 */
public class ExistingFieldVisitorAdapter extends ClassVisitor {

    private String fieldName;
    private boolean isPresent;
    private int acc;
    private String desc;

    public ExistingFieldVisitorAdapter(String fieldName, int acc, String desc) {
        super(ASM4);
        this.fieldName = fieldName;
        this.acc = acc;
        this.desc = desc;
    }

    public ExistingFieldVisitorAdapter(ClassVisitor cv, String fieldName, int acc, String desc) {
        super(ASM4, cv);
        this.fieldName = fieldName;
        this.acc = acc;
        this.desc = desc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (name.equals(fieldName)) {
            System.out.println("属性" + fieldName + "已经存在");
            this.isPresent = true;
        }
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!isPresent) {
            FieldVisitor fieldVisitor = super.visitField(this.acc, this.fieldName, this.desc, null, null);
            if (Objects.nonNull(fieldVisitor)) {
                System.out.println("属性" + fieldName + ".visitEnd方法已经被调用");
                fieldVisitor.visitEnd();
            }
        }
        super.visitEnd();
    }

    public static void main(String[] args) {
        try {
            ClassReader cr = new ClassReader(Demo.class.getName());
            ClassWriter cw = new ClassWriter(cr, 0);
            ExistingFieldVisitorAdapter adapter = new ExistingFieldVisitorAdapter(cw, "name1", Opcodes.ACC_PRIVATE, "Ljava/lang/String");
            cr.accept(adapter, 0);
            cw.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Demo {
        private String name;
        private Integer age;
    }
}
