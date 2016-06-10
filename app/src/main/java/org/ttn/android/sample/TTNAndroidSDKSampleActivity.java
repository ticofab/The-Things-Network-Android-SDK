package org.ttn.android.sample;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.robinhood.spark.SparkView;

import org.ttn.android.sdk.v1.client.MqttApiListener;
import org.ttn.android.sdk.v1.client.TTNMqttClient;
import org.ttn.android.sdk.v1.domain.Packet;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
 * Created by fabiotiriticco on 3 June 2016.
 */
public class TTNAndroidSDKSampleActivity extends AppCompatActivity {
    // log tag
    private static final String TAG = TTNAndroidSDKSampleActivity.class.getSimpleName();

    // credentials to connect
    private static final String APP_EUI = "HIDDEN";
    private static final String ACCESS_KEY = "HIDDEN";
    private static final String STAGING_HOST = "staging.thethingsnetwork.org";

    // our views
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.packet_list) RecyclerView mDataList;
    @Bind(R.id.temperature_view) SparkView mTempView;
    @Bind(R.id.progress_bar) CircleProgressBar mProgressBar;

    // the client
    TTNMqttClient mTTNMqttClient;

    // store the received packets and nodes. This is sample app so we let them grow indefinitely.
    final List<Packet> mPackets = new ArrayList<>();
    final List<Payload> mPayloads = new ArrayList<>();

    // adapters
    final PacketAdapter mPacketAdapter = new PacketAdapter(mPackets);
    final TemperatureAdapter mTemperatureAdapter = new TemperatureAdapter(mPayloads);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind views.
        ButterKnife.bind(this);

        // setup toolbar
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);

        // add sample packets to show something
        mPackets.addAll(SampleData.mSamplePackets);
        mPayloads.addAll(SampleData.mSamplePayloads);

        // initially, setup recycler view to show nodes
        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setAdapter(mPacketAdapter);

        // setup temperature viewer
        mTempView.setAdapter(mTemperatureAdapter);

        // display dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.sample_data_dialog_title);
        builder.setMessage(R.string.sample_data_dialog_message);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // initial data refresh
        subscribe();
    }

    @Override
    public void onStop() {
        super.onStop();

        // the MQTT client
        mTTNMqttClient.disconnect();
    }

    /**
     * Triggers a data refresh from the APIs. Whether the user has inserted a node EUI in the field
     * or not, this function will retrieve nodes or packets from one specific node.
     */
    void subscribe() {
        mProgressBar.setVisibility(View.VISIBLE);

        // instantiate a new client
        mTTNMqttClient = new TTNMqttClient(STAGING_HOST, APP_EUI, ACCESS_KEY, "+");

        // and subscribe for new packets.
        mTTNMqttClient.listen(new MqttApiListener() {
            @Override
            public void onPacket(final Packet packet) {
                // notify user
                Log.d(TAG, "onPacket");
                toastOnUiThread(getString(R.string.packet_received));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // insert packet at the top of the list
                        mPackets.add(0, packet);
                        mPacketAdapter.notifyItemInserted(0);
                        mDataList.scrollToPosition(0);

                        // update temp chart
                        Payload payload = Payload.fromEncodedPayload(packet.getPayload());
                        mPayloads.add(payload);
                        mTemperatureAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(final Throwable throwable) {
                // notify user
                Log.e(TAG, "onError: " + throwable.getMessage());
                toastOnUiThread(getString(R.string.mqtt_error, throwable.getMessage()));
            }

            @Override
            public void onConnected() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "onConnected");
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onDisconnected() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "onDisconnected");
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    void toastOnUiThread(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TTNAndroidSDKSampleActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

}
