package com.slk.asistes.Managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.slk.asistes.Static.ApplicationContext;

/**
 * Created by vis on 14.03.15.
 */
public final class SocialManager extends BaseManager {

    public final String LOGIN_URL = "http://192.168.0.101:3000/mobile/login";
    public final String LOGOUT_URL = "http://192.168.0.101:3000/mobile/logout";

    private ConnectivityManager _androidNetManager;

    public SocialManager (){
        Init();
    }

    public void Init()
    {
        _androidNetManager = (ConnectivityManager) ApplicationContext.Instance().getAndroidContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        super.Init(this.toString());
    }

    public boolean hasInternet()
    {
        NetworkInfo activeNetwork = _androidNetManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


    public void TryLogin()
    {

    }





}
