package com.example.keyboardtheme.softkeyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard.Key;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodSubtype;

public class KeyboardView extends android.inputmethodservice.KeyboardView {

    static final int KEYCODE_OPTIONS = -100;
    // TODO: Move this into android.inputmethodservice.Keyboard
    static final int KEYCODE_LANGUAGE_SWITCH = -101;
    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        
    }
    @Override
    protected boolean onLongPress(Key key) {
        if (key.codes[0] == android.inputmethodservice.Keyboard.KEYCODE_CANCEL) {
            getOnKeyboardActionListener().onKey(KEYCODE_OPTIONS, null);
            return true;
        } else {
            return super.onLongPress(key);
        }
    }
    
 
	void setSubtypeOnSpaceKey(final InputMethodSubtype subtype) {
        final Keyboard keyboard = (Keyboard)getKeyboard();
       // keyboard.setSpaceIcon(getResources().getDrawable(subtype.getIconResId()));
        invalidateAllKeys();
    }
}
