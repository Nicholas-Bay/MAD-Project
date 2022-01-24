package com.example.mad_project.fragments;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_project.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;


public class BuyerMeFragment extends Fragment {
    //declaration
    TextView FullName;
    TextView FullNameLabel;
    TextInputLayout ProfileName;
    TextInputLayout ProfileEmail;
    TextInputLayout ProfilePhone;
    TextInputLayout ProfileUser;
    public BuyerMeFragment() {
        super(R.layout.fragment_buyer_me);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_me, container, false);
        //get data from mainpg buyer activity
        String username=getArguments().getString("username");
        String password=getArguments().getString("password");
        String name=getArguments().getString("name");
        String email=getArguments().getString("email");
        String phoneNo=getArguments().getString("phoneNo");

        //hooks
        FullName = view.findViewById(R.id.Full_name);
        FullNameLabel = view.findViewById(R.id.Full_name_label);
        ProfileName = view.findViewById(R.id.profile_fullName);
        ProfileEmail = view.findViewById(R.id.profile_email);
        ProfilePhone = view.findViewById(R.id.profile_phone);
        ProfileUser = view.findViewById(R.id.profile_username);
        //show data
        showAllUserData(username,password,name,email,phoneNo);



        return view;
    }
    private void showAllUserData(String username,String pwd,String name,String email,String phoneNo){
        FullName.setText(name);
        FullNameLabel.setText(username);
        ProfileName.getEditText().setText(name);
        ProfileEmail.getEditText().setText(email);
        ProfilePhone.getEditText().setText(phoneNo);
        ProfileUser.getEditText().setText(username);
    }
}