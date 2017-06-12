package com.yndongyong.widget.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.yndongyong.widget.multiitemview.Items;
import com.yndongyong.widget.multiitemview.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_list;

    private Items items = new Items();
    private MultiTypeAdapter multiTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        items.add("头部1");
        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片2"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片3"));


        items.add("头部2");
        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));


        items.add("头部3");
        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

        items.add("头部6");

        Category3Entry entry = new Category3Entry();
        List<Category2Entry> datas = new ArrayList<>();

        datas.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        datas.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        datas.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

        datas.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        datas.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        datas.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

        datas.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        datas.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        datas.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

        entry.setDatas(datas);
        items.add(entry);

        items.add("头部4");
        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

        items.add("头部5");
        items.add(new Category2Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category2Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category2Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));




        multiTypeAdapter = new MultiTypeAdapter(items);
        multiTypeAdapter.register(String.class, new StringItemViewProvider());
        multiTypeAdapter.register(Category2Entry.class, new Category2EntryItemViewProvider());


        multiTypeAdapter.register(Category3Entry.class, new Category3EntityItemViewProvider());

        rv_list.setAdapter(multiTypeAdapter);
    }

    private void fakeData() {
        for (int i=0;i<20;i++) {
            items.add("Item" + i);
        }
    }
}
