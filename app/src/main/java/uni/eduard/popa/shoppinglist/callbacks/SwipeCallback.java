package uni.eduard.popa.shoppinglist.callbacks;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

abstract public class SwipeCallback extends ItemTouchHelper.Callback {

    Context context;
    private Paint paint;
    private ColorDrawable colorDrawable;
    private int backgroundColor;
    private Drawable drawable;
    private int intrinsicWidth;
    private int intrinsicHeight;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }


    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    private int direction;
    private int icon;

    SwipeCallback(Context context, int direction, int icon, int backgroundColor) {
        this.context = context;
        this.direction = direction;
        this.backgroundColor = backgroundColor;
        this.icon = icon;
        colorDrawable = new ColorDrawable();
        paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        drawable = ContextCompat.getDrawable(this.context, this.icon);
        intrinsicWidth = drawable.getIntrinsicWidth();
        intrinsicHeight = drawable.getIntrinsicHeight();


    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, direction);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int itemHeight = itemView.getHeight();

        boolean isCancelled = dX == 0 && !isCurrentlyActive;

        int iconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
        int iconMargin = (itemHeight - intrinsicHeight) / 2;
        int iconLeft = 0;
        int iconRight = 0;
        int iconBottom = iconTop + intrinsicHeight;
        int left = 0;
        int right = 0;
        int top = itemView.getTop();
        int bottom = itemView.getBottom();


        switch (direction) {
            case ItemTouchHelper.LEFT:
                left = itemView.getRight();
                right = itemView.getRight() + (int) dX;
                iconLeft = itemView.getRight() - iconMargin - intrinsicWidth;
                iconRight = itemView.getRight() - iconMargin;

                break;
            case ItemTouchHelper.RIGHT:
                left = (int) dX;
                right = 0;
                iconLeft = iconMargin;
                iconRight = iconMargin + intrinsicWidth;
                break;
        }
        if (isCancelled) {
            clearCanvas(c, (float) left, (float) top, (float) right, (float) bottom);
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            return;
        }
        Log.d("LEFT: ", String.valueOf(left - right));
//            Log.d("TOP: ", String.valueOf(top));
//            Log.d("RIGHT: ", String.valueOf(right));
//            Log.d("BOTTOM: ", String.valueOf(bottom));
        colorDrawable.setBounds(left, top, right, bottom);
        colorDrawable.setColor(backgroundColor);
        colorDrawable.draw(c);

        drawable.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        drawable.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


    }

    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
        c.drawRect(left, top, right, bottom, paint);

    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }
}

