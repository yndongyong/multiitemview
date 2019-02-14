package com.yndongyong.adapter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by yndongyong on 2017/5/28.
 */

public class Items extends ArrayList<Object> {

    public Items(int initialCapacity) {
        super(initialCapacity);
    }

    public Items() {
        super();
    }

    public Items(@NonNull Collection<?> c) {
        super(c);
    }
}