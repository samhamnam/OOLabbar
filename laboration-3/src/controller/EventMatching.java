package controller;

import util.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class EventMatching<key> {
    private HashMap<key, func> dicc; // ;)

    public EventMatching(HashMap<key,func> hm){
        dicc = hm;
    }

    public EventMatching(key[] keys) {

        dicc = new HashMap<>();
        for (key key : keys) {
            dicc.put(key,x -> {});
        }
    }
    public void assign(key key, func f){
        dicc.replace(key,f);
    }
    public void asignAll(Tuple<key,func>[] ts){
        for(Tuple<key, func> t :ts){
            assign(t.getLeft(),t.getRight());
        }
    }
    public func getFunc(key key){
        return dicc.get(key);
    }


    interface func {
        void apply(double d);
    }
/*
    void test() {
        ArrayList<func> l = new ArrayList<>();
        l.add(x -> {
                }
        );
    }

 */

}
