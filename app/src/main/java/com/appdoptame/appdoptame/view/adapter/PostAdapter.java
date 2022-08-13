package com.appdoptame.appdoptame.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.appdoptame.appdoptame.R;
import com.appdoptame.appdoptame.model.Post;
import com.appdoptame.appdoptame.util.DateTextGetter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Post> posts;
    private final Context context;

    public PostAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.context  = context;
        this.posts    = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.userName.setText(post.getUser().getName());
        holder.time.setText(DateTextGetter.getDateText(post.getDate()));
        holder.description.setText(post.getPet().getDescription());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView        userName;
        TextView        time;
        TextView        description;
        TextView        adoptButton;
        LinearLayout    likeButton;
        TextView        likeCount;
        LinearLayout    commentButton;
        TextView        commentCount;
        LinearLayout    shareButton;
        TextView        shareCount;
        CircleImageView userImage;
        ViewPager2      imageView;

        ViewHolder(View itemView) {
            super(itemView);

            userName      = itemView.findViewById(R.id.post_item_user_name);
            time          = itemView.findViewById(R.id.post_item_time);
            description   = itemView.findViewById(R.id.post_item_description);
            adoptButton   = itemView.findViewById(R.id.post_item_adopt_button);
            likeButton    = itemView.findViewById(R.id.post_item_like_button);
            likeCount     = itemView.findViewById(R.id.post_item_like_count);
            commentButton = itemView.findViewById(R.id.post_item_comment_button);
            commentCount  = itemView.findViewById(R.id.post_item_comment_count);
            shareButton   = itemView.findViewById(R.id.post_item_share_button);
            shareCount    = itemView.findViewById(R.id.post_item_share_count);
            userImage     = itemView.findViewById(R.id.post_item_user_image);
            imageView     = itemView.findViewById(R.id.post_item_image_view);
        }
    }
}