package org.ttn.android.sdk;

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

import org.ttn.android.sdk.api.client.ApiClientV0;
import org.ttn.android.sdk.api.listeners.GatewayStatusListener;
import org.ttn.android.sdk.api.listeners.NodeListener;
import org.ttn.android.sdk.api.listeners.PacketListener;
import org.ttn.android.sdk.domain.gateway.GatewayStatus;
import org.ttn.android.sdk.domain.node.Node;
import org.ttn.android.sdk.domain.packet.Packet;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TTNClient {

    final ApiClientV0 mApiClient = new ApiClientV0();

    public void getPackets(String nodeEui,
                           String timeSpan,
                           String limit,
                           String offset,
                           final PacketListener packetListener) {
        mApiClient.getPacketService().get(nodeEui, timeSpan, limit, offset, getCallback(packetListener));
    }

    public void getNodes(String timeSpan,
                         final NodeListener nodeListener) {
        mApiClient.getNodeService().get(timeSpan, getCallback(nodeListener));
    }

    public void getGatewayStatusList(String timeSpan,
                                     int limit,
                                     int offset,
                                     final GatewayStatusListener gsListener) {
        mApiClient.getGatewaysService().get(timeSpan, limit, offset, getCallback(gsListener));
    }

    public void getGatewayStatus(String gatewayEui,
                                 String timeSpan,
                                 int limit,
                                 int offset,
                                 final GatewayStatusListener gsListener) {
        mApiClient.getGatewaysService().get(gatewayEui, timeSpan, limit, offset, getCallback(gsListener));
    }

    Callback<List<Packet>> getCallback(final PacketListener listener) {
        return new Callback<List<Packet>>() {
            @Override
            public void success(List<Packet> list, Response response) {
                listener.onResult(list);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        };
    }

    Callback<List<GatewayStatus>> getCallback(final GatewayStatusListener listener) {
        return new Callback<List<GatewayStatus>>() {
            @Override
            public void success(List<GatewayStatus> statuses, Response response) {
                listener.onResult(statuses);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        };
    }

    Callback<List<Node>> getCallback(final NodeListener listener) {
        return new Callback<List<Node>>() {
            @Override
            public void success(List<Node> nodes, Response response) {
                listener.onResult(nodes);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        };
    }

}
