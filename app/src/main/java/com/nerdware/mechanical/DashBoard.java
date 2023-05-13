package com.nerdware.mechanical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.navigation.NavigationView;
import com.nerdware.mechanical.dash.Items;
import com.nerdware.mechanical.dash.MyAdapter;
import com.nerdware.mechanical.dash.dashinter;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {


    Activity activity;
    int Counter = 0;

    RecyclerView recyclerView;


    ArrayList<Items> itemsList;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @SuppressLint({"NonConstantResourceId", "VisibleForTests"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        MobileAds.initialize(this);
        AdView adView1 = findViewById(R.id.dashboard);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, filter);
        // Check for internet connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            // Disable or hide certain features of your app
            // Show a dialog box to inform the user that they need an internet connection
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You need an internet connection to use this feature.")
                    .setTitle("No Internet Connection")
                    .setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }


// TODO: Add adView to your view hierarchy.
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        drawerLayout = findViewById(R.id.drawer_layer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav);
        recyclerView = findViewById(R.id.recyclerview);
        toolbar.setTitle(" Home");
        toolbar.setTitleTextAppearance(this, R.style.ToolBarTitle);


        imageSlider.setMinimumWidth(500);
        itemsList = new ArrayList<>();


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        itemsList.add(new Items(R.drawable.mod1,"Module 1"));
        itemsList.add(new Items(R.drawable.paper1,"Paper 1"));
        itemsList.add(new Items(R.drawable.module2,"Module 2"));
        itemsList.add(new Items(R.drawable.module_3,"Paper 2"));


        MyAdapter myAdapter = new MyAdapter(position -> {

            if (position == 0) {
                Intent intent = new Intent(DashBoard.this, Module_1.class);
                startActivity(intent);
            } else if (position == 1) {
                Intent intent = new Intent(DashBoard.this, Pape1.class);
                startActivity(intent);
            } else if (position == 2) {
                Intent intent = new Intent(DashBoard.this, Module_3.class);
                startActivity(intent);
            } else if (position == 3) {
                Intent intent = new Intent(DashBoard.this, Paper_2.class);
                startActivity(intent);
            }
        }, itemsList, this);


        recyclerView.setAdapter(myAdapter);
        com.nerdware.mechanical.dash.dashinter.loadads(DashBoard.this);


        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.mao, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.td_1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.work, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.ict, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.ee, ScaleTypes.FIT));
        imageSlider.setImageList(imageList,ScaleTypes.FIT);
        imageSlider.setItemClickListener(i -> {

            String fileName = "";

            int position = 0;
            switch (position) {
                case 0:
                    fileName = "mao.pdf";
                    break;
                case 1:
                    fileName = "td.pdf";
                    break;
                case 2:
                    fileName = "workshop.pdf";
                    break;
                case 3:
                    fileName = "ict.pdf";
                    break;
                case 4:
                    fileName = "ee.pdf";
                    break;

            }
            Intent intent = new Intent(DashBoard.this, PDF.class);
            intent.putExtra("FILE_NAME", fileName);
            startActivity(intent);
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(DashBoard.this, "Home", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.nav_about:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(DashBoard.this, "About", Toast.LENGTH_SHORT).show();
                    about(DashBoard.this);

                    break;
                case R.id.nav_setting:
                    drawerLayout.closeDrawer(GravityCompat.START);
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

                    Toast.makeText(DashBoard.this, "Setting", Toast.LENGTH_SHORT).show();
                    Intent set = new Intent(DashBoard.this, Settings.class);
                    startActivity(set);

                    break;
                case R.id.nav_share:
                    drawerLayout.closeDrawer(GravityCompat.START);
                    Toast.makeText(DashBoard.this, "Share", Toast.LENGTH_SHORT).show();
                    String sub = "Your Subject Here";
                    String msgbody = "https://play.google.com/store/apps/details?id=com.nerdware.mechanical";

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, sub);
                    intent.putExtra(Intent.EXTRA_TEXT, msgbody);
                    startActivity(Intent.createChooser(intent, "Share via"));
                    break;
                case R.id.nav_exit:
                    drawerLayout.closeDrawer(GravityCompat.START);

                    Toast.makeText(DashBoard.this, "Exit", Toast.LENGTH_SHORT).show();
                    exitMenu(DashBoard.this);


                    break;
            }
            return true;
        });
    }



    private void about(DashBoard dashBoard) {
        AlertDialog.Builder builder = new AlertDialog.Builder(dashBoard);
        builder.setTitle("About");
        builder.setMessage("This Application was Developed by Nerdware Developers with an aim of making your study easier." +
                "For any complains or feedback about our products please do contact us through our email at nerweredevelopers@gmail.com");
        builder.setNegativeButton("back", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void exitMenu(DashBoard dashBoard) {
        AlertDialog.Builder builder = new AlertDialog.Builder(dashBoard);
        builder.setTitle("WARNING!!");
        builder.setMessage("Are You Sure You Want To Exit ?");
        builder.setPositiveButton("Yes", (dialog, which) -> finish());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}


