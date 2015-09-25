package org.ttn.android.sample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.ttn.android.sdk.Listener;
import org.ttn.android.sdk.TTNClient;
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
    @Bind(R.id.packet_list) RecyclerView mPacketList;

    final TTNClient mTTNClient = new TTNClient();
    final List<Packet> mPackets = new ArrayList<>();
    final PacketAdapter mAdapter = new PacketAdapter(mPackets);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        // setup recycler view to show packets
        mPacketList.setLayoutManager(new LinearLayoutManager(this));
        mPacketList.setAdapter(mAdapter);

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
        mTTNClient.get(new Listener() {
            @Override
            public void onResult(List<Packet> packets) {
                mPackets.addAll(packets);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError() {
                Toast.makeText(MainActivity.this, "Failed to retrieve packets", Toast.LENGTH_LONG).show();
            }
        });
    }
}
