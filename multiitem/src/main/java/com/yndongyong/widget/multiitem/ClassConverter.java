package com.yndongyong.widget.multiitem;

import java.util.List;

/**
 * Created by dongzhiyong on 2017/10/7.
 */

public abstract class ClassConverter<T> implements Converter<T> {

    private List<ItemViewProvider> providers;

    public ClassConverter(List<ItemViewProvider> providers) {
        this.providers = providers;
    }

    @Override
    public int index(T entry) {
        int index = -1;
        for (int i=0,j=providers.size();i<j;i++) {
            ItemViewProvider provider = providers.get(i);
            if (provider.getClass().getSimpleName().equals(map(entry).getSimpleName())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new RuntimeException("%s cannot be in relative ItemViewProviders!!!");
        }
        return index;
    }

    public  abstract  Class<? extends ItemViewProvider> map(T entry) ;
}
