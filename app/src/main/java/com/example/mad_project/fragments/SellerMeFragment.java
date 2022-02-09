package com.example.mad_project.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_project.R;
import com.example.mad_project.activities.RegisterActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SellerMeFragment extends Fragment {
    //declaration
    TextView FullName;
    TextView displayUser;
    TextInputLayout ProfileName;
    TextInputLayout ProfileEmail;
    TextInputLayout ProfilePhone;
    TextInputLayout ProfilePassword;
    Button sellerUpdate;
    //firebase
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    //transferred data from previous activity
    String username,password,email,name,phoneNo;
    public SellerMeFragment() {
        super(R.layout.fragment_seller_me);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_me, container, false);
        //get data from sellerActivity
        username=getArguments().getString("username");
        password=getArguments().getString("password");
        name=getArguments().getString("name");
        email=getArguments().getString("email");
        phoneNo=getArguments().getString("phoneNo");
        //hooks
        FullName = view.findViewById(R.id.seller_Full_name);
        displayUser = view.findViewById(R.id.seller_display_username);
        ProfileName = view.findViewById(R.id.seller_profile_fullName);
        ProfileEmail = view.findViewById(R.id.seller_profile_email);
        ProfilePhone = view.findViewById(R.id.seller_profile_phone);
        ProfilePassword = view.findViewById(R.id.seller_profile_password);
        sellerUpdate=view.findViewById(R.id.seller_profile_update);
        sellerUpdate.setOnClickListener(onUpdate);
        //show data
        showAllUserData(username,password,name,email,phoneNo);
        return view;
    }

    private void showAllUserData(String username,String password,String name,String email,String phoneNo){
        FullName.setText(name);
        displayUser.setText(username);
        ProfileName.getEditText().setText(name);
        ProfileEmail.getEditText().setText(email);
        ProfilePhone.getEditText().setText(phoneNo);
        ProfilePassword.getEditText().setText(password);
    }

    private View.OnClickListener onUpdate=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Seller");
            // get all the values
            String new_name = ProfileName.getEditText().getText().toString();
            String new_password = ProfilePassword.getEditText().getText().toString();
            String new_email = ProfileEmail.getEditText().getText().toString();
            String new_phoneNo = ProfilePhone.getEditText().getText().toString();
            if(checkAll(new_name,new_phoneNo,new_email,new_password)) {

                reference.child(username).child("name").setValue(new_name);
                reference.child(username).child("email").setValue(new_email);
                reference.child(username).child("phoneNo").setValue(new_phoneNo);
                reference.child(username).child("password").setValue(new_password);//apparently child-note of seller is username not sellerId
//                reference.updateChildren(childUpdates);
                Toast.makeText(getActivity(), "Update Successful", Toast.LENGTH_SHORT).show();
            }

        }
    };

    public boolean checkAll(String name, String contact,
                            String email, String pwd) {
        String error = "";
        Pattern special_char = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        //error for name
        if (TextUtils.isEmpty(name)){error += "Empty Name\n";
            ProfileName.setError("Name cannot be empty");}
        //error for contact
        if (TextUtils.isEmpty(contact)){
            error += "Empty contact\n";
            ProfilePhone.setError("Empty Contact");
        }
        else if (!Patterns.PHONE.matcher(contact).matches()){
            error += "Invalid Contact\n";
            ProfilePhone.setError("Invalid Contact");

        }
        else if (contact.length()!=8){
            error += "Invalid SG Phone Number\n";
            ProfilePhone.setError("Invalid SG Phone Number");

        }
        //error for email
        if (TextUtils.isEmpty(email)){
            error += "Empty Email\n";
            ProfileEmail.setError("Empty Email");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            error += "Invalid Email\n";
            ProfileEmail.setError("Invalid Email");

        }
        //error for password
        if (TextUtils.isEmpty(pwd)){
            error += "Empty Password\n";
            ProfilePassword.setError("Empty Password");
        }
        else if (pwd.length() < 8){
            error += "Set a better Password. Minimum 8 in length\n";
            ProfilePassword.setError("Set a better Password. Minimum 8 in length");

        }
        else if (!special_char.matcher(pwd).find()){
            error += "Password must have at least 1 special character\n";
            ProfilePassword.setError("Password must have at least a special character");
        }

        if (error.equals("")){
            ProfileName.setError(null);
            ProfilePhone.setError(null);
            ProfileEmail.setError(null);
            ProfilePassword.setError(null);
            return true;//no error returns true
        }
        else {
            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
