package org.ttn.android.sdk.v1.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Packet {

    /*
    Example incoming uplink packet:

    {
        "payload":"CUUCbw==",
        "port":1,
        "counter":4950,
        "dev_eui":"0004A30B001B442B",
        "metadata":[
            {
                "frequency":868.5,
                "datarate":"SF12BW125",
                "codingrate":"4/5",
                "gateway_timestamp":2899320620,
                "gateway_time":"2016-06-05T08:54:43.376773Z",
                "channel":1,
                "server_time":"2016-06-05T08:54:43.422660261Z",
                "rssi":-111,
                "lsnr":4.5,
                "rfchain":1,
                "crc":1,
                "modulation":"LORA",
                "gateway_eui":"0000024B08060112",
                "altitude":22,
                "longitude":4.88661,
                "latitude":52.37371
        }
        ]
    }
    */

    @SerializedName("payload")
    String mPayload;

    @SerializedName("port")
    int mPort;

    @SerializedName("counter")
    int mCounter;

    @SerializedName("dev_eui")
    String mDevEUI;

    @SerializedName("metadata")
    List<Metadata> mMetadata;

    @SerializedName("ttl")
    String mTTL;

    public String getPayload() {
        return mPayload;
    }

    public int getPort() {
        return mPort;
    }

    public int getCounter() {
        return mCounter;
    }

    public String getDevEUI() {
        return mDevEUI;
    }

    public List<Metadata> getMetadata() {
        return mMetadata;
    }

    public String getTTL() {
        return mTTL;
    }

}

