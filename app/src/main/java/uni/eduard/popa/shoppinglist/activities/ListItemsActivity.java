package uni.eduard.popa.shoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import uni.eduard.popa.shoppinglist.callbacks.AddItemCallback;
import uni.eduard.popa.shoppinglist.callbacks.DeleteItemCallback;
import uni.eduard.popa.shoppinglist.callbacks.DeleteSwipeCallback;
import uni.eduard.popa.shoppinglist.callbacks.EditSwipeCallback;
import uni.eduard.popa.shoppinglist.callbacks.ResetItemsCallback;
import uni.eduard.popa.shoppinglist.callbacks.SelectItemCallback;
import uni.eduard.popa.shoppinglist.callbacks.SwapItemCallback;
import uni.eduard.popa.shoppinglist.models.ItemModel;
import uni.eduard.popa.shoppinglist.callbacks.ItemMoveCallback;
import uni.eduard.popa.shoppinglist.callbacks.LoadItemsCallback;
import uni.eduard.popa.shoppinglist.R;
import uni.eduard.popa.shoppinglist.callbacks.UpdateItemCallback;

public class ListItemsActivity extends AppCompatActivity implements RecyclerViewInterface {
    public static final String KEY_ITEM = "KEY_ITEM";
    public static final String KEY_POSITION = "KEY_POSITION";
    public static final String KEY_FROM_POSITION = "KEY_FROM_POSITION";
    public static final String KEY_TO_POSITION = "KEY_TO_POSITION";

    Button btnNewItem;
    RecyclerView listView;

    ListItemsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        listView = findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListItemsAdapter(this, this);

        listView.setAdapter(adapter);
        btnNewItem = findViewById(R.id.btnNewItem);

        ItemTouchHelper deleteHelper = getDeleteItemTouchHelper();
        deleteHelper.attachToRecyclerView(listView);
        ItemTouchHelper editHelper = getEditItemTouchHelper();
        editHelper.attachToRecyclerView(listView);
        ItemTouchHelper touchHelper = getMoveItemTouchHelper();
        touchHelper.attachToRecyclerView(listView);

        LoaderManager loader = LoaderManager.getInstance(this);
        if (loader.getLoader(R.id.loader_list_items) == null) {
            loader.initLoader(R.id.loader_list_items, null, new LoadItemsCallback(this, adapter));
        }

