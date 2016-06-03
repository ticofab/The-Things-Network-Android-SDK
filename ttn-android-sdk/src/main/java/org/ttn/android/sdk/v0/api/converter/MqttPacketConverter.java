package org.ttn.android.sdk.v0.api.converter;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONException;
import org.json.JSONObject;
import org.ttn.android.sdk.v0.api.converter.base.JsonConverter;
import org.ttn.android.sdk.v0.api.converter.base.JsonErrorMessage;
import org.ttn.android.sdk.v0.domain.packet.Packet;

/*
 * Copyright 2016 Fabio Tiriticco / Fabway
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by fabiotiriticco on 25/09/15.
 */
@Deprecated
public class MqttPacketConverter extends JsonConverter {

    // example of a packet coming from MQTT
    /*
    {
        "gatewayEui":"1DEE0DF33702B031",
        "nodeEui":"02011B01",
        "time":"2016-02-01T08:27:03.13697068Z",
        "frequency":868.5,
        "dataRate":"SF7BW125",
        "rssi":-53,
        "snr":7,
        "rawData":"QAEbAQKACgABieNcoUcBwskLvCk80jPLrnxCSM4cDeQUqQJYELy0atRxChPhq9xhu2IV2zwpEp9U1cpZEC6VvelJ54Oh\/nENAuw=",
        "data":"e3RlbXAwOjIxLjAwLHRlbXAxOjIzLjA2LHRlbXAyOjIzLjAwLHRlbXAzOjIzLjA2LGh1bWkwOjE3LjAwfQ=="
    }
    */

    public static final String JSON_KEY_SNR = "snr";
    public static final String JSON_KEY_TIME = "time";
    public static final String JSON_KEY_DATA = "data";
    public static final String JSON_KEY_RSSI = "rssi";
    public static final String JSON_KEY_NODE_EUI = "nodeEui";
    public static final String JSON_KEY_DATA_RAW = "rawData";
    public static final String JSON_KEY_DATA_RATE = "dataRate";
    public static final String JSON_KEY_FREQUENCY = "frequency";
    public static final String JSON_KEY_GATEWAY_EUI = "gatewayEui";

    @Override
    public Object fromJson(JSONObject jsonObj) throws JSONException {
        Packet.Builder builder = new Packet.Builder();

        if (jsonObj.has(JSON_KEY_DATA)) {
            builder.setData(jsonObj.getString(JSON_KEY_DATA));
        }

        if (jsonObj.has(JSON_KEY_DATA_RAW)) {
            builder.setDataRaw(JSON_KEY_DATA_RAW);
        }

        if (jsonObj.has(JSON_KEY_DATA_RATE)) {
            builder.setDataRate(jsonObj.getString(JSON_KEY_DATA_RATE));
        }

        if (jsonObj.has(JSON_KEY_FREQUENCY)) {
            builder.setFrequency(jsonObj.getDouble(JSON_KEY_FREQUENCY));
        }

        if (jsonObj.has(JSON_KEY_RSSI)) {
            builder.setRSSI(jsonObj.getInt(JSON_KEY_RSSI));
        }

        if (jsonObj.has(JSON_KEY_SNR)) {
            builder.setSNR(jsonObj.getDouble(JSON_KEY_SNR));
        }

        if (jsonObj.has(JSON_KEY_GATEWAY_EUI)) {
            builder.setGatewayEui(jsonObj.getString(JSON_KEY_GATEWAY_EUI));
        }

        if (jsonObj.has(JSON_KEY_NODE_EUI)) {
            builder.setNodeEui(jsonObj.getString(JSON_KEY_NODE_EUI));
        }

        if (jsonObj.has(JSON_KEY_TIME)) {
            String dateStr = jsonObj.getString(JSON_KEY_TIME);
            DateTime time = null;
            try {
                time = ISODateTimeFormat.dateTime().parseDateTime(dateStr);
            } catch (IllegalArgumentException e1) {
                // maybe we need to add a Z at the end? Someone might be sending wrong dates
                try {
                    time = ISODateTimeFormat.dateTime().parseDateTime(dateStr + "Z");
                } catch (IllegalArgumentException e2) {
                    // pity, nothing to do
                }
            }
            if (time != null) {
                builder.setTime(time);
            }
        }

        return builder.build();
    }

    @Override
    public JSONObject toJson(Object object) throws JSONException {
        if (!(object instanceof Packet)) {
            throw new JSONException(JsonErrorMessage.unexpectedObject(Packet.class, object));
        }

        Packet packet = (Packet) object;
        final JSONObject jsonObj = new JSONObject();

        // TODO

        return jsonObj;
    }
}
