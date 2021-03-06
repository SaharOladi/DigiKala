package com.example.digikala.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.digikala.R;
import com.example.digikala.model.product.ImagesItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context mContext;
    private List<ImagesItem> mImagesItems = new ArrayList<>();

    public List<ImagesItem> getImagesItems() {
        return mImagesItems;
    }

    public void setImagesItems(List<ImagesItem> imagesItems) {
        mImagesItems = imagesItems;
        notifyDataSetChanged();
    }

    public SliderAdapter(Context context, List<ImagesItem> imagesItems) {
        mContext = context;
        mImagesItems = imagesItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.slider, null);
        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        ImagesItem imageItem = mImagesItems.get(position);
        viewHolder.bindImageItem(viewHolder, imageItem);
    }

    @Override
    public int getCount() {
        return mImagesItems.size();
    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private ImageView imageViewBackground;
        private ImagesItem mImageItem;
        private View mItemView;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            findHolderViews(itemView);
        }

        private void findHolderViews(View itemView) {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            mItemView = itemView;
        }

        private void bindImageItem(SliderAdapterVH holder, ImagesItem image) {
            mImageItem = image;
            if (image.getSrc().length() != 0)
                Glide.with(mItemView)
                        .load(image.getSrc())
                        .fitCenter()
                        .into(imageViewBackground);

        }

    }

}
