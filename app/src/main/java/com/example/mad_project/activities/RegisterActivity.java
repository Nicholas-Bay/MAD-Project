package com.example.mad_project.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mad_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_name;
    private EditText et_contact;
    private EditText et_email;
    private EditText et_password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        // initialise variables
        et_username = findViewById(R.id.register_username);
        et_name = findViewById(R.id.register_name);
        et_contact = findViewById(R.id.register_contact);
        et_email = findViewById(R.id.register_email);
        et_password = findViewById(R.id.register_password);
        Button button_register_buyer = findViewById(R.id.register_buyer);
        Button button_register_seller = findViewById(R.id.register_seller);
        // save Buyer data to firebase and update firmware
        button_register_buyer.setOnClickListener(v -> {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Buyer");
            // get all the values
            String name = et_name.getText().toString();
            String username = et_username.getText().toString();
            String email = et_email.getText().toString();
            String phoneNo = et_contact.getEditableText().toString();
            String password = et_password.getEditableText().toString();
            UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
            reference.child(phoneNo).setValue(helperClass);
        });
        // save Seller data to firebase and update firmware
        button_register_seller.setOnClickListener(v -> {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Seller");
            // get all the values
            String name = et_name.getText().toString();
            String username = et_username.getText().toString();
            String email = et_email.getText().toString();
            String phoneNo = et_contact.getEditableText().toString();
            String password = et_password.getEditableText().toString();
            UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
            reference.child(phoneNo).setValue(helperClass);
        });
    }

    public boolean checkAll(String username, String name, String contact,
                            String email, String pwd) {
        String error = "";
        Pattern special_char = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        //error for username
        if (TextUtils.isEmpty(username)) error += "Empty Username\n";
        if (TextUtils.isEmpty(name)) error += "Empty Name\n";
        //error for contact
        if (TextUtils.isEmpty(contact)) error += "Empty contact\n";
        else if (!Patterns.PHONE.matcher(contact).matches()) error += "Invalid Contact\n";
        else if (contact.length()!=8) error += "Invalid SG Phone Number\n";
        //error for email
        if (TextUtils.isEmpty(email)) error += "Empty Email\n";
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) error += "Invalid Email\n";
        //error for password
        if (TextUtils.isEmpty(pwd)) error += "Empty Password\n";
        else if (pwd.length() < 8) error += "Set a better Password. Minimum 8 in length\n";
        else if (!special_char.matcher(pwd).find()) error += "Password must have at least a special character\n";

        if (error.equals("")) return true;//no error returns true
        else {
            Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}