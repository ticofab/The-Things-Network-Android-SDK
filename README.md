The Things Network - Android SDK
================================

Android SDK to interact with the API of [The Things Network](http://thethingsnetwork.org) servers.

Sample App
----------

Check the sample app for examples on how to use the code.

* First, it uses the REST API to get the available nodes.
* Click on one and it loads all available packets from that node.
* ..and it subscribes to the MQTT client to receive new packets as they are published.

Try the sample app on your device (and report issues if you find any!):

<a href="https://play.google.com/store/apps/details?id=org.ttn.android.sample&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-AC-global-none-all-co-pr-py-PartBadges-Oct1515-1"><img width="200" alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/apps/en-play-badge.png" /></a>

Download SDK
------------

Grab via gradle:

```groovy
compile 'io.ticofab:ttn-android-sdk:1.1.1'
```

Usage
-----

This is going to evolve fast, so for the time being refer to the sample app.

**REST API**

Get a TTNRestClient instance:

```java
final TTNRestClient mTTNRestClient = new TTNRestClient(); // consider injection
```

Then use it to get packets, passing a Listener:

```java
public void getPackets(String nodeEui,
                       String timeSpan,
                       Integer limit,
                       Integer offset,
                       final ApiListener<Packet> packetListener);

mTTNRestClient.getPackets("my_node_eui", null, null, null, new ApiListener<Packet>() {
    @Override
    public void onResult(List<Packet> packets) {
        // got packets
    }

    @Override
    public void onError() {
        // error
    }
});
```
Beside packets, through TTNClient you can get this way also nodes, gateways and gateway statuses:

```java
public void getNodes(String timeSpan,
                     final ApiListener<Node> nodeListener);

public void getGateways(String timeSpan,
                        Integer limit,
                        Integer offset,
                        final ApiListener<Gateway> gatewayListener);

public void getGatewayStatuses(String gatewayEui,
                               String timeSpan,
                               Integer limit,
                               Integer offset,
                               final ApiListener<GatewayStatus> gsListener);
```

**MQTT API**

This API lets you listen to all or one specific node.

Get a TTNMqttClient instance:

```java
final TTNMqttClient mTTNMqttClient = new TTNMqttClient(); // consider injection
```

Then, you can subscribe for updates from a specific packet passing a listener:

```java
// subscribe for new packets.
mTTNMqttClient.packets("myNodeEui", new MqttApiListener() {
    @Override
    public void onPacket(final Packet packet) {
        // do something
        // NOTE: this callback doesn't happen on the main thread!
    }

    @Override
    public void onError(final Throwable throwable) {
        // error
        // NOTE: this callback doesn't happen on the main thread!
    }
});
```

Dependencies
------------

SDK:

* [Retrofit](http://square.github.io/retrofit/)
* [MQTT-Client](https://github.com/fusesource/mqtt-client)
* [Joda DateTime for Android](https://github.com/dlew/joda-time-android)

SAMPLE:

* [ButterKnife](http://jakewharton.github.io/butterknife/)
* [Otto Event Bus](http://square.github.io/otto/)
* [Material Progress Bar](https://github.com/lsjwzh/MaterialLoadingProgressBar)


License
--------

    Copyright 2015 The Things Network

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

