The Things Network - Android SDK
=======

Android SDK to interact with the API of [The Things Network](http://thethingsnetwork.org) servers.  


Download
--------

Soon in jCenter.

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
mTTNClient.get(new Listener() {
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
