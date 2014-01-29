package com.example.AndroidApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor on 29.01.14.
 */
public class ButtonState {
    //enum State{NONE, ZERO, CROSS};

    private Map<Integer,Integer> buttonState = new HashMap<Integer, Integer>();
    public int buttonId[] = new int[9];
    public ButtonState(int[] buttons)
    {
        buttonId = buttons.clone();
    }


    public int getButtonState(ImageButton v)
    {
        return buttonState.get(v.getId());
    }

    public int getButtonState(int id)
    {
        return buttonState.get(id);
    }
    public void setButtonState(int viewId, int state)
    {
        buttonState.put(viewId, state);
    }

    private int checkLine(int id1, int id2, int id3)
    {
        if(getButtonState(buttonId[id1]) == getButtonState(buttonId[id2]) && getButtonState(buttonId[id2]) == getButtonState(buttonId[id3]))
        {
            return getButtonState(buttonId[id1]);
        }
        return State.NONE;
    }

    public int whoWin()
    {
        int temporaryState;
        temporaryState = checkLine(0, 1, 2);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }

        temporaryState = checkLine(3, 4, 5);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }

        temporaryState = checkLine(6, 7, 8);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }

        temporaryState = checkLine(0, 3, 6);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }

        temporaryState = checkLine(1, 4, 7);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }

        temporaryState = checkLine(2, 5, 8);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }

        temporaryState = checkLine(2, 4, 6);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }

        temporaryState = checkLine(0, 4, 8);
        if(temporaryState != State.NONE)
        {
            return temporaryState;
        }
        else
        {
            return State.NONE;
        }
    }

}
