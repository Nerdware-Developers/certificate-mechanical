package com.nerdware.mechanical.Module1;

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
import com.nerdware.mechanical.Admob;
import com.nerdware.mechanical.Module_1;
import com.nerdware.mechanical.PDF;
import com.nerdware.mechanical.R;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.Module_ViewHolder>{

    ArrayList<module_item> module_items;

    Context context;
    Activity activity;

    int Counter = 0;



    public ModuleAdapter(ArrayList<module_item> module_items, Context context,ItemClickListener clickListener) {
        this.module_items = module_items;
        this.context = context;
    }

    public ModuleAdapter(Module_1 context) {
    }


    @NonNull
    @Override
    public ModuleAdapter.Module_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new Module_ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.model_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleAdapter.Module_ViewHolder holder, int position) {
        holder.name.setText(module_items.get(position).getSub());
        holder.publish.setText(module_items.get(position).getPub());

        holder.imageView.setImageResource(module_items.get(position).getImage());
        Counter++;
        if (Counter == 1) {
            if (Admob.minterstitialAd != null) {
                Admob.minterstitialAd.show(activity);
                Admob.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        Admob.minterstitialAd = null;
                        Admob.loadads(activity);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        Admob.minterstitialAd = null;
                        Admob.loadads(activity);
                    }
                });
            }
            Counter = 0;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        fileName = "cft.pdf";
                        break;
                    case 3 :
                        fileName = "cme.pdf";
                        break;
                    case 4 :
                        fileName = "td.pdf";
                        break;
                    case 5 :
                        fileName = "ict.pdf";
                        break;
                    case 6 :
                        fileName = "ee.pdf";
                        break;
                    case 7 :
                        fileName = "eep.pdf";
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
        return module_items.size();
    }

    public class Module_ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, publish;

        public Module_ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.clause);
            publish = itemView.findViewById(R.id.publisher);
            imageView = itemView.findViewById(R.id.card_img);
        }
    }
    public interface ItemClickListener{
        void OnClick(int position, Object o);
    }
}
