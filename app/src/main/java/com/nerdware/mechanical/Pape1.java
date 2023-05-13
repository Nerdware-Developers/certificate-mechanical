package com.nerdware.mechanical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nerdware.mechanical.paper_i.PaperAdapter;
import com.nerdware.mechanical.paper_i.paper_i_items;
import com.nerdware.mechanical.paper_i.paperads;

import java.util.ArrayList;

public class Pape1 extends AppCompatActivity {

    private AdView mAdView;
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pape1);
        AdView mAdView;
        mAdView = findViewById(R.id.ads);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        MobileAds.initialize(this);
        recyclerView = findViewById(R.id.paper_recyclerview);
        ImageView imageView = findViewById(R.id.back_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ArrayList<paper_i_items> paper_i_itemsArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        paper_i_itemsArrayList.add(new paper_i_items(R.drawable.mao,"mathematics"));
        paper_i_itemsArrayList.add(new paper_i_items(R.drawable.ms_1,"Mechanical Science/ Electrical Principles"));
        paper_i_itemsArrayList.add(new paper_i_items(R.drawable.td_1,"Technical Drawing"));
        paper_i_itemsArrayList.add(new paper_i_items(R.drawable.cft_1,"Fabrication/ Material Science"));
        paper_i_itemsArrayList.add(new paper_i_items(R.drawable.ict,"ICT/  Entrepreneurship"));

        PaperAdapter paperAdapter = new PaperAdapter(paper_i_itemsArrayList, this);
        recyclerView.setAdapter(paperAdapter);
        paperads.loadads(Pape1.this);
    }
}