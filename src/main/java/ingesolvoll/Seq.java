package ingesolvoll;

import clojure.java.api.Clojure;
import clojure.lang.*;

import java.util.List;

class Seq extends ASeq {
    private List lst;

    Seq(List lst) {
        this.lst = lst;
    }

    @Override
    public Obj withMeta(IPersistentMap iPersistentMap) {
        IFn _with_meta = Clojure.var("clojure.core", "with-meta");
        return (Obj) _with_meta.invoke(this, iPersistentMap);
    }

    @Override
    public Object first() {
        if (lst.size() > 0) {
            return lst.get(0);
        }
        return null;
    }

    @Override
    public ISeq next() {
        if (lst.size() > 1) {
            return new Seq(lst.subList(1, lst.size()));
        }
        return null;
    }
}