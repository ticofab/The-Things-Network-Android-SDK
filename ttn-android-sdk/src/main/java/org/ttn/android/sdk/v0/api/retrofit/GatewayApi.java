package org.ttn.android.sdk.v0.api.retrofit;

import org.ttn.android.sdk.v0.domain.gateway.Gateway;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

@Deprecated
public interface GatewayApi {
    String NODES_API_BASE_URL = "/v0/gateways";

    @GET(NODES_API_BASE_URL)
    void get(@Query("time_span") String timeSpan,
             @Query("limit") int limit,
             @Query("offset") int offset,
             Callback<List<Gateway>> callback);
}
