package com.nerdware.mechanical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Module_3 extends AppCompatActivity {
    ArrayList<module_3_item> items;


    @SuppressLint("VisibleForTests")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module3);
        MobileAds.initialize(this);
        AdView mAdView = findViewById(R.id.ban3);
        @SuppressLint("VisibleForTests") AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

// TODO: Add adView to your view hierarchy.
        RecyclerView recyclerView = findViewById(R.id.module_recyclerview);
        ImageView imageView = findViewById(R.id.back_btn);
        imageView.setOnClickListener(v -> onBackPressed());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        items = new ArrayList<>();

        items.add(new module_3_item(R.drawable.mao2,"Mathematics",""));
        items.add(new module_3_item(R.drawable.ms_2,"Mechanical Science"," "));
        items.add(new module_3_item(R.drawable.td_2,"Technical Drawing"," "));
        items.add(new module_3_item(R.drawable.cft_1,"Production Process"," "));
        items.add(new module_3_item(R.drawable.comms,"Communication Skills"," "));
        items.add(new module_3_item(R.drawable.work," Workshop Organization"," "));

        module_3_Adapter module3Adapter1 = new module_3_Adapter(items,Module_3.this);
        recyclerView.setAdapter(module3Adapter1);

        m3ads.loadads(Module_3.this);
    }
    }
