package com.example.AndroidApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MyActivity extends Activity {

    private int currentState;
    private ButtonState buttons;

    private void changeGlobalState()
    {
            if(currentState == State.CROSS)
            {
                    currentState = State.ZERO;
            }
            else
            {
                currentState = State.CROSS;
            }
    }


    private void clearField()
    {
        for(int i = 0; i < 9;i++) {
            buttons.setButtonState(buttons.buttonId[i], State.NONE);
            findViewById(buttons.buttonId[i]).setBackgroundColor(Color.GRAY);
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

        if(buttons.getButtonState(button) != State.NONE)
        {
            return;
        }

        if(currentState == State.CROSS)
        {
            button.setBackgroundResource(R.drawable.cross);
            buttons.setButtonState(v.getId(), State.CROSS);
        }
        else
        {
            button.setBackgroundResource(R.drawable.circle);
            buttons.setButtonState(v.getId(), State.ZERO);
        }
        changeGlobalState();

        int winner = buttons.whoWin();
        if(winner == State.CROSS)
        {
            showMessage("CROSS win");
            clearField();
            currentState = State.CROSS;
        }
        else if(winner == State.ZERO)
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
        int[] butt = new int[] {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        buttons = new ButtonState(butt);
        clearField();

    }


}
