package org.ttn.android.sample;

import com.robinhood.spark.SparkAdapter;

import java.util.List;

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
public class TemperatureAdapter extends SparkAdapter {

    List<Payload> mPayloads;

    public TemperatureAdapter(List<Payload> payloads) {
        mPayloads = payloads;
    }

    @Override
    public int getCount() {
        return mPayloads.size();
    }

    @Override
    public Object getItem(int index) {
        return mPayloads.get(index);
    }

    @Override
    public float getY(int index) {
        return Float.valueOf(mPayloads.get(index).mTemp.toString());
    }
}
