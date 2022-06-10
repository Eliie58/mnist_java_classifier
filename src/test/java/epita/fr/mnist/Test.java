package epita.fr.mnist;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        Test test = new Test();
        test.test();
    }

    public void test() throws IOException {

        new TaskBb().test();
        new TaskD().test();
        new TaskE().test();
        new TaskF().test();
        new TaskH().test();
        new TaskI().test();
    }
}
