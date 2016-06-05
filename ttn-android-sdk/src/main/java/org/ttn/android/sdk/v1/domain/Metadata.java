package org.ttn.android.sdk.v1.domain;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

public class Metadata {

    @SerializedName("frequency")
    Double mFrequency;

    @SerializedName("datarate")
    String mDatarate;

    @SerializedName("codingrate")
    String mCodingRate;

    @SerializedName("gateway_timestamp")
    int mGatewayTimestamp;

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
}
