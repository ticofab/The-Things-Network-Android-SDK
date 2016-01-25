package org.ttn.android.sdk.domain.packet;

import org.joda.time.DateTime;

public class Packet {
    final String mData;
    final String mDataRaw;
    final String mDataJson;
    final String mDataPlain;
    final String mGatewayEui;
    final String mNodeEui;
    final DateTime mTime;
    final Double mFrequency;
    final Integer mRSSI;
    final Double mSNR;

    public Packet(Builder builder) {
        mData = builder.mData;
        mDataPlain = builder.mDataPlain;
        mDataRaw = builder.mDataRaw;
        mDataJson = builder.mDataJson;
        mGatewayEui = builder.mGatewayEui;
        mNodeEui = builder.mNodeEui;
        mTime = builder.mTime;
        mFrequency = builder.mFrequency;
        mRSSI = builder.mRSSI;
        mSNR = builder.mSNR;
    }

    public String getData() {
        return mData;
    }

    public String getDataRaw() {
        return mDataRaw;
    }

    public String getDataJson() {
        return mDataJson;
    }

    public String getDataPlain() {
        return mDataPlain;
    }

    public String getGatewayEui() {
        return mGatewayEui;
    }

    public String getNodeEui() {
        return mNodeEui;
    }

    public DateTime getTime() {
        return mTime;
    }

    public Double getFrequency() {
        return mFrequency;
    }

    public Integer getRSSI() {
        return mRSSI;
    }

    public Double getSNR() {
        return mSNR;
    }


    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }

        if (other == this) {
            return true;
        }

        if (other.getClass() == getClass()) {
            final Packet otherPacket = (Packet) other;
            return getData().equals(otherPacket.getData()) &&
                    getDataJson().equals(otherPacket.getDataJson()) &&
                    getDataPlain().equals(otherPacket.getDataPlain()) &&
                    getDataRaw().equals(otherPacket.getDataRaw()) &&
                    getGatewayEui().equals(otherPacket.getGatewayEui()) &&
                    getNodeEui().equals(otherPacket.getNodeEui()) &&
                    getTime().equals(otherPacket.getTime()) &&
                    getFrequency().equals(otherPacket.getFrequency()) &&
                    getRSSI().equals(otherPacket.getRSSI()) &&
                    getSNR().equals(otherPacket.getSNR());
        }

        return super.equals(other);
    }

    public static class Builder {
        private String mData;
        private String mDataRaw;
        private String mDataJson;
        private String mDataPlain;
        private String mGatewayEui;
        private String mNodeEui;
        private DateTime mTime;
        private Double mFrequency;
        private Integer mRSSI;
        private Double mSNR;

        public Builder setData(String data) {
            mData = data;
            return this;
        }

        public Builder setDataRaw(String dataRaw) {
            mDataRaw = dataRaw;
            return this;
        }

        public Builder setDataPlain(String dataPlain) {
            mDataPlain = dataPlain;
            return this;
        }

        public void setDataJson(String dataJson) {
            mDataJson = dataJson;
        }

        public Builder setGatewayEui(String gatewayEui) {
            mGatewayEui = gatewayEui;
            return this;
        }

        public Builder setNodeEui(String nodeEui) {
            mNodeEui = nodeEui;
            return this;
        }

        public Builder setTime(DateTime time) {
            mTime = time;
            return this;
        }

        public Builder setFrequency(Double frequency) {
            mFrequency = frequency;
            return this;
        }

        public Builder setRSSI(Integer rssi) {
            mRSSI = rssi;
            return this;
        }

        public Builder setSNR(Double snr) {
            mSNR = snr;
            return this;
        }

        public Packet build() {
            return new Packet(this);
        }
    }
}

