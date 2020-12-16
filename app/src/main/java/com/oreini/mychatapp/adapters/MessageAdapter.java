package com.oreini.mychatapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.oreini.mychatapp.R;
import com.oreini.mychatapp.models.Message;

import java.util.ArrayList;
import java.util.Calendar;

import static com.oreini.mychatapp.utils.MyChatApp.mContext;
import static com.oreini.mychatapp.utils.MyChatApp.mSharedPrefsManager;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList<Message> mMessageaList;

    public MessageAdapter(ArrayList<Message> messagesList) {
        this.mMessageaList = messagesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message current = mMessageaList.get(position);
        if (current.getAuthor().getUserID().equals(mSharedPrefsManager.getUserID())) {
            holder.rlLayout.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_sent_message));
            holder.tvAuthor.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvTime.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvMessage.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.tvAuthor.setText("You");
        } else {
            holder.rlLayout.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_recevied_message));
            holder.tvAuthor.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
            holder.tvTime.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
            holder.tvMessage.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
            holder.tvAuthor.setText(current.getAuthor().getUserName());
        }
        holder.tvMessage.setText(current.getMessage());
        holder.tvTime.setText(getTimeStr(current.getTimestamp().getTime()));
    }

    @Override
    public int getItemCount() {
        return mMessageaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlLayout;
        private TextView tvAuthor;
        private TextView tvMessage;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            rlLayout = itemView.findViewById(R.id.rlLayout);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvMessage = itemView.findViewById(R.id.tvMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    private String getTimeStr(long timeMillis) {

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeMillis);
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%02d", hours));
        sb.append(":");
        sb.append(String.format("%02d", minutes));

        return sb.toString();
    }
}

