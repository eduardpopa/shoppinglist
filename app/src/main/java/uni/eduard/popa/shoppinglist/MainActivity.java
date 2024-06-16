package uni.eduard.popa.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements  RecyclerViewInterface {
    public static final String INTENT_EXTRA_ITEM = "ITEM";
    public static final int INTENT_ITEM_UPDATE_REQUEST = 1;
    public static final int INTENT_ITEM_ADD_REQUEST = 2;
    public static final String STATE_ITEMS_KEY = "STATE_ITEMS";
    LinearLayout layout;
    Button btnNewItem;
    RecyclerView listView;
    List<ItemModel> items;
    ItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<ItemModel>();
        for(int i=0;i<50;i++){
            ItemModel item = new ItemModel("Item name "+ i, "Item description "+ i);
            items.add(item);
        }
        adapter = new ItemAdapter(this, items, this);
        listView.setAdapter(adapter);
        btnNewItem = findViewById(R.id.btnNewItem);


        ItemTouchHelper deleteHelper = getDeleteItemTouchHelper();
        deleteHelper.attachToRecyclerView(listView);
        ItemTouchHelper editHelper = getEditItemTouchHelper();
        editHelper.attachToRecyclerView(listView);
    }

    @Override
    public void onItemEdit(int position) {
        Intent intent = new Intent(this, EditItemActivity.class);
        ItemData data = new ItemData();
        data.setPosition(position);
        data.setItem(items.get(position));
        intent.putExtra(INTENT_EXTRA_ITEM, data);
        startActivityForResult(intent, INTENT_ITEM_UPDATE_REQUEST);
    }

    @Override
    public void onItemSelect(int position) {
        items.get(position).setBought(! items.get(position).getBought());
        adapter.notifyItemChanged(position);
    }

    public void onNewItem(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, INTENT_ITEM_ADD_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode){
            case INTENT_ITEM_UPDATE_REQUEST:
                if (resultCode == RESULT_OK) {
                    ItemData data = intent.getParcelableExtra(EditItemActivity.INTENT_REPLY);
                    if (data != null) {
                        items.set(data.getPosition(), data.getItem());
                        adapter.notifyItemChanged(data.getPosition());

                    }

                }
                break;
            case INTENT_ITEM_ADD_REQUEST:
                if (resultCode == RESULT_OK) {
                    ItemData data = intent.getParcelableExtra(AddItemActivity.INTENT_REPLY);
                    if (data != null) {
                        items.add(0, data.getItem());
                        adapter.notifyItemInserted(0);
                        listView.scrollToPosition(0);
                    }

                }
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_ITEMS_KEY,
                (ArrayList<? extends Parcelable>) items);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        List<ItemModel> savedItems =  savedInstanceState.getParcelableArrayList(STATE_ITEMS_KEY);
        if(savedItems!=null){
            items=savedItems;
            adapter = new ItemAdapter(this, items, this);
            listView.setAdapter(adapter);
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
        DeleteSwipeCallback deleteSwipeCallback = new DeleteSwipeCallback(this, ItemTouchHelper.LEFT, R.drawable.baseline_delete_24, getResources().getColor( R.color.red)) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                ItemModel deletedItem =  items.get(position);
                adapter.removeItem(position);
                Snackbar snackbar = Snackbar.make(findViewById(R.id.main),getResources().getText(R.string.item_deleted), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getText(R.string.undo), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                adapter.restoreItem(deletedItem, position);
                            }
                        });

                snackbar.show();

            }
        };
        return new ItemTouchHelper(deleteSwipeCallback);
    }
}