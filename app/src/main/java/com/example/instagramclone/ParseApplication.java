package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("kLmSxthC9OTZtNiFz69t0QoqVm9tclZRWw7DVIwN")
                .clientKey("38PtVneCP5Sc1dgsVQtrX7lgkQfHdfHVbeOdcZN5")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
