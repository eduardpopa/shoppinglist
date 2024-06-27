package uni.eduard.popa.shoppinglist.callbacks;


import android.content.Context;

abstract public class EditSwipeCallback extends SwipeCallback {


    protected EditSwipeCallback(Context context, int direction, int icon, int backgroundColor) {
        super(context, direction, icon, backgroundColor);
    }
}

