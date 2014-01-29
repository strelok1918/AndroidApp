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

public class MyActivity extends Activity {
    enum State{NONE, ZERO, CROSS};
    private State currentState;
    private Map<Integer,State> buttonState = new HashMap<Integer, State>();
    private int buttonId[] = new int[9];
    private void changeGlobalState()
    {
            if(currentState.equals(State.CROSS))
            {
                    currentState = State.ZERO;
            }
            else
            {
                currentState = State.CROSS;
            }
    }

    private State getButtonState(ImageButton v)
    {
       return buttonState.get(v.getId());
    }

    private void setButtonState(int viewId, State val)
    {
        ImageButton button = (ImageButton)findViewById(viewId);
        buttonState.put(button.getId(), val);
    }

    private State checkLine(int id1, int id2, int id3)
    {
        ImageButton button1 = (ImageButton)findViewById(buttonId[id1]);
        ImageButton button2 = (ImageButton)findViewById(buttonId[id2]);
        ImageButton button3 = (ImageButton)findViewById(buttonId[id3]);
        if(getButtonState(button1).equals(getButtonState(button2)) && getButtonState(button2).equals(getButtonState(button3)))
        {
            return getButtonState(button1);
        }
        return State.NONE;
    }

    private State whoWin()
    {
        State temporaryState;
        temporaryState = checkLine(0, 1, 2);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }

        temporaryState = checkLine(3, 4, 5);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }

        temporaryState = checkLine(6, 7, 8);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }

        temporaryState = checkLine(0, 3, 6);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }

        temporaryState = checkLine(1, 4, 7);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }

        temporaryState = checkLine(2, 5, 8);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }

        temporaryState = checkLine(2, 4, 6);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }

        temporaryState = checkLine(0, 4, 8);
        if(!temporaryState.equals(State.NONE))
        {
            return temporaryState;
        }
        else
        {
            return State.NONE;
        }
    }

    private void clearField()
    {
        for(int i = 0; i < 9;i++) {
            setButtonState(buttonId[i], State.NONE);
            findViewById(buttonId[i]).setBackgroundColor(Color.GRAY);
        }
    }
    private View.OnClickListener CloseButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearField();
        }
    };

    private void showMessage(CharSequence str)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(str);
        builder.setTitle("Win");
        builder.setPositiveButton("Close", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void StepClick(View v)
    {
        ImageButton button = (ImageButton)v;

        if(!getButtonState(button).equals(State.NONE))
        {
            return;
        }

        if(currentState.equals(State.CROSS))
        {
            button.setBackgroundResource(R.drawable.cross);
            setButtonState(v.getId(), State.CROSS);
        }
        else
        {
            button.setBackgroundResource(R.drawable.circle);
            setButtonState(v.getId(), State.ZERO);
        }
        changeGlobalState();

        State winner = whoWin();
        if(winner.equals(State.CROSS))
        {
            showMessage("CROSS win");
            clearField();
            currentState = State.CROSS;
        }
        else if(winner.equals(State.ZERO))
        {
            showMessage("ZERO win");
            clearField();
            currentState = State.CROSS;
        }
    }
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        currentState = State.CROSS;
        buttonId[0] = R.id.button1;
        buttonId[1] = R.id.button2;
        buttonId[2] = R.id.button3;
        buttonId[3] = R.id.button4;
        buttonId[4] = R.id.button5;
        buttonId[5] = R.id.button6;
        buttonId[6] = R.id.button7;
        buttonId[7] = R.id.button8;
        buttonId[8] = R.id.button9;
        clearField();

    }


}
