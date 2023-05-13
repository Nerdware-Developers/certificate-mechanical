package com.nerdware.mechanical.dash;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;

import com.nerdware.mechanical.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private final OnItemClickListener mlistener;
    ArrayList<Items> items;
    Activity activity;
    int Counter = 0;
    Context context;

    public MyAdapter(OnItemClickListener listener, ArrayList<Items> items, Context context) {
        this.mlistener = listener;
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        holder.imageView.setImageResource(items.get(position).getImage());
        holder.textView.setText(items.get(position).getDash());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Counter++;
                if (Counter == 1) {
                    if (dashinter.minterstitialAd != null) {
                        dashinter.minterstitialAd.show(activity);
                        dashinter.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                dashinter.minterstitialAd = null;
                                dashinter.loadads(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                dashinter.minterstitialAd = null;
                                dashinter.loadads(activity);
                            }
                        });
                    }
                    Counter = 0;
                }
                if(mlistener !=null){
                    mlistener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.res_image);
            textView = itemView.findViewById(R.id.dashh);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
