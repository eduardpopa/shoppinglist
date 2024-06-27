package uni.eduard.popa.shoppinglist.persistance;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import uni.eduard.popa.shoppinglist.models.ItemModel;

@Database(entities = ItemModel.class, version = 1)
public abstract class ShoppingListDatabase extends RoomDatabase{

        private static ShoppingListDatabase INSTANCE;
        public abstract ItemDao itemModel();
    public static ShoppingListDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),ShoppingListDatabase.class,"shopping-list.db")
//                        .createFromAsset("database/shopping-list.db")
                    .build();
        }
        return INSTANCE;
    }
        public static void destroyInstance() {
            INSTANCE = null;
        }
    }

