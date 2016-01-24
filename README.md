The Things Network - Android SDK
=======

Android SDK to interact with the API of [The Things Network](http://thethingsnetwork.org) servers.


Download
--------

Grab via gradle:

`compile 'io.ticofab:ttn-android-sdk:1.0.0'`

Dependencies
------------

SDK:

* [Retrofit](http://square.github.io/retrofit/)

SAMPLE:

* [Joda DateTime for Android][1]
* [ButterKnife](http://jakewharton.github.io/butterknife/)

Usage
-----

This is going to evolve fast, so for the time being refer to the sample app.

Get a TTNClient instance:

```java
TTNClient mTTNClient = new TTNClient(); // consider injection
```

Then use it to get packets, passing a Listener:

```java
public void getPackets(String nodeEui,
                       String timeSpan,
                       Integer limit,
                       Integer offset,
                       final ApiListener<Packet> packetListener);

mTTNClient.getPackets("my_node_eui", null, null, null, new ApiListener<Packet>() {
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

Sample App
----------

Currently, all the sample app does is query the API for available packets and displays information about those in a list.

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

[1]: https://github.com/dlew/joda-time-android
