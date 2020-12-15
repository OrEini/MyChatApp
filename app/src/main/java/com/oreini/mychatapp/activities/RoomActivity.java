package com.oreini.mychatapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oreini.mychatapp.R;
import com.oreini.mychatapp.models.User;

import static com.oreini.mychatapp.utils.MyChatApp.mSharedPrefsManager;

public class RoomActivity extends AppCompatActivity {

    //=================================================
    //  Constants
    //=================================================

    private final int ROOM_ID_MAX_LENGTH = 6;

    //=================================================
    //  Members
    //=================================================


    //=================================================
    //  UI
    //=================================================

    TextView tvWelcome;
    EditText etRoomID;
    RelativeLayout btnEnter;

    //=================================================
    //  Private Methods
    //=================================================

    private void initUI(){
        getViews();
        setViews();
        setListeners();
    }

    private void getViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        etRoomID = findViewById(R.id.etRoomID);
        btnEnter = findViewById(R.id.btnEnter);
    }

    private void setViews() {
        disableEnterButton();

        tvWelcome.setText("Hey " + mSharedPrefsManager.getUserName() + ",");
    }

    private void setListeners() {
        etRoomID.setOnEditorActionListener(new RoomActivity.DoneOnEditorActionListener());
        etRoomID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkForm()) {
                    enableEnterButton();
                } else {
                    disableEnterButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (checkForm()) {
                    enableEnterButton();
                } else {
                    disableEnterButton();
                }
            }
        });


        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToChatActivity();
            }
        });
    }

    private boolean checkForm () {
        if (etRoomID.length() > ROOM_ID_MAX_LENGTH || etRoomID.length() <= 0) return false;
        else {
            return true;
        }
    }

    private void disableEnterButton() {
        btnEnter.animate().alpha(0.1f).setDuration(500).start();
        btnEnter.setEnabled(false);
    }

    private void enableEnterButton() {
        btnEnter.animate().alpha(1.0f).setDuration(500).start();
        btnEnter.setEnabled(true);
    }

    private void goToChatActivity() {

        Intent i = new Intent(this,ChatActivity.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        initUI();
    }

    class DoneOnEditorActionListener implements EditText.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    }
}