package org.ttn.android.sdk.v1.domain;

import com.google.gson.annotations.SerializedName;

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

