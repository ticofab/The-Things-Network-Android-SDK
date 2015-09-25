package org.ttn.android.sdk.api.client;/*
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

import org.ttn.android.sdk.api.converter.PacketConverter;
import org.ttn.android.sdk.api.retrofit.ServiceGenerator;
import org.ttn.android.sdk.api.retrofit.v0.NodeApi;

public class ApiClientV0 {

    final NodeApi mNodeService;

    public ApiClientV0() {
        ServiceGenerator serviceGenerator = new ServiceGenerator();
        PacketConverter packetConverter = new PacketConverter();
        mNodeService = serviceGenerator.createService(NodeApi.class, packetConverter);
    }

    public NodeApi getNodeService() {
        return mNodeService;
    }
}
