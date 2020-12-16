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

public class RegisterActivity extends AppCompatActivity {

    //=================================================
    //  Constants
    //=================================================

    private final int USER_NAME_MAX_LENGTH = 30;

    //=================================================
    //  Members
    //=================================================


    //=================================================
    //  UI
    //=================================================

    EditText etUserName;
    RelativeLayout btnRegister;

    //=================================================
    //  Private Methods
    //=================================================

    private void initUI(){
        getViews();
        setViews();
        setListeners();
    }

    private void getViews() {
        etUserName = findViewById(R.id.etUserName);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void setViews() {

        if (mSharedPrefsManager.isAlreadyRegistered()) {
            goToRoomActivity();
            finish();
        }

        disableRegisterButton();
    }

    private void setListeners () {

        etUserName.setOnEditorActionListener(new RegisterActivity.DoneOnEditorActionListener());
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkForm()) {
                    enableRegisterButton();
                } else {
                    disableRegisterButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (checkForm()) {
                    enableRegisterButton();
                } else {
                    disableRegisterButton();
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSharedPrefsManager.setUser(new User(etUserName.getText().toString()));
                mSharedPrefsManager.setAlreadyRegistered(true);

                goToRoomActivity();

                etUserName.setText("");
            }
        });
    }

    private boolean checkForm () {
        if (etUserName.length() > USER_NAME_MAX_LENGTH || etUserName.length() < 2) return false;
        else {
            return true;
        }
    }

    private void disableRegisterButton() {
        btnRegister.animate().alpha(0.1f).setDuration(500).start();
        btnRegister.setEnabled(false);
    }

    private void enableRegisterButton() {
        btnRegister.animate().alpha(1.0f).setDuration(500).start();
        btnRegister.setEnabled(true);
    }

    private void goToRoomActivity() {

        Intent i = new Intent(this,RoomActivity.class);
        startActivity(i);
    }

    //=================================================
    //  Public Methods
    //=================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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