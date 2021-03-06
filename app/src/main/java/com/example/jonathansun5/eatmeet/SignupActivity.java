package com.example.jonathansun5.eatmeet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.BindView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

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

    @BindView(R.id.my_toolbar) android.support.v7.widget.Toolbar _mToolbar;
    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        Intent receivingIntent = getIntent();
        Bundle extras = receivingIntent.getExtras();

        if (extras != null) {
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

            _nameText.setText(name);
            _emailText.setText(email.replace(",", "."));
            _passwordText.setText(password);


            Log.e("edit is", String.valueOf(edit));

            if (edit == false) {
                setSupportActionBar(_mToolbar);
                getSupportActionBar().hide();
            } else {
                Log.e("SignupActivity EDITTT", "pls edit");
                setSupportActionBar(_mToolbar);
                _mToolbar.setTitle("Edit Name/Email/Password");
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fafafa")));
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().show();

//                _mToolbar2.setVisibility(View.VISIBLE);
                _signupButton.setVisibility(View.GONE);
                _loginLink.setVisibility(View.GONE);

                ImageView btn = (ImageView) findViewById(R.id.item_save_edit);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validate()) {
                            onSignupFailed();
                            return;
                        }
                        String name = _nameText.getText().toString();
                        String email = _emailText.getText().toString();
                        String password = _passwordText.getText().toString();

                        Context mContext = getBaseContext();
                        Intent intent = new Intent(mContext, ProfileActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("email", email);
                        intent.putExtra("password", password);
                        intent.putExtra("username", username);
                        intent.putExtra("phonenumber", phoneNumber);
                        intent.putExtra("lifestyle", lifestyle);
                        intent.putExtra("birthyear", birthYear);
                        intent.putExtra("partysize", partySize);
                        intent.putExtra("allergies", allergies);
                        intent.putExtra("numFriends", numFriends);
                        intent.putExtra("friends", friends);
                        intent.putExtra("edit", true);
                        intent.putExtra("editSourceLogin", editSourceLogin);
                        mContext.startActivity(intent);
                    }
                });
            }
        } else {
            setSupportActionBar(_mToolbar);
            getSupportActionBar().hide();
        }

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

        // TODO: Implement your own signup logic here.
        Context mContext = getBaseContext();
        Intent intent = new Intent(mContext, UserNameActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("password", password);
        intent.putExtra("username", username);
        intent.putExtra("phonenumber", phoneNumber);
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


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        Context mContext = getBaseContext();
        Intent intent = new Intent(mContext, SignupActivity.class);
        mContext.startActivity(intent);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
