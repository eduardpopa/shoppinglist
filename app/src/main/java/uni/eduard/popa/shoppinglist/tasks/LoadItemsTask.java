package uni.eduard.popa.shoppinglist.tasks;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.persistance.ShoppingListDatabase;

public class LoadItemsTask extends AsyncTaskLoader<List<ItemModel>> {
        public LoadItemsTask(Context context) {
                super(context);
        }

        @Nullable
        @Override
        public List<ItemModel> loadInBackground() {
                return ShoppingListDatabase.getDatabase(getContext()).itemModel().loadAll();
        }
}