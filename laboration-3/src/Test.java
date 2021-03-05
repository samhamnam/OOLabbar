public class Test {
    public static void main(String[] args){
        Dummy a = new Dummy(5);
        Dummy b = a;
        System.out.println("a: " + a.x  +" b: "+ b.x);

        a = new Dummy(6);
        System.out.println("a: " + a.x  +" b: "+ b.x);
    }

    static class Dummy{
        public int x = 0;
        public Dummy(int x){
            this.x = x;
        }
    }
}
