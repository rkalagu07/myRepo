package com.arasan.master.ui.feedback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arasan.master.R;
import com.arasan.master.data.network.response.Feedback;
import com.arasan.master.di.listeners.ItemClick;

import java.text.SimpleDateFormat;
import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int ME = 1;
    private static int USER = 2;
    private Context context;
    private List<Feedback> mFeedbacks;
    private ItemClick mItemClick;
    public FeedbackAdapter(Context context, List<Feedback> aFeedbacks,ItemClick aItemClick) {
        this.context = context;
        this.mFeedbacks = aFeedbacks;
        mItemClick = aItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == ME) { // ME
            view = LayoutInflater.from(context).inflate(R.layout.item_chat_me, viewGroup, false);
            return new MeViewHolder(view);

        } else { // User Layout
            view = LayoutInflater.from(context).inflate(R.layout.item_chat_other, viewGroup, false);
            return new UserViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mFeedbacks.get(position).type.equalsIgnoreCase("ADMIN")) {
            return ME;
        } else {
            return USER;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ME) {
            ((MeViewHolder) holder).setMonthDetails(mFeedbacks.get(position));
        } else {
            ((UserViewHolder) holder).setHolidayDetails(mFeedbacks.get(position));
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mFeedbacks.size();
    }

    class MeViewHolder extends RecyclerView.ViewHolder {

        private TextView name,message,created_at;

        MeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_chat_name_me);
            created_at = itemView.findViewById(R.id.text_gchat_timestamp_me);
            message = itemView.findViewById(R.id.text_gchat_message_me);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClick.onItemClick(getAbsoluteAdapterPosition());
                }
            });
        }

        void setMonthDetails(Feedback employee) {
            name.setText(employee.name);
            message.setText(employee.message);
            created_at.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(employee.createdAt));

        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView type,message,created_at;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.text_gchat_user_other);
            message = itemView.findViewById(R.id.text_gchat_message_other);
            created_at = itemView.findViewById(R.id.text_gchat_timestamp_other);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClick.onItemClick(getAbsoluteAdapterPosition());
                }
            });
        }

        void setHolidayDetails(Feedback employee) {
            type.setText(employee.type);
            message.setText(employee.message);
            created_at.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(employee.createdAt));
        }
    }
}
