package org.ttn.android.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.ttn.android.sdk.TTNMqttClient;
import org.ttn.android.sdk.TTNRestClient;
import org.ttn.android.sdk.api.listeners.MqttApiListener;
import org.ttn.android.sdk.api.listeners.RestApiListener;
import org.ttn.android.sdk.domain.node.Node;
import org.ttn.android.sdk.domain.packet.Packet;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * Copyright 2015 The Things Network
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
 * Created by fabiotiriticco on 25/09/15.
 *
 */
public class MainActivity extends AppCompatActivity {

    // our views
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.node_eui) EditText mNodeEui;
    @Bind(R.id.packet_list) RecyclerView mDataList;
    @Bind(R.id.progress_bar) CircleProgressBar mProgressBar;

    // the event bus
    final Bus mBus = new Bus();

    // store the received packets and nodes. This is sample app so we let them grow indefinitely.
    final List<Node> mNodes = new ArrayList<>();
    final List<Packet> mPackets = new ArrayList<>();

    // this guy should be preferably injected
    final TTNRestClient mTTNRestClient = new TTNRestClient();

    // this guy should be preferably injected
    final TTNMqttClient mTTNMqttClient = new TTNMqttClient();

    // adapters
    final PacketAdapter mPacketAdapter = new PacketAdapter(mPackets);
    final NodeAdapter mNodeAdapter = new NodeAdapter(mBus, mNodes);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bind views.
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        // initially, setup recycler view to show nodes
        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setAdapter(mNodeAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshData();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // initial data refresh
        refreshData();
    }

    @Override
    public void onResume() {
        super.onResume();

        // register the bus for receiving events.
        mBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        // unregister the bus.
        mBus.unregister(this);
    }

    @Subscribe
    public void onNodeSelected(NodeSelectedEvent event) {
        mNodeEui.setText(event.mNodeEui);
        refreshData();
    }

    /**
     * Triggers a data refresh from the APIs. Whether the user has inserted a node EUI in the field
     * or not, this function will retrieve nodes or packets from one specific node.
     */
    void refreshData() {
        mProgressBar.setVisibility(View.VISIBLE);
        String nodeEui = mNodeEui.getText().toString();
        if (TextUtils.isEmpty(nodeEui)) {

            // no node was specified. Let's get nodes.
            getNodes();

            // and make sure our live MQTT listener is disconnected,
            mTTNMqttClient.disconnect();

        } else {

            // a particular node was specified. Get packets from this node.
            getPackets(nodeEui);

            // and subscribe for new packets.
            mTTNMqttClient.packets(nodeEui, new MqttApiListener() {
                @Override
                public void onPacket(Packet packet) {
                    // notify user
                    Toast.makeText(MainActivity.this, R.string.packet_received, Toast.LENGTH_SHORT).show();

                    // insert packet at the top of the list
                    mPackets.add(mPackets.size(), packet);
                    mPacketAdapter.notifyItemInserted(mPackets.size() - 1);
                }

                @Override
                public void onError(Throwable throwable) {
                    // notify user
                    Toast.makeText(MainActivity.this, getString(R.string.mqtt_error, throwable.getMessage()), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * Get all nodes from the REST Apis.
     */
    void getNodes() {
        mTTNRestClient.getNodes(null, mNodeListener);
    }

    /**
     * Gets all packets of a specific node from the REST Api.
     *
     * @param nodeEui The unique node ID.
     */
    void getPackets(String nodeEui) {
        mTTNRestClient.getPackets(nodeEui, null, null, null, mPacketListener);
    }

    // callback for node request to the REST Api.
    RestApiListener<Node> mNodeListener = new RestApiListener<Node>() {
        @Override
        public void onResult(List<Node> nodes) {
            mProgressBar.setVisibility(View.GONE);
            mNodes.clear();
            if (nodes.isEmpty()) {
                Toast.makeText(MainActivity.this, R.string.no_nodes_found, Toast.LENGTH_SHORT).show();
            } else {
                mNodes.addAll(nodes);
                mDataList.setAdapter(mNodeAdapter);
                mNodeAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError() {
            // something went wrong.
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, R.string.failed_to_retrieve_nodes, Toast.LENGTH_LONG).show();
        }
    };

    // callback for packet requests to the REST Api.
    RestApiListener<Packet> mPacketListener = new RestApiListener<Packet>() {
        @Override
        public void onResult(List<Packet> packets) {
            mProgressBar.setVisibility(View.GONE);
            mPackets.clear();
            if (packets.isEmpty()) {
                Toast.makeText(MainActivity.this, R.string.no_packets_found, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, R.string.packets_retrieved, Toast.LENGTH_LONG).show();
                mPackets.addAll(packets);
                mDataList.setAdapter(mPacketAdapter);
                mPacketAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError() {
            // something went wrong.
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, R.string.failed_to_retrieve_packets, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onBackPressed() {
        if (!TextUtils.isEmpty(mNodeEui.getText())) {

            // the user has inserted a specific node eui. Let's clear that.
            mNodeEui.setText("");
            refreshData();

        } else {

            // let the user go somewhere else, even if it hurts.
            super.onBackPressed();

        }
    }
}
