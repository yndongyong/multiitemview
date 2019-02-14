package com.yndongyong.widget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.yndongyong.adapter.Items;
import com.yndongyong.adapter.SimpleAdapter;
import com.yndongyong.widget.demo.entities.Category3Entry;
import com.yndongyong.widget.demo.entities.CategoryEntry;
import com.yndongyong.widget.demo.viewproviders.Category1EntryItemViewProvider;
import com.yndongyong.widget.demo.viewproviders.Category2EntryItemViewProvider;
import com.yndongyong.widget.demo.viewproviders.Category3EntryItemViewProvider;
import com.yndongyong.widget.demo.viewproviders.HeaderItemViewProvider;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_list;

    private Items items = new Items();
    private SimpleAdapter multiTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        items.add("头部1 -- type1");
        items.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054128121&di=4288f93d40b58291f2fd45419ffc39dd&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F18%2F2b5f1b6298411b0045c5562da02fc6ac.jpg", "风景图片1"));
        items.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184708&di=16874780ddd31e1b429acbba458061cb&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg", "风景图片2"));

        items.add("头部2 -- type2");
        items.add(new CategoryEntry("", "风景图片4", 2));
        items.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184708&di=16874780ddd31e1b429acbba458061cb&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg", "风景图片5", 2));
        items.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184708&di=16874780ddd31e1b429acbba458061cb&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg", "风景图片5", 2));

//        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6", 2));


        Category3Entry category3Entry = new Category3Entry();
        List<CategoryEntry> categoryEntries = new ArrayList<>();
        categoryEntries.add(new CategoryEntry("", "风景图片1", 2));
        categoryEntries.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184708&di=16874780ddd31e1b429acbba458061cb&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg", "风景图片2", 2));
        categoryEntries.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片3", 2));
        categoryEntries.add(new CategoryEntry("",
                "风景图片4", 2));
        categoryEntries.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184511&di=c2ec0c012b749893e8e816fab94c5674&imgtype=0&src=http%3A%2F%2Fpic26.nipic.com%2F20121225%2F6440111_111538500168_2.jpg",
                "风景图片5", 2));
        categoryEntries.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg",
                "风景图片6", 2));
        category3Entry.setCategoryEntries(categoryEntries);

        items.add("头部6 -- type3");
        items.add(category3Entry);

        items.add("头部3 -- type1");
        items.add(new CategoryEntry("", "风景图片4"));
        items.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184708&di=16874780ddd31e1b429acbba458061cb&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg", "风景图片5"));
        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));


        items.add("头部4 -- type2");
        items.add(new CategoryEntry("",
                "风景图片4", 2));
        items.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184708&di=16874780ddd31e1b429acbba458061cb&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg",
                "风景图片5", 2));
        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg",
                "风景图片6", 2));

        items.add("头部5 -- type1");
        items.add(new CategoryEntry("", "风景图片4"));
        items.add(new CategoryEntry("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1550054184708&di=16874780ddd31e1b429acbba458061cb&imgtype=0&src=http%3A%2F%2Fpic30.photophoto.cn%2F20140115%2F0040039504628208_b.jpg", "风景图片5"));
        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));


        /**
         * 方式一
         */
        multiTypeAdapter = new SimpleAdapter(this);
        multiTypeAdapter.addNewData(items);

        multiTypeAdapter.register(new HeaderItemViewProvider());
        multiTypeAdapter.register(new Category1EntryItemViewProvider());
        multiTypeAdapter.register(new Category2EntryItemViewProvider());
        multiTypeAdapter.register(new Category3EntryItemViewProvider());
        rv_list.setAdapter(multiTypeAdapter);

        /**
         * 局部更新
         */
        /*multiTypeAdapter.updateItemsWithNotify(items, new SimpleDiff.CallBack<CategoryEntry>() {
            @Override
            public boolean areItemsTheSame(CategoryEntry oldItem, CategoryEntry newItem) {
                return oldItem.getUrl().equals(newItem.getUrl());
            }

            @Override
            public boolean areContentsTheSame(CategoryEntry oldItem, CategoryEntry newItem) {
                if (oldItem.getUrl().equals(newItem.getUrl()) || oldItem.getDescription().equals(newItem.getDescription())) {
                    return true;
                }
                return false;
            }

            @Override
            public Bundle getChangePayload(CategoryEntry oldItem, CategoryEntry newItem) {
                Bundle bundle = new Bundle();
                bundle.putString("Description", newItem.getDescription());
                bundle.putString("Url", newItem.getUrl());
                return bundle;
            }
        });*/

    }

}
