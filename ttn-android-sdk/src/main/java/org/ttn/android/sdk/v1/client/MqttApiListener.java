package org.ttn.android.sdk.v1.client;


import org.ttn.android.sdk.v1.domain.Packet;

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
 * Created by fabiotiriticco on 3 June 2016.
 *
 */
public interface MqttApiListener {
    void onPacket(Packet packet);

    void onError(Throwable throwable);

    void onConnected();

    void onDisconnected();
}
