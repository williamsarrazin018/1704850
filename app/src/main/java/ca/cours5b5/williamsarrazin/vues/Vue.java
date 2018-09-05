package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.williamsarrazin.R;

public abstract class Vue extends ConstraintLayout {
    public Vue(Context context) {
        super(context);
    }

    public Vue(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Vue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("Atelier04",  this.getClass().getSimpleName() + "::OnFinishInflate");
    }

}
