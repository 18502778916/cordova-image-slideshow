package org.apache.cordova.images;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by zhangchong on 16/4/1.
 */
public class ImagePlugin extends CordovaPlugin {
    CallbackContext callbackContext;

    ArrayList<String> listUrl=new ArrayList<String>();
    int imageNum;
    @Override
    public boolean execute(String action, final JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext=callbackContext;
        if ("showImages".equals(action)){
            imageNum=args.getInt(1);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONArray jsonArray=args.getJSONArray(0);
                        for(int i=0;i<jsonArray.length();i++){
                            Log.e("x", "" + jsonArray.get(i));
                            listUrl.add(jsonArray.getString(i));
                        }
                        Intent intent=new Intent(cordova.getActivity(),ImageShowActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putStringArrayList("listUrl",listUrl);
                        bundle.putInt("imageNum",imageNum);
                        intent.putExtras(bundle);
                        cordova.getActivity().startActivity(intent);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }
        return true;
    }


}
