package util;

public class Threple<L, M, R>{
    private final L l;
    private final M m;
    private final R r;
    public Threple(L l,M m, R r) {
        this.l = l;
        this.m = m;
        this.r = r;
    }

    public L getLeft(){
        return l;
    }

    public M getMiddle() {return m; }

    public R getRight(){
        return r;
    }
}
