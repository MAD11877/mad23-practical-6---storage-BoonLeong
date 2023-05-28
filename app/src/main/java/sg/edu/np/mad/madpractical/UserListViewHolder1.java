package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserListViewHolder1 extends RecyclerView.ViewHolder {
    TextView list_userName;
    TextView list_userDesc;
    ImageView list_userImage;

    public UserListViewHolder1(View itemView) {
        super(itemView);
        list_userName = itemView.findViewById(R.id.list_userName);
        list_userDesc = itemView.findViewById(R.id.list_userDesc);
        list_userImage = itemView.findViewById(R.id.list_userImage);
    }
}

