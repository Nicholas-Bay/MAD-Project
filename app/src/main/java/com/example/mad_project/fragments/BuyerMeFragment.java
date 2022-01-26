package com.example.mad_project.fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mad_project.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BuyerMeFragment extends Fragment {
    //declaration
    TextView FullName;
    TextView displayUser;
    TextInputLayout ProfileName;
    TextInputLayout ProfileEmail;
    TextInputLayout ProfilePhone;
    TextInputLayout ProfilePassword;
    Button buyerUpdate;
    //firebase
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    //transferred data from previous activtiy
    String username,password,email,name,phoneNo;
    public BuyerMeFragment() {
        super(R.layout.fragment_buyer_me);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_me, container, false);
        //get data from mainPage buyer activity
        username=getArguments().getString("username");
        password=getArguments().getString("password");
        name=getArguments().getString("name");
        email=getArguments().getString("email");
        phoneNo=getArguments().getString("phoneNo");
        //hooks
        FullName = view.findViewById(R.id.Full_name);
        displayUser = view.findViewById(R.id.buyer_display_username);
        ProfileName = view.findViewById(R.id.profile_fullName);
        ProfileEmail = view.findViewById(R.id.profile_email);
        ProfilePhone = view.findViewById(R.id.profile_phone);
        ProfilePassword = view.findViewById(R.id.profile_password);
        buyerUpdate=view.findViewById(R.id.profile_update);
        buyerUpdate.setOnClickListener(onUpdate);
        //show data
        showAllUserData(username,password,name,email,phoneNo);
        return view;
    }

    private void showAllUserData(String username,String pwd,String name,String email,String phoneNo){
        FullName.setText(name);
        displayUser.setText(username);
        ProfileName.getEditText().setText(name);
        ProfileEmail.getEditText().setText(email);
        ProfilePhone.getEditText().setText(phoneNo);
        ProfilePassword.getEditText().setText(pwd);
    }
    private View.OnClickListener onUpdate=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("Buyer");
            // get all the values
            String new_name = ProfileName.getEditText().getText().toString();
            String new_password = ProfilePassword.getEditText().getText().toString();
            String new_email = ProfileEmail.getEditText().getText().toString();
            String new_phoneNo = ProfilePhone.getEditText().getText().toString();
//                UserHelperClass post =new UserHelperClass(new_name,new_username,new_email,new_phoneNo);
//                Map<String,Object>postValues=post.toMap();
//                Map<String,Object>childUpdates=new HashMap<>();
//                childUpdates.put("/"+username + "/", postValues);
//                //overwriting prevoious data
            reference.child(username).child("name").setValue(new_name);
            reference.child(username).child("email").setValue(new_email);
            reference.child(username).child("phoneNo").setValue(new_phoneNo);
            reference.child(username).child("password").setValue(new_password);//apparently child-note of buyer is username not buyerid
//                reference.updateChildren(childUpdates);
            Toast.makeText(getActivity(), "Update Successful", Toast.LENGTH_SHORT).show();
        }
    };
}