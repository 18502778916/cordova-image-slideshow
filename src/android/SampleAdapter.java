package org.apache.cordova.images;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by zhangchong on 16/3/30.
 */
public class SampleAdapter extends PagerAdapter {
    Activity activity;
    List<String> listUrl;
    boolean isOpen;
    SampleAdapter(Activity activity, List<String> listUrl){
        this.activity=activity;
        this.listUrl=listUrl;
    }

    @Override
    public int getCount() {
        return listUrl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        final PhotoView photoView=new PhotoView(container.getContext());
        Log.e("x", "1");
        Glide.with(activity).load(listUrl.get(position)).into(photoView);

        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                //container.setVisibility(View.GONE);
                isOpen=false;
                Intent mIntent = new Intent();
                mIntent.putExtra("isOpen", isOpen);
                // 设置结果，并进行传送
                activity.setResult(1, mIntent);
                activity.finish();
            }
        });
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("xxx", listUrl.get(position));
                return true;
            }
        });


        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
