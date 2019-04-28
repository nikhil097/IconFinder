package com.app.nikhil.iconfinderapp.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.nikhil.iconfinderapp.Pojo.Icon;
import com.app.nikhil.iconfinderapp.R;
import com.app.nikhil.iconfinderapp.Rest.ApiCredential;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public abstract class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder>{



    Context context;
    ArrayList<Icon> iconsList;

    @NonNull
    @Override
    public IconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context=viewGroup.getContext();
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.icon_item,viewGroup,false);
        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconViewHolder iconViewHolder, int i) {

        final Icon icon=iconsList.get(i);

        Glide.with(context).load(icon.getRasterSizes().get(icon.getRasterSizes().size()-1).getFormats().get(0).getPreview_url())
                .into(iconViewHolder.iconPreview);

        if(icon.getIs_premium())
        {
            iconViewHolder.iconDownloadBtn.setVisibility(View.GONE);
            iconViewHolder.priceTag.setVisibility(View.VISIBLE);
            iconViewHolder.priceTv.setVisibility(View.VISIBLE);
            iconViewHolder.priceTv.setText(icon.getPrices().get(0).getCurrency()+" "+icon.getPrices().get(0).getPrice());
        }
        else{
            iconViewHolder.iconDownloadBtn.setVisibility(View.VISIBLE);
            iconViewHolder.priceTag.setVisibility(View.GONE);
            iconViewHolder.priceTv.setVisibility(View.GONE);
        }

        iconViewHolder.iconTitleTv.setText(icon.getIcon_id());

        iconViewHolder.iconDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl=ApiCredential.baseUrl+"v3/"+icon.getRasterSizes().get(icon.getRasterSizes().size()-1).getFormats().get(0).getDownload_url()+"?client_id="+ApiCredential.apiClientId+"&client_secret="+ApiCredential.apiClientSecret;
                String iconName=icon.getPublished_at();
                downloadIcon(imageUrl,iconName);
            }
        });

        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/lato-heavy.ttf");

        iconViewHolder.iconTitleTv.setTypeface(custom_font);

        if(i==iconsList.size()-1)
        {
            load(iconsList.size());
        }

    }

    @Override
    public int getItemCount() {
        return iconsList.size();
    }

    public IconAdapter(Context context, ArrayList<Icon> iconsList) {
        this.context = context;
        this.iconsList = iconsList;
    }


    public class IconViewHolder extends RecyclerView.ViewHolder{


        ImageView iconPreview;
        TextView iconTitleTv;
        Button iconDownloadBtn;
        ImageView priceTag;
        TextView priceTv;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            iconPreview=itemView.findViewById(R.id.iconPreview);
            iconTitleTv=itemView.findViewById(R.id.iconTitleTv);
            iconDownloadBtn=itemView.findViewById(R.id.downloadIconBtn);
            priceTag=itemView.findViewById(R.id.priceTagView);
            priceTv=itemView.findViewById(R.id.priceTv);
        }
    }

    public abstract void load(int position);

    public abstract void downloadIcon(String downloadUrl,String iconName);

}
