package edu.temple.signupform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Patterns;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

public class FormActivity extends AppCompatActivity {
    //Pattern for a stronger password
    //ALT + ENTER to import
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    //private static final String regexStr = ^[+]?[0-9]{10,13}$;
    private EditText textInputName;
    private EditText textInputEmail;
    private EditText textInputPassword;
    private EditText textInputConfirmPassword;
    Button btn;

    //Create input layout for each input text
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInputName = findViewById(R.id.text_input_username);
        textInputEmail = findViewById(R.id.text_input_email);
        textInputPassword = findViewById(R.id.text_input_password);
        textInputConfirmPassword = findViewById(R.id.text_input_confirmPassword);
        btn = (Button) findViewById(R.id.btnDoTheMagic);
        //Declare them to the ID

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                if (!validateUsername() | !validateEmail() | !validatePassword()) {
                    Toast.makeText(getApplicationContext(), "All field has be to filled and correct.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String input = "Welcome " + textInputName.getText().toString() + ", to the SignUpForm App.";
                Toast.makeText(getApplicationContext(), input, Toast.LENGTH_LONG).show();
            }

        });


    }

    private boolean validateUsername() {
        String name = textInputName.getText().toString().trim();

        if (name.isEmpty()) {
            textInputName.setError("Name cannot not be empty");
            //setError Method shows when it doesn't fit its requirements on textView
            return false;
        } else {
            textInputName.setError(null);
            //setError(null) removes it if the condition is true
            return true;
        }
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field cannot not be empty");
            //setError Method shows when it doesn't fit its requirements on textView
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            //The condition to see if the email is valid
            textInputEmail.setError("This is not a valid email");
            return false;
        } else {
            textInputEmail.setError(null);
            //setError(null) removes it if the condition is true
            return true;
        }
    }


    private boolean validatePassword() {
        String password = textInputPassword.getText().toString().trim();
        if (password.isEmpty()) {
            textInputPassword.setError("Password cannot be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            textInputPassword.setError("Password is too weak.\nPlease at least 1 digit," +
                    " at least 1 lower case letter, " +
                    "at least 1 upper case letter," +
                    " at least 1 special character," +
                    " no white spaces, " +
                    "and at least 4 characters\n");
            return false;
        } else if (!validateConfirmPassword()) {
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }

    }

    private boolean validateConfirmPassword() {
        String password = textInputPassword.getText().toString().trim();
        String confirmPassword = textInputConfirmPassword.getText().toString().trim();
        if (confirmPassword.isEmpty()) {
            textInputConfirmPassword.setError("Can't be empty");
            return false;
        } else if (!(confirmPassword.equals(password))) {
            textInputConfirmPassword.setError("Confirm password must be the same");
            return false;
        } else {
            textInputConfirmPassword.setError(null);
            return true;
        }
    }
}

