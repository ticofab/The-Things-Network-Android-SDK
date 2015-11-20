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

import org.ttn.android.sdk.TTNClient;
import org.ttn.android.sdk.api.listeners.NodeListener;
import org.ttn.android.sdk.api.listeners.PacketListener;
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

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.node_eui) EditText mNodeEui;
    @Bind(R.id.packet_list) RecyclerView mDataList;

    final TTNClient mTTNClient = new TTNClient();
    final List<Node> mNodes = new ArrayList<>();
    final List<Packet> mPackets = new ArrayList<>();
    final NodeAdapter mNodeAdapter = new NodeAdapter(mNodes);
    final PacketAdapter mPacketAdapter = new PacketAdapter(mPackets);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        // setup recycler view to show packets
        mDataList.setLayoutManager(new LinearLayoutManager(this));
        mDataList.setAdapter(mNodeAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshPackets();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // initial packets refresh
        refreshPackets();
    }

    /**
     * Triggers a packet refresh from the APIs.
     */
    void refreshPackets() {
        String nodeEui = mNodeEui.getText().toString();
        if (TextUtils.isEmpty(nodeEui)) {
            getNodes();
        } else {
            getPackets(nodeEui);
        }
    }

    void getNodes() {
        mTTNClient.getNodes(null, nodeListener);
    }

    void getPackets(String nodeEui) {
        mTTNClient.getPackets(nodeEui, null, null, null, packetListener);
    }

    NodeListener nodeListener = new NodeListener() {
        @Override
        public void onResult(List<Node> nodes) {
            mNodes.clear();
            if (nodes.isEmpty()) {
                // TODO: toast
            } else {
                mNodes.addAll(nodes);
                mDataList.setAdapter(mNodeAdapter);
                mNodeAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError() {
            // TODO: toast
        }
    };

    PacketListener packetListener = new PacketListener() {
        @Override
        public void onResult(List<Packet> packets) {
            mPackets.clear();
            if (packets.isEmpty()) {
                Toast.makeText(MainActivity.this, R.string.no_packets_found, Toast.LENGTH_SHORT).show();
            } else {
                mPackets.addAll(packets);
                mDataList.setAdapter(mPacketAdapter);
                mPacketAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onError() {
            Toast.makeText(MainActivity.this, R.string.failed_to_retrieve_packets, Toast.LENGTH_LONG).show();
        }
    };
}
