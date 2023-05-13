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

public class module_3_Adapter extends RecyclerView.Adapter<module_3_Adapter.ViewHolder> {

    ArrayList<module_3_item> module3Items;
    Activity activity;

    int counter = 0;


    Context context;

    public module_3_Adapter(ArrayList<module_3_item> module3Items, Context context) {
        this.module3Items = module3Items;
        this.context = context;
    }
    @NonNull
    @Override
    public module_3_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mod_3items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull module_3_Adapter.ViewHolder holder, int position) {


        holder.tee.setText(module3Items.get(position).getM3());
        holder.ostx.setText(module3Items.get(position).getP3());
        holder.imageView.setImageResource(module3Items.get(position).getImage());






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter ++;
                if (counter == 1) {

                    if (m3ads.minterstitialAd != null) {
                        m3ads.minterstitialAd.show(activity);
                        m3ads.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                m3ads.minterstitialAd = null;
                                m3ads.loadads(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                m3ads.minterstitialAd = null;
                                m3ads.loadads(activity);
                            }
                        });
                    }
                    counter = 0;
                }

                int position = holder.getAdapterPosition();
                String fileName = "";

                switch (position) {
                    case 0:
                        fileName = "mao.pdf";
                        break;
                    case 1:
                        fileName = "ms.pdf";
                        break;
                    case 2:
                         fileName = "td.pdf";
                        break;
                    case 3 :
                        fileName = "production.pdf";
                        break;
                    case 4 :
                        fileName = " comm_skills.pdf";
                        break;
                    case 5 :
                        fileName = "workshop.pdf";
                        break;

                }
                Intent intent = new Intent(v.getContext(), PDF.class);
                intent.putExtra("FILE_NAME", fileName);
                v.getContext().startActivity(intent);
            }


        });

    }

    @Override
    public int getItemCount() {
        return module3Items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tee;
        TextView ostx;

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tee = itemView.findViewById(R.id.mod_3);
            ostx = itemView.findViewById(R.id.pub_3);
            imageView = itemView.findViewById(R.id.image_3);
        }
    }

}
