package sg.edu.np.mad.madpractical;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int VIEW_TYPE_ONE = 1;
    private static final int VIEW_TYPE_TWO = 2;

    ArrayList<User> data;

    public UserListAdapter(ArrayList<User> input) {
        data = input;
    }

    @Override
    public int getItemViewType(int position) {
        String userName = data.get(position).getName();
        char userName_lastChar = userName.charAt(userName.length() - 1);

        if (userName_lastChar == '7') {
            return VIEW_TYPE_TWO;
        }
        else {
            return VIEW_TYPE_ONE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_TWO) {
            View item = inflater.inflate(R.layout.activity_user_list_item_7, parent, false);
            return new UserListViewHolder2(item);
        }
        else {
            View item = inflater.inflate(R.layout.activity_user_list_item, parent, false);
            return new UserListViewHolder1(item);
        }
    }

    @Override
    public void onBindViewHolder(
            RecyclerView.ViewHolder holder,
            int position) {

        User s = data.get(position);

        if (holder.getItemViewType() == VIEW_TYPE_TWO) {
            UserListViewHolder2 viewHolder2 = (UserListViewHolder2) holder;

            viewHolder2.list_userName.setText(s.getName());
            viewHolder2.list_userDesc.setText(s.getDescription());

            viewHolder2.list_userImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent MainActivity = new Intent(v.getContext(), MainActivity.class);

                    AlertDialog.Builder profileInfo = new AlertDialog.Builder(v.getContext());
                    profileInfo.setTitle("Profile");
                    profileInfo.setMessage(s.getName());
                    profileInfo.setCancelable(true);
                    profileInfo.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            MainActivity.putExtra("userID", s.getId());
                            v.getContext().startActivity(MainActivity);
                        }
                    });
                    profileInfo.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            dialog.dismiss();
                        }
                    });

                    AlertDialog profileInfoAlert = profileInfo.create();
                    profileInfoAlert.show();
                }
            });
        }

        else {
            UserListViewHolder1 viewHolder1 = (UserListViewHolder1) holder;

            viewHolder1.list_userName.setText(s.getName());
            viewHolder1.list_userDesc.setText(s.getDescription());

            viewHolder1.list_userImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent MainActivity = new Intent(v.getContext(), MainActivity.class);

                    AlertDialog.Builder profileInfo = new AlertDialog.Builder(v.getContext());
                    profileInfo.setTitle("Profile");
                    profileInfo.setMessage(s.getName());
                    profileInfo.setCancelable(true);
                    profileInfo.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            MainActivity.putExtra("userID", s.getId());
                            v.getContext().startActivity(MainActivity);
                        }
                    });
                    profileInfo.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            dialog.dismiss();
                        }
                    });

                    AlertDialog profileInfoAlert = profileInfo.create();
                    profileInfoAlert.show();
                }
            });
        }
    }

    public int getItemCount() {
        return data.size();
    }
}
