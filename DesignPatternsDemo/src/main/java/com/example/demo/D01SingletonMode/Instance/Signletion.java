package com.example.demo.D01SingletonMode.Instance;

/**
 * @author wang.pengfei
 * 设计单例模式
 */
public class Signletion implements DoSomeThing {

    private static volatile Signletion signletion = null;

    private static int count = 200;

    private Signletion() {
    }

    ;

    public static Signletion getInstance() {
        if (null == signletion) {
            synchronized (Signletion.class) {
                if (null == signletion) {
                    signletion = new Signletion();
                }
            }
        }
        return signletion;
    }

    @Override
    public void print() {
        System.out.println("Signletion " + signletion.hashCode() + "-----do a thing" + ", count:" + count);
        count--;
    }
}
