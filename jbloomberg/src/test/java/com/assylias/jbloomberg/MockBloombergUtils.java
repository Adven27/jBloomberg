package com.assylias.jbloomberg;

import mockit.Mock;
import mockit.MockUp;

public class MockBloombergUtils extends MockUp<BloombergUtils> {

    private final boolean started;

    public MockBloombergUtils(boolean started) {
        super();
        this.started = started;
    }

    @Mock
    public boolean startBloombergProcessIfNecessary() {
        return this.started;
    }
}
