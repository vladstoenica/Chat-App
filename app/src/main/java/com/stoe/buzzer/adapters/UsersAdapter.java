package com.stoe.buzzer.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stoe.buzzer.R;
//import com.stoe.buzzer.databinding.ItemContainerUserBinding;
import com.stoe.buzzer.listeners.UserListener;
import com.stoe.buzzer.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{

    private final List<User> users;
    private final UserListener userListener;

    public UsersAdapter(List<User> users, UserListener userListener) {
        this.users = users;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_container_user, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        //  ItemContainerUserBinding binding;
        TextView textName, textEmail;
        ImageView imageProfile;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textNameCard);
            textEmail = itemView.findViewById(R.id.textEmailCard);
            imageProfile = itemView.findViewById(R.id.imageProfileCard);

        }

        void setUserData(User user){
            textName.setText(user.name);
            textEmail.setText(user.email);
            imageProfile.setImageBitmap(getUserImage(user.image));
            itemView.getRootView().setOnClickListener(v -> userListener.onUserClicked(user));  //era diferit cu binding
        }
    }

    private Bitmap getUserImage(String encodedImage){
        byte[] bytes = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0 , bytes.length);
    }
}
