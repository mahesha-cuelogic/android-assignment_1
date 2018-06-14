package com.cuelogic.maheshauti.tinytorchassign_1.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cuelogic.maheshauti.tinytorchassign_1.Listener.OnLoadMoreListener;
import com.cuelogic.maheshauti.tinytorchassign_1.R;
import com.cuelogic.maheshauti.tinytorchassign_1.model.PostDetails;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by maheshauti on 24/03/18.
 */

public class TinyTorchPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public ArrayList<PostDetails> list;
    private LayoutInflater mInflater;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private RecyclerView mRecyclerView;
    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    static public Boolean isLastPage=false;

    public void setLastPage(Boolean lastPage) {
        isLastPage = lastPage;
    }



    Context mContext;
    public TinyTorchPostAdapter(Context context, ArrayList<PostDetails> list,RecyclerView recyclerView){
        this.mInflater=LayoutInflater.from(context);
        this.list=list;
        this.mRecyclerView=recyclerView;
        this.mContext=context;

        final LinearLayoutManager linearLayoutManager=(LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold))
                {

                    if (mOnLoadMoreListener != null) {
                        System.out.println("load more called");
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener){
        this.mOnLoadMoreListener=mOnLoadMoreListener;
    }


    @Override
    public int getItemViewType(int position) {

        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder vh;
        //create the view for each corresponding viewtype
        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
            vh = new tinyTorchHolder(v);
            return vh;


        } else if(viewType==VIEW_TYPE_LOADING){


                View v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.loading_item_layout, parent, false);
                vh = new ProgressViewHolder(v);
                return vh;


        }
        return  null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof tinyTorchHolder){

           tinyTorchHolder newholder=(tinyTorchHolder)holder;

            PostDetails currentObj=list.get(position);
            try {
                newholder.setData(currentObj,position);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else {

                  ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
            System.out.println("progressbar set indeterminate");
            if(isLastPage){
                ((ProgressViewHolder) holder).progressBar.setVisibility(View.GONE);
            }

        }


    }

    @Override
    public int getItemCount() {

            return list == null ? 0 : list.size();

    }
    public void setLoaded() {
        System.out.println("set loaded");
        isLoading = false;
    }


    public class tinyTorchHolder extends RecyclerView.ViewHolder{
            TextView msg,tags;
            ImageView imageView;
            ProgressBar p;
        public tinyTorchHolder(View itemView) {
            super(itemView);
            msg=(TextView)itemView.findViewById(R.id.message);
            tags=(TextView)itemView.findViewById(R.id.tags);
            imageView=(ImageView)itemView.findViewById(R.id.image);
//            p=(ProgressBar)itemView.findViewById(R.id.progress1);
        }

        public void setData(PostDetails currentObj, int position) throws JSONException {
//
            if(currentObj.getMsg().isEmpty()){
                    msg.setVisibility(View.GONE);
            }else
            {
                msg.setText("Message: "+currentObj.getMsg());
            }

            System.out.println("length of the tags is ========="+currentObj.getTags().length());
//            if(currentObj.getTags().length()==0){
//                tags.setVisibility(View.GONE);
//            }
            if(currentObj.getTags().length()!=0) {
                tags.setVisibility(View.VISIBLE);
                tags.setText("Tags: "+currentObj.getTags());
            }



        if(currentObj.getImgUrl()!=null){
            Glide.with( mContext).load(currentObj.getImgUrl()).into(imageView);
        }else {
            imageView.setImageResource(R.drawable.no);
        }

        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
            //progressBar.setVisibility(View.VISIBLE);
            System.out.println("contructor of progressviewholder called");
        }
    }

}
