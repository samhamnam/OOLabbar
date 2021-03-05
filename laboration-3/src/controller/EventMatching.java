package controller;

import util.Tuple;

import java.util.HashMap;

public class EventMatching<Key, Func> {
    private HashMap<Key, Func> dicc; // ;)

    public EventMatching(HashMap<Key, Func> hm){
        dicc = hm;
    }

    public EventMatching(Key[] keys) {
        dicc = new HashMap<>();
        for (Key key : keys) {
            dicc.put(key,null);
        }
    }
    public void assign(Key key, Func f){
        dicc.replace(key,f);
    }
    public void assignAll(Tuple<Key, Func>[] ts){
        for(Tuple<Key, Func> t :ts){
            assign(t.getLeft(),t.getRight());
        }
    }
    public Func getFunc(Key key){
        return dicc.get(key);
    }
/*


 */

/*
    void test() {
        ArrayList<func> l = new ArrayList<>();
        l.add(x -> {
                }
        );
    }

 */

}
