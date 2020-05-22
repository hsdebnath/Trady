package com.example.trady;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;


public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {

        FirebaseInstanceId.getInstance().getInstanceId()
        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()){
                    Log.e("tag", "getInstanceId Failed", task.getException());
                    return;
                }
                String token = task.getResult().getToken();
                String model= android.os.Build.MODEL;

                //Toast.makeText(getApplicationContext(),"1st function : "+token,Toast.LENGTH_LONG).show();

                //token sending code
                int SDK_INT = android.os.Build.VERSION.SDK_INT;

                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    //OkHTTP codes here
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("http://3.83.226.184/token?Token="+token+"&Model="+model)
                            .build();
                    //Toast.makeText(getApplicationContext(),"Code came here !!",Toast.LENGTH_LONG).show();
                    try {
                       // Toast.makeText(getApplicationContext(),"Code came to TRY!!",Toast.LENGTH_LONG).show();
                        client.newCall(request).execute();
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"Code came to Catch!!",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }

        });
    }

}
