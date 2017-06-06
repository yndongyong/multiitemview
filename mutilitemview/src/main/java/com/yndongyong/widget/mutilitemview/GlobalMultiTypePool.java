package com.yndongyong.widget.mutilitemview;

/**
 * Created by dongzhiyong on 2017/6/6.
 */

class GlobalMultiTypePool {

    private static final GlobalMultiTypePool ourInstance = new GlobalMultiTypePool();

    static GlobalMultiTypePool getInstance() {
        return ourInstance;
    }


    private ITypePool typePool;

    private GlobalMultiTypePool() {
        typePool = new MultiTypePool();
        typePool.register(HeaderEntry.class, new HeaderViewProvider());
    }

    public ITypePool applyGlobalMultiPool() {
        return typePool;
    }



}
