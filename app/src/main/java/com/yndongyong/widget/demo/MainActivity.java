package com.yndongyong.widget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.yndongyong.widget.multiitem.ClassConverter;
import com.yndongyong.widget.multiitem.Converter;
import com.yndongyong.widget.multiitem.ItemViewProvider;
import com.yndongyong.widget.multiitem.Items;
import com.yndongyong.widget.multiitem.SimpleAdapter;
import com.yndongyong.widget.multiitem.SimpleDiff;

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
        items.add(new CategoryEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1"));
        items.add(new CategoryEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片2"));
        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片3"));


        items.add("头部2 -- type2");
        items.add(new CategoryEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4",2));
        items.add(new CategoryEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5",2));

        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6",2));


        items.add("头部3 -- type1");
        items.add(new CategoryEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new CategoryEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));


        items.add("头部4 -- type2" );
        items.add(new CategoryEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg",
                "风景图片4", 2));
        items.add(new CategoryEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg",
                "风景图片5", 2));
        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg",
                "风景图片6", 2));

        items.add("头部5 -- type1");
        items.add(new CategoryEntry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new CategoryEntry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new CategoryEntry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

        List<ItemViewProvider> providers = new ArrayList<>();
        providers.add(new Category1EntryItemViewProvider());
        providers.add(new Category4EntryItemViewProvider());

        /**
         * 方式一
         */
       /* multiTypeAdapter = SimpleAdapter.create(this)
                .addNewData(items)
                .register(String.class, new HeaderItemViewProvider())
                .register(CategoryEntry.class, providers, new Converter<CategoryEntry>() {
                    @Override
                    public int index(CategoryEntry entry) {
                        if (entry.getType() == 1)
                            return 0;
                        else
                            return 1;
                    }
                }).attachToRecyclerView(rv_list);*/
        /**
         * 方式二
         */
        multiTypeAdapter = SimpleAdapter.create(this)
                .addNewData(items)
                .register(String.class, new HeaderItemViewProvider())
                .register(CategoryEntry.class, providers, new ClassConverter<CategoryEntry>(providers) {
                    @Override
                    public Class<? extends ItemViewProvider> map(CategoryEntry entry) {
                        if (entry.getType() == 1)
                            return Category1EntryItemViewProvider.class;
                        else
                            return Category4EntryItemViewProvider.class;
                    }
                }).attachToRecyclerView(rv_list);
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
