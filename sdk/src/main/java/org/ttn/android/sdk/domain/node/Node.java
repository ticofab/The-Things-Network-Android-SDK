package org.ttn.android.sdk.domain.node;

import org.joda.time.DateTime;

public class Node {

    final String mNodeEui;
    final DateTime mLastSeen;
    final Integer mPacketsCount;
    final String mLastGatewayEui;

    public String getNodeEui() {
        return mNodeEui;
    }

    public DateTime getLastSeen() {
        return mLastSeen;
    }

    public Integer getPacketsCount() {
        return mPacketsCount;
    }

    public String getLastGatewayEui() {
        return mLastGatewayEui;
    }

    public Node(Builder builder) {
        mNodeEui = builder.mNodeEui;
        mLastSeen = builder.mLastSeen;
        mPacketsCount = builder.mPacketsCount;
        mLastGatewayEui = builder.mLastGatewayEui;
    }

    public static class Builder {
        private String mNodeEui;
        private DateTime mLastSeen;
        private Integer mPacketsCount;
        private String mLastGatewayEui;

        public Builder setLastGatewayEui(String lastGatewayEui) {
            mLastGatewayEui = lastGatewayEui;
            return this;
        }

        public Builder setNodeEui(String nodeEui) {
            mNodeEui = nodeEui;
            return this;
        }

        public Builder setLastSeen(DateTime lastSeen) {
            mLastSeen = lastSeen;
            return this;
        }

        public Builder setPacketsCount(Integer packetsCount) {
            mPacketsCount = packetsCount;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }
}
