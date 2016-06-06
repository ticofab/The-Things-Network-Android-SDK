package org.ttn.android.sdk.application;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/*
 * Copyright 2016 Fabio Tiriticco / Fabway
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by fabiotiriticco on 5 June 2016.
 *
 */
public class TTNAndroidSDKApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // init joda date time
        JodaTimeAndroid.init(this);
    }
}
