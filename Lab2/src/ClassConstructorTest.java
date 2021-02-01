import java.lang.reflect.Constructor;

public class ClassConstructorTest {

    public static void main(String args[]) throws Throwable {
        Class<ConstructorTest> ctClass = ConstructorTest.class;



        // creating an object by getting Constructor object (with parameters) and calling newInstance (with parameters) on it
        Constructor<?>[] constructor = ctClass.getConstructors();
        Object[] a = new Object[]{};
        ConstructorTest ctArgs = (ConstructorTest) constructor[0].newInstance(a);
        System.out.println("pri:" + ctArgs.getPri());
    }



        public class ConstructorTest {
            private String pri;

            public ConstructorTest() {
                this.pri = "hej";
            }

            /*public ConstructorTest(String pri) {
                this.pri = pri;
            }*/

            public String getPri() {
                return pri;
            }

            public void setPri(String pri) {
                this.pri = pri;
            }



        }
}