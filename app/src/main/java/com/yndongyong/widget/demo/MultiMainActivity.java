package com.yndongyong.widget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.yndongyong.widget.mutilitemview.DYRecyclerView;
import com.yndongyong.widget.mutilitemview.HeaderEntry;
import com.yndongyong.widget.mutilitemview.HeaderViewProvider;
import com.yndongyong.widget.mutilitemview.Items;
import com.yndongyong.widget.mutilitemview.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiMainActivity extends AppCompatActivity {

    private DYRecyclerView rv_list;

    private Items items = new Items();
    private MultiTypeAdapter multiTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nulti_main);
        rv_list = (DYRecyclerView) findViewById(R.id.rv_list);

        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片2"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片3"));
        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));
        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

        multiTypeAdapter = new MultiTypeAdapter(items);
        rv_list.addHeaderView(new HeaderEntry("refreshTip","normalTips"), new HeaderViewProvider());
        rv_list.addFooterView(new HeaderEntry("refreshTip","normalTips"), new HeaderViewProvider());

        multiTypeAdapter.register(Category2Entry.class, new Category2EntryItemViewProvider());
        rv_list.setAdapter(multiTypeAdapter);

//        multiTypeAdapter.register(Category3Entry.class, new Category3EntityItemViewProvider());

    }

    private void fakeData() {
        for (int i=0;i<20;i++) {
            items.add("Item" + i);
        }
    }
}
