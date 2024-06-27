package uni.eduard.popa.shoppinglist.callbacks;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.activities.ListItemsAdapter;
import uni.eduard.popa.shoppinglist.R;
import uni.eduard.popa.shoppinglist.activities.ListItemsActivity;
import uni.eduard.popa.shoppinglist.tasks.AddItemTask;

public class AddItemCallback implements LoaderManager.LoaderCallbacks<ItemModel> {
    Context context;
    ListItemsAdapter adapter;
    public AddItemCallback(Context context, ListItemsAdapter adapter){
        this.context=context;
        this.adapter = adapter;
    }

    @NonNull
    @Override
    public Loader<ItemModel> onCreateLoader(int id, @Nullable Bundle args) {
        if(args==null) throw new NullPointerException("Cannot add null item");
        return new AddItemTask(context, args.getParcelable(ListItemsActivity.KEY_ITEM));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ItemModel> loader, ItemModel data) {
        adapter.addItem(data);
        Toast.makeText(context, R.string.task_item_added, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<ItemModel> loader) {

    }
}
