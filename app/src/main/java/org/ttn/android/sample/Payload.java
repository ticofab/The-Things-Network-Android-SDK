package org.ttn.android.sample;

import android.util.Base64;

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
class Payload {
    Integer mLux;
    Double mTemp;

    public Payload(Double temp, Integer lux) {
        mTemp = temp;
        mLux = lux;
    }

    static Payload fromEncodedPayload(String encodedPayload) {
        byte[] bytes = Base64.decode(encodedPayload, Base64.DEFAULT);
        double temp = (double) ((bytes[0] << 8) | bytes[1]) / 100;
        int lux = (bytes[2] << 8) | bytes[3];
        return new Payload(temp, lux);
    }
}