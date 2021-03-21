package com.hulk.androidstudy.java_base.jvm;

/**
 * Java1.6以前代码才有效
 * Java虚拟机栈和本地方法栈溢出
 * VM Args : -Xss128K
 */
public class JavaVMStackOF {
    private int stackLength = 1;

    /**
     * 无线递归，方法调用深度超出虚拟机允许的最大深度，StackOverflowError异常
     * 虚拟机扩展无法申请到足够的内存空间，OOM异常
     */
    public void stackLeak() {
        //记录深度
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackOF oom = new JavaVMStackOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }


}
