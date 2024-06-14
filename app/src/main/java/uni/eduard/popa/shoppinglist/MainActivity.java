package uni.eduard.popa.shoppinglist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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


        DeleteSwipeCallback deleteSwipeCallback = new DeleteSwipeCallback(this, ItemTouchHelper.LEFT, R.drawable.baseline_delete_24, getResources().getColor( R.color.red)) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                ItemModel deletedItem =  items.get(position);
                adapter.removeItem(position);
                Snackbar snackbar = Snackbar.make(findViewById(R.id.main),"Item deleted", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                adapter.restoreItem(deletedItem, position);
                            }
                        });

                snackbar.show();

            }
        };
        ItemTouchHelper deleteHelper = new ItemTouchHelper(deleteSwipeCallback);
        deleteHelper.attachToRecyclerView(listView);
        EditSwipeCallback editSwipeCallback = new EditSwipeCallback(this, ItemTouchHelper.RIGHT, R.drawable.baseline_edit_24, getResources().getColor(R.color.blue)) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                onItemEdit(position);
            }
        };
        ItemTouchHelper editHelper = new ItemTouchHelper(editSwipeCallback);
        editHelper.attachToRecyclerView(listView);
    }

    @Override
    public void onItemEdit(int position) {
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("NAME", items.get(position).getName());
        startActivity(intent);
    }

    @Override
    public void onItemSelect(int position) {
        items.get(position).setBought(! items.get(position).getBought());
        adapter.notifyItemChanged(position);
    }

    public void onNewItem(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
}