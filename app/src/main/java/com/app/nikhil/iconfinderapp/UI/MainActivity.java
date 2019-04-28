package com.app.nikhil.iconfinderapp.UI;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.nikhil.iconfinderapp.Adapter.IconAdapter;
import com.app.nikhil.iconfinderapp.Pojo.Icon;
import com.app.nikhil.iconfinderapp.Pojo.Iconset;
import com.app.nikhil.iconfinderapp.R;
import com.app.nikhil.iconfinderapp.ResponseBody.IconResponseBody;
import com.app.nikhil.iconfinderapp.ResponseBody.IconSetResponseBody;
import com.app.nikhil.iconfinderapp.Rest.ApiCredential;
import com.app.nikhil.iconfinderapp.Rest.ApiService;
import com.app.nikhil.iconfinderapp.Rest.ResponseCallback;
import com.victor.loading.rotate.RotateLoading;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ImageButton searchIconBtn;
    ImageButton crossBtn;
    EditText queryEt;
    ImageView appIconView;
    TextView appTitleView;
    DownloadManager downloadmanager;
    IconAdapter iconAdapter;
    RecyclerView iconRv;
    TextView noIconsFoundTv;
    RotateLoading iconsLoadingBar;

    String iconName;
    boolean isQuerySearch=false;

    String afterIconsetId = "";

    ApiService apiService;

    ArrayList<Iconset> iconsetsList;

    int currentChildPosition = 0;

    ArrayList<Icon> iconsList;
    private String downloadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconRv = findViewById(R.id.iconsRv);
        noIconsFoundTv=findViewById(R.id.noIconsFoundTv);
        iconsLoadingBar=findViewById(R.id.iconsLoadingBar);
        iconsLoadingBar.start();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher_background);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.custom_app_bar, null);

        searchIconBtn = v.findViewById(R.id.searchBtn);
        crossBtn = v.findViewById(R.id.crossBtn);
        queryEt = v.findViewById(R.id.searchIconEt);
        appIconView = v.findViewById(R.id.appIconImageView);
        appTitleView = v.findViewById(R.id.appTitle);

        searchIconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchIconBtn.setVisibility(View.GONE);
                crossBtn.setVisibility(View.VISIBLE);
                queryEt.setVisibility(View.VISIBLE);
                appIconView.setVisibility(View.GONE);
                appTitleView.setVisibility(View.GONE);

            }
        });

        crossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryEt.setText("");
                crossBtn.setVisibility(View.GONE);
                queryEt.setVisibility(View.GONE);
                appIconView.setVisibility(View.VISIBLE);
                appTitleView.setVisibility(View.VISIBLE);
                searchIconBtn.setVisibility(View.VISIBLE);
                currentChildPosition=0;
                iconsList.clear();
                iconsetsList.clear();
                iconRv.setVisibility(View.VISIBLE);
                noIconsFoundTv.setVisibility(View.GONE);
                afterIconsetId="";
                isQuerySearch=false;
                iconAdapter.notifyDataSetChanged();
                populateIconsetsList();

            }
        });

        queryEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length()>2) {
                    populateIconsListUsingQuery(s.toString().trim());
                    iconsList.clear();
                    iconsetsList.clear();
                    isQuerySearch=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actionBar.setCustomView(v);

        apiService = new ApiService();
        iconsetsList = new ArrayList<>();
        iconsList = new ArrayList<>();
        populateIconsetsList();

    }


    public void populateIconsetsList() {

        iconsLoadingBar.start();

        apiService.getIconsets(ApiCredential.apiClientId, ApiCredential.apiClientSecret, "20", afterIconsetId, new ResponseCallback<IconSetResponseBody>() {
            @Override
            public void success(IconSetResponseBody iconSetResponseBody) {

                for (int i = 0; i < iconSetResponseBody.getIconsets().size(); i++) {
                    iconsetsList.add(iconSetResponseBody.getIconsets().get(i));

                    populateIconsList(iconSetResponseBody.getIconsets().get(i).getIdentifier());
                }
                afterIconsetId = iconSetResponseBody.getIconsets().get(iconSetResponseBody.getIconsets().size() - 1).getIconset_id();

                if (currentChildPosition == 0)
                    populateRecyclerIconView();
            }

            @Override
            public void failure(IconSetResponseBody iconSetResponseBody) {

                Toast.makeText(MainActivity.this, iconSetResponseBody.getMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void populateIconsList(String iconsetIdentifer) {
        apiService.getIcons(iconsetIdentifer, ApiCredential.apiClientId, ApiCredential.apiClientSecret, new ResponseCallback<IconResponseBody>() {
            @Override
            public void success(IconResponseBody iconResponseBody) {


                for (Icon icon : iconResponseBody.getIcons()) {
                    iconsList.add(icon);
                    Log.v("iconname", icon.getIcon_id());
                }

                iconAdapter.notifyItemInserted(iconsList.size() - 1);
                iconsLoadingBar.stop();
            }

            @Override
            public void failure(IconResponseBody iconResponseBody) {
                Toast.makeText(MainActivity.this, iconResponseBody.getMessage()+"", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void populateRecyclerIconView() {

        iconAdapter = new IconAdapter(this, iconsList) {

            @Override
            public void load(int position) {
                if (!isQuerySearch) {
                    populateIconsetsList();
                    currentChildPosition = position;
                }
            }

            @Override
            public void downloadIcon(String url,String iconName1) {

                downloadUrl=url;
                iconName=iconName1;
                isStoragePermissionGranted();
            }
        };
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        iconRv.setLayoutManager(mLayoutManager);
        iconRv.setItemAnimator(new DefaultItemAnimator());
        iconRv.setAdapter(iconAdapter);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
               downloadIconManager();
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            downloadIconManager();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("TAG","Permission: "+permissions[0]+ "was "+grantResults[0]);
            downloadIconManager();
        }
    }

    public void populateIconsListUsingQuery(String query) {
        apiService.getIcons(query, ApiCredential.apiClientId, ApiCredential.apiClientSecret, new ResponseCallback<IconResponseBody>() {
            @Override
            public void success(IconResponseBody iconResponseBody) {


                for (Icon icon : iconResponseBody.getIcons()) {
                    iconsList.add(icon);
                    Log.v("iconname", icon.getIcon_id());
                }

                noIconsFoundTv.setVisibility(View.GONE);
                iconRv.setVisibility(View.VISIBLE);
                populateRecyclerIconView();

            }

            @Override
            public void failure(IconResponseBody iconResponseBody) {

                noIconsFoundTv.setVisibility(View.VISIBLE);
                iconRv.setVisibility(View.GONE);
            }

        });

    }


    public void downloadIconManager()
    {
        downloadmanager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(downloadUrl);
        Log.v("downloadUrl1",downloadUrl);
        Log.v("downloaduri1", String.valueOf(uri));
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(iconName);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, iconName+".png");
        downloadmanager.enqueue(request);
        Toast.makeText(this, "Downloading icon!", Toast.LENGTH_SHORT).show();

    }




}
