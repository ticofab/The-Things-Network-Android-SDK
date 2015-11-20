package org.ttn.android.sdk.api.listeners;

import org.ttn.android.sdk.domain.node.Node;

import java.util.List;

public interface NodeListener {
    void onResult(List<Node> packets);

    void onError();
}
