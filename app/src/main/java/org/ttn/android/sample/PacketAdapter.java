package org.ttn.android.sample;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.ttn.android.sdk.v1.domain.Packet;

import java.text.DateFormat;
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

public class PacketAdapter extends RecyclerView.Adapter<PacketAdapter.ViewHolder> {

    List<Packet> mPackets;
    DateFormat mDateFormatter = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

    public PacketAdapter(List<Packet> packets) {
        mPackets = packets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_packet, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Packet packet = mPackets.get(position);

        // node eui
        String nodeEui = packet.getDevEUI();
        if (!TextUtils.isEmpty(nodeEui)) {
            vh.mDeviceId.setVisibility(View.VISIBLE);
            vh.mDeviceId.setText(nodeEui);
        } else {
            vh.mDeviceId.setVisibility(View.GONE);
            vh.mDeviceId.setText(null);
        }

        // timestamp
        DateTime date = packet.getMetadata().get(0).getServerTime();
        if (date != null) {
            vh.mTime.setVisibility(View.VISIBLE);
            vh.mTime.setText(mDateFormatter.format(date.toDate()));
        } else {
            vh.mTime.setVisibility(View.GONE);
            vh.mTime.setText(null);
        }
    }

    @Override
    public int getItemCount() {
        return mPackets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.packet_time) TextView mTime;
        @Bind(R.id.packet_device_id) TextView mDeviceId;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
