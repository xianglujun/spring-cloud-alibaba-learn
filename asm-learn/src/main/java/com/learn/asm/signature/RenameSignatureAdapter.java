package com.learn.asm.signature;

import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.signature.SignatureVisitor;
import org.objectweb.asm.signature.SignatureWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.objectweb.asm.Opcodes.ASM4;

/**
 * @author xianglujun
 * @date 2023/4/25 10:45
 */
public class RenameSignatureAdapter extends SignatureVisitor {

    private Map<String, String> renaming;
    private SignatureVisitor sv;
    private String oldName;

    public RenameSignatureAdapter(SignatureVisitor sv, Map<String, String> renaming) {
        super(ASM4);
        this.renaming = renaming;
        this.sv = sv;
    }

    @Override
    public void visitFormalTypeParameter(String name) {
        super.visitFormalTypeParameter(name);
    }

    @Override
    public SignatureVisitor visitClassBound() {
        sv.visitClassBound();
        return this;
    }

    @Override
    public SignatureVisitor visitInterfaceBound() {
        sv.visitInterfaceBound();
        return super.visitInterfaceBound();
    }

    @Override
    public void visitClassType(String name) {
        this.oldName = name;
        String newName = renaming.get(this.oldName);
        sv.visitClassType(Objects.isNull(newName) ? name : newName);
    }

    @Override
    public void visitInnerClassType(String name) {
        System.out.println("获取内部类的泛型信息: " + name);
        oldName = oldName + "." + name;
        String newName = renaming.get(oldName);
        sv.visitInnerClassType(Objects.isNull(newName) ? name : newName);
    }

    @Override
    public void visitTypeArgument() {
        sv.visitTypeArgument();
    }

    @Override
    public SignatureVisitor visitTypeArgument(char wildcard) {
        return sv.visitTypeArgument(wildcard);
    }

    @Override
    public void visitEnd() {
        sv.visitEnd();
    }

    public static void main(String[] args) {
        String s = "Ljava/util/HashMap<TK;TV;>.HashIterator<TK;>;";
        Map<String, String> renaming = new HashMap<String, String>();
        renaming.put("java/util/HashMap", "A");
        renaming.put("java/util/HashMap.HashIterator", "B");
        SignatureWriter sw = new SignatureWriter();
        SignatureVisitor sa = new RenameSignatureAdapter(sw, renaming);
        SignatureReader sr = new SignatureReader(s);
        sr.acceptType(sa);
        System.out.println(sw);
    }
}
