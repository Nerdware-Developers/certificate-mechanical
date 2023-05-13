package com.nerdware.mechanical;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;

import java.util.ArrayList;

public class paper_2_Adapter extends RecyclerView.Adapter<paper_2_Adapter.ViewHolder> {
    ArrayList<paper_item> paper_2_items;
    private ItemClickListener listener;
    int Counter = 0;
    Activity activity;
    Context context;

    public paper_2_Adapter(ItemClickListener listener) {
        this.listener = listener;
    }

    public paper_2_Adapter(ArrayList<paper_item> paper_2_items, Context context) {
        this.paper_2_items = paper_2_items;
        this.context = context;
    }

    @NonNull
    @Override
    public paper_2_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull paper_2_Adapter.ViewHolder holder, int position) {
        holder.txt.setText(paper_2_items.get(position).getPap());


        holder.imageView.setImageResource(paper_2_items.get(position).getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Counter++;
                if (Counter == 1) {
                    if (paper2ads.minterstitialAd != null) {
                        paper2ads.minterstitialAd.show(activity);
                        paper2ads.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                paper2ads.minterstitialAd = null;
                                paper2ads.loadads(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                paper2ads.minterstitialAd = null;
                                paper2ads.loadads(activity);
                            }
                        });
                    }
                    Counter = 0;
                }
                int position = holder.getAdapterPosition();
                String fileName = "";

                switch (position) {
                    case 0:
                        fileName = "math_ms.pdf";
                        break;
                    case 1:
                        fileName = "comms.pdf";
                        break;
                    case 2:
                        fileName = "cftk_2.pdf";
                        break;
                    case 3 :
                        fileName = "works.pdf";
                        break;
                    case 4 :
                        fileName = "tdk_2.pdf";
                        break;

                }
                Intent intent;
                intent = new Intent(v.getContext(), PDF.class);
                intent.putExtra("FILE_NAME", fileName);
                v.getContext().startActivity(intent);
            }


        });

    }

    @Override
    public int getItemCount() {
        return paper_2_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.pp2);
            imageView = itemView.findViewById(R.id.image_pp2);
        }
    }
    public interface ItemClickListener{
        void OnClick(int position, Object o);
    }
}