        loader.getLoader(R.id.loader_list_items).forceLoad();
    }

    @Override
    public void onItemEdit(int position) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra(KEY_POSITION, position);
        intent.putExtra(KEY_ITEM, adapter.getData().get(position));
        startActivityForResult(intent, R.id.intent_update_request);
    }

    @Override
    public void onItemDelete(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        bundle.putParcelable(KEY_ITEM, adapter.getData().get(position));
        LoaderManager loader =  LoaderManager.getInstance(this);
        if (loader.getLoader(R.id.loader_delete_item) == null) {
            loader.initLoader(R.id.loader_delete_item, bundle, new DeleteItemCallback(this, adapter));
        }else{
            loader.restartLoader(R.id.loader_delete_item, bundle, new DeleteItemCallback(this, adapter)).forceLoad();
        }
        loader.getLoader(R.id.loader_delete_item).forceLoad();
    }

    @Override
    public void onItemSelect(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        bundle.putParcelable(KEY_ITEM, adapter.getData().get(position));

        LoaderManager loader =  LoaderManager.getInstance(this);
        if (loader.getLoader(R.id.loader_select_item) == null) {
            loader.initLoader(R.id.loader_select_item, bundle, new SelectItemCallback(this, adapter));
        }else{
            loader.restartLoader(R.id.loader_select_item, bundle, new SelectItemCallback(this, adapter));
        }
        loader.getLoader(R.id.loader_select_item).forceLoad();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FROM_POSITION, fromPosition);
        bundle.putInt(KEY_TO_POSITION, toPosition);

        LoaderManager loader =  LoaderManager.getInstance(this);
        if (loader.getLoader(R.id.loader_swap_items) == null) {
            loader.initLoader(R.id.loader_swap_items, bundle, new SwapItemCallback(this, adapter));
        }else{
            loader.restartLoader(R.id.loader_swap_items, bundle, new SwapItemCallback(this, adapter));
        }
        loader.getLoader(R.id.loader_swap_items).forceLoad();
    }

    @Override
    public void onRowSelected(ItemViewHolder viewHolder) {
        viewHolder.getRowView().setBackgroundColor(getResources().getColor(R.color.grey));
    }

    @Override
    public void onRowClear(ItemViewHolder viewHolder) {
        viewHolder.getRowView().setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void onNewItem(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, R.id.intent_add_request);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == R.id.intent_update_request) {
            if (resultCode == RESULT_OK) {
                int position = intent.getIntExtra(KEY_POSITION, -1);
                ItemModel item = intent.getParcelableExtra(KEY_ITEM);
                if (item != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(KEY_POSITION, position);
                    bundle.putParcelable(KEY_ITEM, item);
                    LoaderManager loader = LoaderManager.getInstance(this);
                    if (loader.getLoader(R.id.loader_update_item) == null) {
                        loader.initLoader(R.id.loader_update_item, bundle, new UpdateItemCallback(this, adapter));
                    } else {
                        loader.restartLoader(R.id.loader_update_item, bundle, new UpdateItemCallback(this, adapter));
                    }
                    loader.getLoader(R.id.loader_update_item).forceLoad();
                }
            }
        }
        if (requestCode == R.id.intent_add_request) {
            if (resultCode == RESULT_OK) {
                ItemModel item = intent.getParcelableExtra(KEY_ITEM);
                if (item != null) {
                    item.setPosition(adapter.getData().size());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(KEY_ITEM, item);
                    LoaderManager loader = LoaderManager.getInstance(this);
                    if (loader.getLoader(R.id.loader_add_item) == null) {
                        loader.initLoader(R.id.loader_add_item, bundle, new AddItemCallback(this, adapter));
                    } else {
                        loader.restartLoader(R.id.loader_add_item, bundle, new AddItemCallback(this, adapter));
                    }
                    loader.getLoader(R.id.loader_add_item).forceLoad();
                    listView.scrollToPosition(adapter.getData().size() - 1);
                }
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_menu_reset) {
            Bundle bundle = new Bundle();
            LoaderManager loader = LoaderManager.getInstance(this);
            if (loader.getLoader(R.id.loader_reset_items) == null) {
                loader.initLoader(R.id.loader_reset_items, bundle, new ResetItemsCallback(this, adapter));
            } else {
                loader.restartLoader(R.id.loader_reset_items, bundle, new ResetItemsCallback(this, adapter));
            }
            loader.getLoader(R.id.loader_reset_items).forceLoad();

            return true;
        }else if(item.getItemId() == R.id.action_menu_help){
            startActivity(new Intent(this, HelpActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @NonNull
    private ItemTouchHelper getEditItemTouchHelper() {
        return new ItemTouchHelper(new EditSwipeCallback(this, ItemTouchHelper.RIGHT, R.drawable.baseline_edit_24, getResources().getColor(R.color.blue)) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                onItemEdit(position);
            }
        });
    }

    @NonNull
    private ItemTouchHelper getDeleteItemTouchHelper() {
        DeleteSwipeCallback deleteSwipeCallback = new DeleteSwipeCallback(this, ItemTouchHelper.LEFT, R.drawable.baseline_delete_24, getResources().getColor(R.color.red)) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                onItemDelete(position);


            }
        };
        return new ItemTouchHelper(deleteSwipeCallback);
    }

    private ItemTouchHelper getMoveItemTouchHelper() {
        ;
        return new ItemTouchHelper(new ItemMoveCallback(this));
    }

}