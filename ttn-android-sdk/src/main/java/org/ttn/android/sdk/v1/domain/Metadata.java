package org.ttn.android.sdk.v1.domain;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

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
public class Metadata {

    @SerializedName("frequency")
    Double mFrequency;

    @SerializedName("datarate")
    String mDatarate;

    @SerializedName("codingrate")
    String mCodingRate;

    @SerializedName("gateway_timestamp")
    Long mGatewayTimestamp;

    @SerializedName("gateway_time")
    DateTime mGateWayTime;

    @SerializedName("channel")
    int mChannel;

    @SerializedName("server_time")
    DateTime mServerTime;

    @SerializedName("rssi")
    int mRSSI;

    @SerializedName("lsnr")
    Double mLSNR;

    @SerializedName("rfchain")
    int mRFChain;

    @SerializedName("crc")
    int mCRC;

    @SerializedName("modulation")
    String mModulation;

    @SerializedName("gateway_eui")
    String mGatewayEUI;

    @SerializedName("altitude")
    int mAltitude;

    @SerializedName("latitude")
    Double mLatitude;

    @SerializedName("longitude")
    Double mLongitude;

    public Double getFrequency() {
        return mFrequency;
    }

    public String getDatarate() {
        return mDatarate;
    }

    public String getCodingRate() {
        return mCodingRate;
    }

    public Long getGatewayTimestamp() {
        return mGatewayTimestamp;
    }

    public DateTime getGateWayTime() {
        return mGateWayTime;
    }

    public int getChannel() {
        return mChannel;
    }

    public DateTime getServerTime() {
        return mServerTime;
    }

    public int getRSSI() {
        return mRSSI;
    }

    public Double getLSNR() {
        return mLSNR;
    }

    public int getRFChain() {
        return mRFChain;
    }

    public int getCRC() {
        return mCRC;
    }

    public String getModulation() {
        return mModulation;
    }

    public String getGatewayEUI() {
        return mGatewayEUI;
    }

    public int getAltitude() {
        return mAltitude;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }
}
