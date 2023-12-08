package hu.ak_akademia.algorithms.basics;

public class MultipleStackReferenceDemo {

    public static void main(String[] args) {
        int a = 7;
        foo(a);
        System.out.println(a);
        Bar bar = new Bar();
        bar.n = 7;
        foo2(bar);
        System.out.println(bar.n);
    }

    static void foo(int n) {
        n++;
        System.out.println(n);
    }

    static void foo2(Bar bar) {
        bar.n++;
        System.out.println(bar.n);
    }

    private static final class Bar {
        private int n;
    }
}
