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
