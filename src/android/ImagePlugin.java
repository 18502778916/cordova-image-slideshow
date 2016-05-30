package org.km.plugins.image_slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by zhangchong on 16/4/1.
 */
public class ImagePlugin extends CordovaPlugin {
    CallbackContext callbackContext;
    public boolean isOpen;
    ArrayList<String> listUrl=new ArrayList<String>();
    int imageNum;
    public Handler handler;


    @Override
    public boolean execute(String action, final JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext=callbackContext;
        if ("show".equals(action)){
            imageNum=args.getInt(1);
            isOpen=true;
            try {


                JSONArray jsonArray = args.getJSONArray(0);
                for (int i=0; i < jsonArray.length(); i++) {
                    Log.e("x", "" + jsonArray.get(i));
                    listUrl.add(jsonArray.getString(i));
                }
                Intent intent = new Intent(this.cordova.getActivity(),ImageShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listUrl", listUrl);
                bundle.putInt("imageNum", imageNum);
                intent.putExtras(bundle);
                this.cordova.startActivityForResult((CordovaPlugin) this, intent, 1);
                Log.e("x",""+isOpen);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (resultCode) { //resultCode为回传的标记，我在第二个Activity中回传的是RESULT_OK
            case 1:
                Bundle b=intent.getExtras();  //data为第二个Activity中回传的Intent
                isOpen=b.getBoolean("isOpen");  //str即为回传的值
                Log.e("x",""+isOpen);
                //下面三句为cordova插件回调页面的逻辑代码
                PluginResult mPlugin = new PluginResult(PluginResult.Status.OK,
                        isOpen);
                mPlugin.setKeepCallback(true);
                callbackContext.sendPluginResult(mPlugin);
                Log.e("x",""+isOpen);
                break;
            default:
                break;
        }
    }
}
