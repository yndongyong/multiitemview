package com.yndongyong.widget.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.yndongyong.widget.multiitem.Items;

public class SecondActivity extends AppCompatActivity {


    private RecyclerView rv_list;
    private Items items = new Items();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",2));

        items.add(new NewsEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片2",2));
        items.add(new NewsEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片3", 2));
        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4", 2));
        items.add(new NewsEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5", 2));


        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4",2));
        items.add(new NewsEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5",2));
        items.add(new NewsEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6",2));

        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",2));
        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4",2));
        items.add(new NewsEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5",2));
        items.add(new NewsEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6",2));

        MultiAdapterHelper adapterHelper = new MultiAdapterHelper(this, rv_list, items);
    }
}
