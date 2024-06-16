package uni.eduard.popa.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class EditItemActivity extends AppCompatActivity {
    public static final String INTENT_REPLY = "REPLY";
    public static final String STATE_ITEM_KEY = "STATE_ITEM_EDIT";

    ItemModel item;
    int position;
    EditText txtName;
    EditText txtDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        ItemData data = getIntent().getParcelableExtra(MainActivity.INTENT_EXTRA_ITEM);
        if(data!=null) {
           item = data.getItem();
           position = data.getPosition();
           txtName = findViewById(R.id.txtName);
           txtDescription = findViewById(R.id.txtDescription);
           txtName.setText(item.getName());
           txtDescription.setText(item.getDescription());
       }
    }
    public void onUpdate(View view) {
        if( TextUtils.isEmpty(txtName.getText())){
            txtName.setError( getResources().getText(R.string.error_name_is_required));
        }else {
            Intent intent = new Intent();
            item.setName(txtName.getText().toString());
            item.setDescription(txtDescription.getText().toString());
            ItemData data = new ItemData();
            data.setPosition(position);
            data.setItem(item);
            intent.putExtra(INTENT_REPLY, data);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(STATE_ITEM_KEY, (Parcelable) item);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ItemModel savedItem =  savedInstanceState.getParcelable(STATE_ITEM_KEY);
        if(savedItem!=null){
        item=savedItem;
            txtName.setText(item.getName());
            txtDescription.setText(item.getDescription());
        }
    }
}