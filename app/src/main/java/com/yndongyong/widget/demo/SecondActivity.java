package com.yndongyong.widget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.yndongyong.adapter.Items;
import com.yndongyong.widget.demo.entities.NewsEntry;

/**
 * recycleview 实现复杂首页-多种格式的布局
 */
public class SecondActivity extends AppCompatActivity {

    private RecyclerView rv_list;
    private Items items = new Items();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rv_list =  findViewById(R.id.rv_list);

        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));

        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));


        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));


        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));


        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));


        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));


        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));


        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));



        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));



        items.add(new NewsEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1",1));

        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));
        items.add(new NewsEntry("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1545370824,4013464876&fm=26&gp=0.jpg", "风景图片5", 2));

        MultiAdapterHelper adapterHelper = new MultiAdapterHelper(this, rv_list, items);
    }
}
