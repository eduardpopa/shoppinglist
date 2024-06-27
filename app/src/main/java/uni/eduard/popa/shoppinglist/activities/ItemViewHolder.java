package uni.eduard.popa.shoppinglist.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import uni.eduard.popa.shoppinglist.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public View getRowView() {
        return rowView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getNameView() {
        return nameView;
    }

    public TextView getDescriptionView() {
        return descriptionView;
    }

    View rowView;
    ImageView imageView;
    TextView nameView;
    TextView descriptionView;

    public ItemViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        rowView = itemView;
        imageView = itemView.findViewById(R.id.imageView);
        nameView = itemView.findViewById(R.id.nameView);
        descriptionView = itemView.findViewById(R.id.descriptionView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemSelect(position);
                    }
                }
            }
        });
    }
}
