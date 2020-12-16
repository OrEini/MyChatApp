package com.oreini.mychatapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oreini.mychatapp.R;
import com.oreini.mychatapp.adapters.MessageAdapter;
import com.oreini.mychatapp.models.Message;
import com.oreini.mychatapp.models.User;

import java.util.ArrayList;

import static com.oreini.mychatapp.utils.MyChatApp.mSharedPrefsManager;

public class ChatActivity extends AppCompatActivity {

    //=================================================
    //  Constants
    //=================================================


    //=================================================
    //  Members
    //=================================================

    private ArrayList<Message> messagesList = new ArrayList<Message>();
    private MessageAdapter mMessageAdapter;

    //=================================================
    //  UI
    //=================================================

    RecyclerView rvMessages;
    EditText etMessage;
    Button btnSend;

    //=================================================
    //  Private Methods
    //=================================================

    private void initUI(){
        getViews();
        setViews();
        setListeners();
    }

    private void getViews() {

        rvMessages = findViewById(R.id.rvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
    }

    private void setViews() {

        mMessageAdapter = new MessageAdapter(messagesList);
        rvMessages.setAdapter(mMessageAdapter);
        disableSendButton();

    }

    private void setListeners () {

        etMessage.setOnEditorActionListener(new ChatActivity.DoneOnEditorActionListener());
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (checkForm()) {
                    enableSendButton();
                } else {
                    disableSendButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (checkForm()) {
                    enableSendButton();
                } else {
                    disableSendButton();
                }
            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message messageToSend = new Message(mSharedPrefsManager.getUser(),etMessage.getText().toString());

                etMessage.setText("");

                messagesList.add(messageToSend);

                mMessageAdapter.notifyDataSetChanged();

                rvMessages.smoothScrollToPosition(messagesList.size()-1);

                receiveAutoResponse();
            }
        });
    }

    private void receiveAutoResponse() {

        btnSend.postDelayed(new Runnable() {
            @Override
            public void run() {

                Message messageToReceived = new Message(new User("abcd1234","Shay Eini"),"Hello World");

                messagesList.add(messageToReceived);

                mMessageAdapter.notifyDataSetChanged();

                rvMessages.smoothScrollToPosition(messagesList.size()-1);


            }
        },2000);
    }

    private boolean checkForm () {
        if (etMessage.length() <= 0) return false;
        else {
            return true;
        }
    }

    private void disableSendButton() {
        btnSend.animate().alpha(0.1f).setDuration(200).start();
        btnSend.setEnabled(false);
    }

    private void enableSendButton() {
        btnSend.animate().alpha(1.0f).setDuration(200).start();
        btnSend.setEnabled(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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