package org.ttn.android.sample;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.ttn.android.sdk.domain.node.Node;

import java.text.DateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.ViewHolder> {

    List<Node> mNodes;
    DateFormat mDateFormatter = DateFormat.getDateInstance(DateFormat.FULL);

    public NodeAdapter(List<Node> nodes) {
        mNodes = nodes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_node, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Node node = mNodes.get(position);

        // node eui
        String nodeEui = node.getNodeEui();
        if (!TextUtils.isEmpty(nodeEui)) {
            vh.mNodeId.setVisibility(View.VISIBLE);
            vh.mNodeId.setText(nodeEui);
        } else {
            vh.mNodeId.setVisibility(View.GONE);
            vh.mNodeId.setText(null);
        }

        // last seen
        DateTime lastSeen = node.getLastSeen();
        if (lastSeen != null) {
            vh.mLastSeen.setVisibility(View.VISIBLE);
            vh.mLastSeen.setText(mDateFormatter.format(lastSeen.toDate()));
        } else {
            vh.mLastSeen.setVisibility(View.GONE);
            vh.mLastSeen.setText(null);
        }

        // last gateway
        String lastGateway = node.getLastGatewayEui();
        if (!TextUtils.isEmpty(lastGateway)) {
            vh.mLastGatewayEui.setVisibility(View.VISIBLE);
            vh.mLastGatewayEui.setText(lastGateway);
        } else {
            vh.mLastGatewayEui.setVisibility(View.GONE);
            vh.mLastGatewayEui.setText(null);
        }

        // packets count
        Integer packetsCount = node.getPacketsCount();
        if (packetsCount != null) {
            vh.mPacketsCount.setVisibility(View.VISIBLE);
            vh.mPacketsCount.setText(String.format("%d", packetsCount));
        } else {
            vh.mPacketsCount.setVisibility(View.GONE);
            vh.mPacketsCount.setText(null);
        }
    }

    @Override
    public int getItemCount() {
        return mNodes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.node_eui) TextView mNodeId;
        @Bind(R.id.node_last_seen) TextView mLastSeen;
        @Bind(R.id.node_packets_count) TextView mPacketsCount;
        @Bind(R.id.node_last_gateway_eui) TextView mLastGatewayEui;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
