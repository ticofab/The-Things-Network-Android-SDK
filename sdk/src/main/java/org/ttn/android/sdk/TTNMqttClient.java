package org.ttn.android.sdk;

import android.util.Log;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.json.JSONException;
import org.json.JSONObject;
import org.ttn.android.sdk.api.converter.PacketConverter;
import org.ttn.android.sdk.api.listeners.MqttApiListener;
import org.ttn.android.sdk.domain.packet.Packet;

import java.net.URISyntaxException;

public class TTNMqttClient {
    private static final String MQTT_HOST = "tcp://croft.thethings.girovito.nl:1883";

    MQTT mMqtt = new MQTT();
    PacketConverter mPacketConverter = new PacketConverter();

    public TTNMqttClient() {
        try {
            mMqtt.setHost(MQTT_HOST);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void packets(String nodeEui, MqttApiListener listener) {
        setupMqtt(nodeEui, listener);
    }

    public void packets(MqttApiListener listener) {
        setupMqtt("+", listener);
    }

    void setupMqtt(String nodeEui, final MqttApiListener listener) {

        // establish connection
        final CallbackConnection conn = mMqtt.callbackConnection();
        conn.listener(new Listener() {
            @Override
            public void onConnected() {
            }

            @Override
            public void onDisconnected() {
            }

            @Override
            public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {

                try {
                    JSONObject jsonObj = new JSONObject(body.ascii().toString());
                    Packet packet = (Packet) mPacketConverter.fromJson(jsonObj);
                    listener.onPacket(packet);
                } catch (JSONException e) {
                    listener.onError(e);
                }

                ack.run();
            }

            @Override
            public void onFailure(Throwable value) {
                listener.onError(value);
            }
        });

        final Topic[] topics = {new Topic("nodes/" + nodeEui + "/packets", QoS.AT_LEAST_ONCE)};

        // connect
        conn.connect(new Callback<Void>() {
            @Override
            public void onFailure(Throwable value) {
                listener.onError(value);
            }

            @Override
            public void onSuccess(Void value) {
                Log.d("t", "connection success");

                // subscribe
                conn.subscribe(topics, new Callback<byte[]>() {
                    public void onSuccess(byte[] qoses) {
                        // The result of the subcribe request.
                        Log.d("t", "subscribed");
                    }

                    public void onFailure(Throwable value) {
                        listener.onError(value);
                    }
                });
            }

        });


    }
}
