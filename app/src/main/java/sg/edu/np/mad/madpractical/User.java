package sg.edu.np.mad.madpractical;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class User implements Parcelable {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User() {};

    public User(String n, String d, int i, boolean f) {
        this.name = n;
        this.description = d;
        this.id = i;
        this.followed = f;
    }

    static Random rd = new Random();

    protected User(Parcel in) {
        name = in.readString();
        description = in.readString();
        id = in.readInt();
        followed = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() { return id; }

    public boolean isFollowed() {
        return followed;
    }

    public void changeFollowStatus() {
        if (isFollowed()) {
            followed = false;
        }
        else {
            followed = true;
        }
    }

    public static ArrayList<User> createRdTestUserList(int numUser) {
        ArrayList<User> testUserList = new ArrayList<User>();
        for (int i = 0; i < numUser; i++) {
            String randomName = "Name" + rd.nextInt();
            String randomDesc = "Description " + rd.nextInt();
            int id = i;
            boolean randomFollowStatus = rd.nextBoolean();

            User randomUser = new User(randomName, randomDesc, id, randomFollowStatus);
            testUserList.add(randomUser);
        }

        return testUserList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(id);
        dest.writeByte((byte) (followed ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(description, user.description) &&
                id == user.id &&
                followed == user.followed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, followed);
    }
}
