package com.example.jonathansun5.eatmeet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class UserNameActivity extends AppCompatActivity {

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.my_toolbar2) android.support.v7.widget.Toolbar _mToolbar2;
    @BindView(R.id.usernameInput) EditText _usernameText;
    @BindView(R.id.phoneEditText) EditText _phoneNumberText;
    @BindView(R.id.usernameContinue) Button _usernameContinueButton;

    private String name;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String lifestyle;
    private String birthYear;
    private String partySize;
    private ArrayList<String> allergies;
    private String numFriends;
    private ArrayList<String> friends;
    private Boolean edit = false;
    private Boolean editSourceLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        ButterKnife.bind(this);

        //Get Intent information from sign-up
        Intent recevingIntent = getIntent();
        Bundle extras = recevingIntent.getExtras();
        name = (String) extras.get("name");
        email = (String) extras.get("email");
        password = (String) extras.get("password");
        username = (String) extras.get("username");
        phoneNumber = (String) extras.get("phonenumber");
        lifestyle = (String) extras.get("lifestyle");
        birthYear = (String) extras.get("birthyear");
        partySize = (String) extras.get("partysize");
        allergies = (ArrayList<String>) extras.get("allergies");
        numFriends = (String) extras.get("numFriends");
        friends = (ArrayList<String>) extras.get("friends");
        edit = (Boolean) extras.get("edit");
        editSourceLogin = (Boolean) extras.get("editSourceLogin");

        if (username != null) {
            _usernameText.setText(username);
        }

        if (phoneNumber != null) {
            _phoneNumberText.setText(phoneNumber);
        }

        _usernameContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = _usernameText.getText().toString();
                phoneNumber = _phoneNumberText.getText().toString();
                Boolean usernameOK = false;
                Boolean phonenumberOK = false;
                if (username.isEmpty() || username.length() < 3) {
                    usernameOK = false;
                    _usernameText.setError("at least 3 characters");
                } else {
                    usernameOK = true;
                }

                if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
                    phonenumberOK = false;
                    _phoneNumberText.setError("not a valid phone number");
                } else {
                    phonenumberOK = true;
                }

                if (usernameOK && phonenumberOK) {
                    _usernameText.setError(null);
                    _phoneNumberText.setError(null);
                    Context mContext = getBaseContext();
                    Intent intent = new Intent(mContext, PersonalActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("phonenumber", phoneNumber);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("lifestyle", lifestyle);
                    intent.putExtra("birthyear", birthYear);
                    intent.putExtra("partysize", partySize);
                    intent.putExtra("allergies", allergies);
                    intent.putExtra("numFriends", numFriends);
                    intent.putExtra("friends", friends);
                    intent.putExtra("edit", false);
                    intent.putExtra("editSourceLogin", editSourceLogin);
                    mContext.startActivity(intent);
                }
            }
        });

        if (!edit) {
            setSupportActionBar(_mToolbar2);
            getSupportActionBar().hide();
            setSupportActionBar(_mToolbar);
            _mToolbar.setTitle("Create a Username");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            _mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context mContext = getBaseContext();
                    Intent intent = new Intent(mContext, SignupActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("phonenumber", phoneNumber);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("birthyear", birthYear);
                    intent.putExtra("lifestyle", lifestyle);
                    intent.putExtra("partysize", partySize);
                    intent.putExtra("allergies", allergies);
                    intent.putExtra("numFriends", numFriends);
                    intent.putExtra("friends", friends);
                    intent.putExtra("edit", false);
                    intent.putExtra("editSourceLogin", editSourceLogin);
                    mContext.startActivity(intent);
                }
            });
        } else {
            Log.e("Usernameactivity EDITTT", "pls edit");
            setSupportActionBar(_mToolbar);
            getSupportActionBar().hide();
            setSupportActionBar(_mToolbar2);
            _mToolbar2.setTitle("Edit Username/Phone #");
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);

            _usernameContinueButton.setVisibility(View.GONE);

            ImageView btn = (ImageView) findViewById(R.id.item_save_edit);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username = _usernameText.getText().toString();
                    phoneNumber = _phoneNumberText.getText().toString();
                    Boolean usernameOK = false;
                    Boolean phonenumberOK = false;
                    if (username.isEmpty() || username.length() < 3) {
                        usernameOK = false;
                        _usernameText.setError("at least 3 characters");
                    } else {
                        usernameOK = true;
                    }

                    if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
                        phonenumberOK = false;
                        _phoneNumberText.setError("not a valid phone number");
                    } else {
                        phonenumberOK = true;
                    }

                    if (usernameOK && phonenumberOK) {
                        _usernameText.setError(null);
                        _phoneNumberText.setError(null);
                        Context mContext = getBaseContext();
                        Intent intent = new Intent(mContext, ProfileActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("phonenumber", phoneNumber);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        intent.putExtra("lifestyle", lifestyle);
                        intent.putExtra("birthyear", birthYear);
                        intent.putExtra("partysize", partySize);
                        intent.putExtra("allergies", allergies);
                        intent.putExtra("numFriends", numFriends);
                        intent.putExtra("friends", friends);
                        intent.putExtra("edit", false);
                        intent.putExtra("editSourceLogin", true);
                        mContext.startActivity(intent);
                    }
                }
            });
        }
    }
}
