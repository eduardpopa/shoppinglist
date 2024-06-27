package uni.eduard.popa.shoppinglist.persistance;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

import uni.eduard.popa.shoppinglist.models.ItemModel;

@Dao
public interface ItemDao {

        @Query("SELECT * FROM itemmodel order by position asc")
        List<ItemModel> loadAll();

        @Insert
        long insert(ItemModel item);
        @Delete
        void delete(ItemModel item);
        @Update
        void update(ItemModel item);
    }

