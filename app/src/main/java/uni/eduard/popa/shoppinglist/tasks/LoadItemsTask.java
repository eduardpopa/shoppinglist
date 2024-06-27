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
                List<ItemModel> items = ShoppingListDatabase.getDatabase(getContext()).itemModel().loadAll();
                if (items.size() <= 0) {
                        ShoppingListDatabase.getDatabase(getContext()).itemModel().insert(new ItemModel("Cucumbers", "2kg BIO"));
                        ShoppingListDatabase.getDatabase(getContext()).itemModel().insert(new ItemModel("Mushrooms", "0.5kg Psilocybin"));
                        ShoppingListDatabase.getDatabase(getContext()).itemModel().insert(new ItemModel("Onions", "make JN cries"));
                        ShoppingListDatabase.getDatabase(getContext()).itemModel().insert(new ItemModel("Peppers", "3R 3Y 3G"));
                        ShoppingListDatabase.getDatabase(getContext()).itemModel().insert(new ItemModel("Potatoes", "from india"));
                        ShoppingListDatabase.getDatabase(getContext()).itemModel().insert(new ItemModel("Spinach", "green one"));
                        return ShoppingListDatabase.getDatabase(getContext()).itemModel().loadAll();
                }
                return items;
        }
}