package com.learn.asm.method;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.*;

/**
 * @author xianglujun
 * @date 2023/4/24 14:51
 */
public class AddTimerMethodAdapter extends MethodVisitor {

    private String ownerName;

    public AddTimerMethodAdapter(String ownerName) {
        super(ASM4);
        this.ownerName = ownerName;
    }

    public AddTimerMethodAdapter(MethodVisitor mv, String ownerName) {
        super(ASM4, mv);
        this.ownerName = ownerName;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        // 定义 public static Long timer
        mv.visitFieldInsn(GETSTATIC, this.ownerName, "timer", "J");
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
        mv.visitInsn(Opcodes.LSUB);
        mv.visitFieldInsn(Opcodes.PUTSTATIC, this.ownerName, "timer", "J");
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            mv.visitFieldInsn(GETSTATIC, this.ownerName, "timer", "J");
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System",
                    "currentTimeMillis", "()J");
            mv.visitInsn(LADD);
            mv.visitFieldInsn(PUTSTATIC, this.ownerName, "timer", "J");
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack + 4, maxLocals);
    }
}
