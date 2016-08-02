package com.robinpowered.react;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Promise;

public class SharedPreferencesModule extends ReactContextBaseJavaModule {
  SharedPreferences preferences;

  public SharedPreferencesModule(ReactApplicationContext reactApplicationContext) {
    super(reactApplicationContext);
    preferences = reactApplicationContext.getApplicationContext().getSharedPreferences("titanium", Context.MODE_PRIVATE);
    System.out.println("SharedPreferencesModule HAS INITIALIZED");
  }

  @Override
  public String getName() {
    return "SharedPreferencesAndroid";
  }

  @ReactMethod
  public void getString(String key, Promise promise) {
    Object value = preferences.getAll().get(key);
    if (value != null) {
      promise.resolve(value.toString());
    } else {
      promise.resolve("");
    }
  }

  @ReactMethod
  public void setString(String key, String value, Promise promise) {
    SharedPreferences.Editor editor = preferences.edit();
    if (value == null) {
      editor.remove(key);
    } else {
      editor.putString(key, value);
    }
    editor.commit();
    promise.resolve("");
  }
}
