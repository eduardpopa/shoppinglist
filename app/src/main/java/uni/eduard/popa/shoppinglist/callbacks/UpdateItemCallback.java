package uni.eduard.popa.shoppinglist.callbacks;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import uni.eduard.popa.shoppinglist.R;
import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.activities.ListItemsAdapter;
import uni.eduard.popa.shoppinglist.tasks.UpdateItemTask;
import uni.eduard.popa.shoppinglist.activities.ListItemsActivity;

public class UpdateItemCallback implements LoaderManager.LoaderCallbacks<Pair<Integer, ItemModel>> {
    Context context;
    ListItemsAdapter adapter;
    public UpdateItemCallback(Context context, ListItemsAdapter adapter){

        this.context=context;
        this.adapter=adapter;
    }

    @NonNull
    @Override
    public Loader<Pair<Integer, ItemModel>> onCreateLoader(int id, @Nullable Bundle args) {
        if(args==null) throw  new NullPointerException("Cannot update null item");
        return new UpdateItemTask(context,  args.getParcelable(ListItemsActivity.KEY_ITEM), args.getInt(ListItemsActivity.KEY_POSITION));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Pair<Integer, ItemModel>> loader, Pair<Integer, ItemModel> data) {
        adapter.updateItem(data.second, data.first);
        Toast.makeText(context, R.string.task_item_updated, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Pair<Integer, ItemModel>> loader) {

    }
}
