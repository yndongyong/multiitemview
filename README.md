# multiitemview
作用：简化adapter中涉及多种ItemView布局的写法，使用对象池存放布局对象(ItemViewProvider)，并在其中封装布局与对象绑定相关的逻辑，简化Adapter。

###特性
-  极少的类文件
-  简化Adapter的写法，直接使用MultiTypeAdapter
-  每一种布局相关逻辑均写在单独的类中。


###主要涉及的类
- MultiTypeAdapter

 继承RecylerView的adapter，简化多类型Item的Adapter写法。
- ItemViewProvider

	针对每种布局Item，在其中完成布局的解析和对象的绑定和该布局相关的逻辑，提供两个方法，仿RecylerView.ViewHolder的写法,用于完成ViewHolder的创建和ViewHolder和实体对象的绑定。
	+ onCreateViewHolder(LyaoutInflater inflater,int viewType)
	+ onBindViewHolder(VH viewHolder,T entity)
	
- Items 数据集



###demo





