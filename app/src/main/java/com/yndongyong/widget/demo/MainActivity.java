package com.yndongyong.widget.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yndongyong.widget.multiitem.ItemViewProvider;
import com.yndongyong.widget.multiitem.Items;
import com.yndongyong.widget.multiitem.SimpleAdapter;
import com.yndongyong.widget.multiitem.SimpleViewHolder;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_list;

    private Items items = new Items();
    private SimpleAdapter multiTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        items.add("头部1");
        items.add(new Category4Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片1"));
        items.add(new Category4Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片2"));
        items.add(new Category4Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片3"));


        items.add("头部2");
        items.add(new Category4Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category4Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category4Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));


        items.add("头部3");
        items.add(new Category4Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category4Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category4Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));


        items.add("头部4");
        items.add(new Category4Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category4Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category4Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));

//        items.add("头部5");
        items.add(new Category4Entry("http://pic.58pic.com/58pic/14/27/45/71r58PICmDM_1024.jpg", "风景图片4"));
        items.add(new Category4Entry("http://img0.imgtn.bdimg.com/it/u=1610953019,3012342313&fm=214&gp=0.jpg", "风景图片5"));
        items.add(new Category4Entry("http://scimg.jb51.net/allimg/150819/14-150QZ9194K27.jpg", "风景图片6"));


        /*multiTypeAdapter = new SimpleAdapter(this,items);
        multiTypeAdapter.register(Category4Entry.class, new Category4EntryItemViewProvider());
        multiTypeAdapter.register(String.class, new ItemViewProvider<String>() {
            @Override
            public int getLayoutId() {
                return R.layout.item_header;
            }

            @Override
            public void onBindViewHolder(final SimpleViewHolder holder, String entity) {
                holder.setText(R.id.tv_header_name, entity);
                holder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = holder.getAdapterPosition();
                        Toast.makeText(MainActivity.this,  "click header position:" + adapterPosition + ";", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/
        multiTypeAdapter = SimpleAdapter.create(this)
                .addNewData(items)
                .register(Category4Entry.class, new Category4EntryItemViewProvider())
                .register(String.class, new ItemViewProvider<String>() {
                    @Override
                    public int getLayoutId() {
                        return R.layout.item_header;
                    }

                    @Override
                    public void onBindViewHolder(final SimpleViewHolder holder, String entity) {
                        holder.setText(R.id.tv_header_name, entity);
                        holder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int adapterPosition = holder.getAdapterPosition();
                                Toast.makeText(MainActivity.this, "click header position:" + adapterPosition + ";", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).attachToRecyclerView(rv_list);
    }

    private void fakeData() {
        for (int i = 0; i < 20; i++) {
            items.add("Item" + i);
        }
    }
}
