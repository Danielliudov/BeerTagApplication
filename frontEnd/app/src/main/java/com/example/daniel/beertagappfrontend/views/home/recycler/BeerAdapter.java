package com.example.daniel.beertagappfrontend.views.home.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.beertagappfrontend.R;
import com.example.daniel.beertagappfrontend.models.Beer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ItemViewHolder> {
    private List<Beer> mBeer;
    private OnItemClickListener mOnBeerClickListener;


    public BeerAdapter() {
        this.mBeer = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.beer_item, viewGroup, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.setOnItemClickListener(mOnBeerClickListener);
        itemViewHolder.bind(mBeer.get(position));
    }


    @Override
    public int getItemCount() {
        return mBeer.size();
    }

    public Beer getItem(int position) {
        return mBeer.get(position);
    }


    public void clear() {
        mBeer.clear();
    }

    public void addAll(List<Beer> superheroes) {
        mBeer.addAll(superheroes);
    }

    public void setOnBeerClickListener(OnItemClickListener OnItemClickListener) {
        this.mOnBeerClickListener = OnItemClickListener;
    }



    protected static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.beerbrand_tv)
        TextView mBeerBrand;


        @BindView(R.id.beername_tv)
        TextView mBeerName;

        @BindView(R.id.beerabv_tv)
        TextView mBeerAbv;

        @BindView(R.id.beer_photo)
        ImageView mBeerPhoto;


        private Beer mBeer;
        private OnItemClickListener mOnClickListener;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Beer beer) {
            mBeer = beer;

            mBeerBrand.setText("Brand: " + String.valueOf(mBeer.getBrand()));
            mBeerName.setText("Name: " + mBeer.getBeerName());
            mBeerAbv.setText("Abv%: " + mBeer.getAbv());
            //Set the photo to be taken
            //  mBeerPhoto.(to see how the picture have to be taken
        }

        @OnClick
        public void onClick() {
            mOnClickListener.onClick(mBeer);
        }

        private void setOnItemClickListener(OnItemClickListener onClickListener) {
            mOnClickListener = onClickListener;
        }


    }
        public interface OnItemClickListener {
            void onClick(Beer beer);
        }
}
