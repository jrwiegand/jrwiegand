package jmodern;

import org.checkerframework.checker.nullness.qual.*;

public class Main {
    public static void main (String[] args) {
        String str1 = "hi";
        foo(str1); // we know str1 to be non-null

        String str2 = System.getProperty("foo");
        // foo(str2); // <-- doesn't compile as str2 may be null
        if (str2 != null)
            foo(str2); // after the null test it compiles
    }

    static void foo(@NonNull String s) {
        System.out.println("==> " + s.length());
    }
}
