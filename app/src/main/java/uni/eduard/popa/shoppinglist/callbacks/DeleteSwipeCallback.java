package uni.eduard.popa.shoppinglist.callbacks;


    import android.content.Context;

abstract public class DeleteSwipeCallback extends SwipeCallback {

    protected DeleteSwipeCallback(Context context, int direction, int icon, int backgroundColor) {
        super(context, direction, icon, backgroundColor);
    }
}

