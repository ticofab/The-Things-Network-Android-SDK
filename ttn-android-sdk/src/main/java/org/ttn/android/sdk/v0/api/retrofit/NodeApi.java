package org.ttn.android.sdk.v0.api.retrofit;

import org.ttn.android.sdk.v0.domain.node.Node;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

@Deprecated
public interface NodeApi {
    String NODES_API_BASE_URL = "/v0/nodes";

    @GET(NODES_API_BASE_URL)
    void get(@Query("time_span") String timeSpan,
             Callback<List<Node>> callback);

}
