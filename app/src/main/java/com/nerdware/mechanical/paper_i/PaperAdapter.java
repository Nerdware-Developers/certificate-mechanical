package com.nerdware.mechanical.paper_i;

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
import com.nerdware.mechanical.PDF;
import com.nerdware.mechanical.R;

import java.util.ArrayList;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.PaperViewHolder>{

    ArrayList<paper_i_items> paperIItems;
    Context context;
    int Counter = 0;

    Activity activity;
    private  ItemClickListener clickListener;

    public PaperAdapter(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public PaperAdapter(ArrayList<paper_i_items> paperIItems, Context context) {
        this.paperIItems = paperIItems;
        this.context = context;
    }

    @NonNull
    @Override
    public PaperAdapter.PaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaperViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PaperAdapter.PaperViewHolder holder, int position) {
        holder.textView.setText(paperIItems.get(position).getSbj());
        holder.imageView.setImageResource(paperIItems.get(position).getImg());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Counter++;
                if (Counter == 3) {
                    if (paperads.minterstitialAd != null) {
                        paperads.minterstitialAd.show(activity);
                        paperads.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                paperads.minterstitialAd = null;
                                paperads.loadads(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                paperads.minterstitialAd = null;
                                paperads.loadads(activity);
                            }
                        });
                    }
                    Counter = 0;
                }
                int position = holder.getAdapterPosition();
                String fileName = "";

                switch (position) {
                    case 0:
                        fileName = "mao_1.pdf";
                        break;
                    case 1:
                        fileName = "ms_eep.pdf";
                        break;
                    case 2:
                        fileName = "tdk_1.pdf";
                        break;
                    case 3 :
                        fileName = "cftk_1.pdf";
                        break;
                    case 4 :
                        fileName = "ictk.pdf";
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
        return paperIItems.size();
    }

    public static class PaperViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView textView;


        public PaperViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.paper1);
            imageView = itemView.findViewById(R.id.paper_img);

        }
    }
    public interface ItemClickListener{
        void OnClick(int position, Object o);
    }
}
