package org.ttn.android.sample;

import android.test.InstrumentationTestCase;

public class PayloadTest extends InstrumentationTestCase {
    public void testPayloadFunction() throws Exception {
        String p = "CUUCbw==";
        Payload payload = Payload.fromEncodedPayload(p);
        assertEquals((int) payload.mLux, 623);
        assertEquals(payload.mTemp * 100, 2373.0);
    }
}

