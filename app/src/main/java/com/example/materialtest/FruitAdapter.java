package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @version: V1.0
 * @author: WenyiYin
 * @className: FruitAdapter
 * @packageName: com.example.materialtest
 * @description: RecyclerView适配器，用于显示特定内容
 * @data: 2024/2/27 10:31
 **/
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mContext;

    private List<Fruit> mFruitList;//存储对应的水果数据

    /**
     * @version: V1.0
     * @author: WenyiYin
     * @className: ViewHolder
     * @packageName: com.example.materialtest
     * @description: 静态内部类，用于保存单个RecyclerView项的视图引用
     * @date: 2024/2/27 10:36
     **/
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitImage = (ImageView) view.findViewById(R.id.fruit_image);//初始化fruitImage
            fruitName = (TextView) view.findViewById(R.id.fruit_name);//初始化fruitName
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }


    /**
    * @author:  WenyiYin
    * @methodsName: onCreateViewHolder
    * @description: 重写该方法，用于构建RecyclerView的每个视图项
    * @param:  parent，viewType
    * @return: ViewHolder
    * @throws: null
    * @date: 2024/2/27 10:37
    */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent, false);

        return new ViewHolder(view);
    }

    /**
    * @author:  WenyiYin
    * @methodsName: onBindViewHolder
    * @description: 绑定RecyclerView的每个视图项的数据
    * @param:  holder，position
    * @return: void
    * @throws: null
    * @date: 2024/2/27 10:39
    */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {/*用于绑定RecyclerView的每个项视图的数据*/
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);

        //final ViewHolder holder1 = holder;
        /*注册cardView监听事件，并传递资源*/
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Log.d("xxx", "onClick: " + position);
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}
