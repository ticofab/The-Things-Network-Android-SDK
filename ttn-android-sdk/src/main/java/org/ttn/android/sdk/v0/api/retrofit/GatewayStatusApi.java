package org.ttn.android.sdk.v0.api.retrofit;

import org.ttn.android.sdk.v0.domain.gateway.GatewayStatus;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

@Deprecated
public interface GatewayStatusApi {
    String NODES_API_BASE_URL = "/v0/gateways";

    @GET(NODES_API_BASE_URL + "/{node_eui}")
    void get(@Path("node_eui") String nodeEui,
             @Query("time_span") String timeSpan,
             @Query("limit") int limit,
             @Query("offset") int offset,
             Callback<List<GatewayStatus>> callback);
}
