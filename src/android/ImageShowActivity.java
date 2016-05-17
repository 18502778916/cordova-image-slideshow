package org.apache.cordova.images;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;

import com.example.show.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchong on 16/5/16.
 */
public class ImageShowActivity extends Activity {
    ViewPager viewPager;
    int imageNum;
    ArrayList listUrl;
    boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_show_activity);

        init();
        showImage(listUrl,imageNum);

    }

    private void init(){
        Bundle bundle=getIntent().getExtras();
        imageNum=bundle.getInt("imageNum");
        listUrl=bundle.getStringArrayList("listUrl");
        for (int i=0;i<=listUrl.size()-1;i++){
            Log.e("listUrl",""+listUrl.get(i));
            Log.e("imageNum",""+imageNum);
        }

    }

    private void showImage(List<String> listUrl,int imageNum) {
        viewPager = new HackyViewPager(this);
        viewPager.setAdapter(new SampleAdapter(this, listUrl));
        viewPager.setCurrentItem(imageNum-1);
        try {
            this.runOnUiThread(
                    new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            int backGroundColor = Color.parseColor("#000000");
                            viewPager.setBackgroundColor(backGroundColor);
                            addContentView(viewPager, layoutParams);


                        }
                    }

            );
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Log.e("onKeyDown","KEYCODE_BACK");
            isOpen=false;
            Intent mIntent = new Intent();
            mIntent.putExtra("isOpen", isOpen);
            // 设置结果，并进行传送
            setResult(1, mIntent);
            this.finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


}
