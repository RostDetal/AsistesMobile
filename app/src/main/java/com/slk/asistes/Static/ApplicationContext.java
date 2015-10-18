package com.slk.asistes.Static;

import android.content.Context;

import com.slk.asistes.Managers.SocialManager;
import com.slk.asistes.Managers.UserManager;

/**
 * Created by ViS on 12.03.15.
 */
public final class ApplicationContext {

    private static ApplicationContext _mInstanceContext;

    private UserManager _mUserManager;
    private SocialManager _mSocialManager;
    private Context _maContext;



    public static ApplicationContext Instance()
    {
        if(_mInstanceContext == null)
            _mInstanceContext = new ApplicationContext();
        return _mInstanceContext;
    }

    public final void Initialize(Context _context)
    {
        _maContext = _context;
        _mUserManager = new UserManager();
        _mSocialManager = new SocialManager();

        Logger.toConsole("Application static context initializing complete");
    }

    //return user manager
    public UserManager getUserManager()
    {
        return _mUserManager;
    }
    public SocialManager getSocialManager(){ return _mSocialManager; }
    public Context getAndroidContext()
    {
        return _maContext;
    }

}
