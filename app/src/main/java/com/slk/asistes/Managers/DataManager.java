package com.slk.asistes.Managers;

import java.util.Hashtable;

/**
 * Created by ViS on 20.10.15.
 */
public class DataManager extends BaseManager {

    private Hashtable<String, Object> _liveData;


    public DataManager(){
        Init();
    }

    public void Init()
    {
        super.Init(this.toString());
        _liveData = new Hashtable<String, Object>();
    }

    public void SetLiveData(String key, Object data)
    {
        _liveData.put(key, data);
    }

    public Object GetLiveData(String key)
    {
        return _liveData.containsKey(key) ? _liveData.get(key) : null;
    }



}
