package org.ttn.android.sample;

import android.util.Base64;

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