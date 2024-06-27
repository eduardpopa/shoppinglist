package uni.eduard.popa.shoppinglist.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import uni.eduard.popa.shoppinglist.R;


@Entity
public class ItemModel implements Parcelable {


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private long id;
    private int position;
    public int getPosition() {
        return position;
    }

    public void setPosition(int order) {
        this.position = order;
    }

    protected ItemModel(Parcel in) {
        id = in.readLong();
        position = in.readInt();
        checked = in.readByte() != 0;
        image = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private boolean checked;
    public ItemModel( String name, String description) {
        this.name = name;
        this.description = description;
        this.checked =false;
        this.position =0;
    }

    public int getImage() {
        return checked ? R.drawable.grocery4 : R.drawable.grocery1;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(position);
        dest.writeByte((byte) (checked ? 1 : 0));
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(description);
    }
}
