package org.ttn.android.sdk;/*
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
import org.ttn.android.sdk.domain.CollectionContainer;
import org.ttn.android.sdk.domain.packet.Packet;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TTNClient {

    final ApiClientV0 mApiClient = new ApiClientV0();

    public List<Packet> get(final Listener listener) {
        return get(null, listener);
    }

    public List<Packet> get(String timespan, final Listener listener) {
        mApiClient.getNodeService().get(timespan, new Callback<CollectionContainer<Packet>>() {
            @Override
            public void success(CollectionContainer<Packet> container, Response response) {
                listener.onResult(new ArrayList<>(container.mCollection));
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onError();
            }
        });
    }
}
