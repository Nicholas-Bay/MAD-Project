package com.example.mad_project.fragments;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.R;
import com.example.mad_project.activities.ProductAddHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// Use the application default credentials
    public class SellerAddFragment extends Fragment{
//global parameters
    TextInputLayout productName;
    TextInputLayout productQuantity;
    EditText productDescription;
    Spinner spinner;
    //recyclerview stuff
    RecyclerView productAddRecycler;
    ProductAddAdapter adapter;
    ArrayList<ProductAddHelper>productAdd;
    //temp storing stuff
    ProductAddHelper addImageHelper=new ProductAddHelper();
    ProductAddHelper findImageposition=new ProductAddHelper();
    Holder tempHolder=new Holder();
    //firesotre stuff
    FirebaseFirestore db;
    //button for saving stuff
    Button button;
    //from activity to fragment
    String username,password,name,email,phoneNo;
    //imageview perm
    public static final int GET_FROM_GALLERY = 1;



    public SellerAddFragment() {
        super(R.layout.fragment_seller_add);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_add, container, false);
        //data from activity
        username=getArguments().getString("username");
        password=getArguments().getString("password");
        name=getArguments().getString("name");
        email=getArguments().getString("email");
        phoneNo=getArguments().getString("phoneNo");
        //text input settings
        productName=view.findViewById(R.id.product_name);
        productQuantity=view.findViewById(R.id.product_quantity);
        productDescription=view.findViewById(R.id.product_description);
        //spinner settings
        spinner=view.findViewById(R.id.product_category);
        ArrayAdapter<CharSequence> spinnerAdapter =ArrayAdapter.createFromResource(getActivity(),R.array.category,
                android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        //recycler view settings
        productAddRecycler=view.findViewById(R.id.product_addPhoto);
        productAddRecycler();
        //firestore
        db=FirebaseFirestore.getInstance();
        //button settings
        button=view.findViewById(R.id.product_add);
        button.setOnClickListener(listProduct);

        return view;
    }
    private void productAddRecycler(){
        //default
//        productAddRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //slide
//        productAddRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //side by side
//        StaggeredGridLayoutManager lm=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager lm=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        productAddRecycler.setLayoutManager(lm);
        productAdd = new ArrayList<>();
        productAdd.add(new ProductAddHelper(getResources().getDrawable(R.drawable.empty)));
        adapter = new ProductAddAdapter(productAdd);
        productAddRecycler.setItemAnimator(new DefaultItemAnimator());
        productAddRecycler.setAdapter(adapter);

    }
    public View.OnClickListener listProduct=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //get the infomation when button is clicked
            String strProduct=productName.getEditText().getText().toString().trim();
            String strQuantity=productQuantity.getEditText().getText().toString().trim();
            String category=spinner.getSelectedItem().toString();
            String description=productDescription.getText().toString().trim();
            //convert imageview to bitmap
            //saving the stuff
            Map<String,Object>something=new HashMap<>();
            something.put("product",strProduct);
            something.put("category",category);
            something.put("description",description);
            something.put("quantity",Integer.parseInt(strQuantity));
            db.collection("Seller").document(username).collection(category).document(strProduct)
                    .set(something)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    };

//this function obtains the image from the galllery
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(resultCode == Activity.RESULT_OK) {
            try {
                Uri selectedImage = data.getData();
                //selected image stored as bitmap here
                Bitmap tempBitamp = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                Drawable tempDrawable = new BitmapDrawable(tempBitamp);//convert bitmap to draweable and store as draweable
                //store the image in bitmap form and drawable to helper
                addImageHelper.setBitmap(tempBitamp);
                addImageHelper.setDrawable(tempDrawable);
                //set the image ont eh recycler view
                int tempInt= findImageposition.getPosition();
                ProductAddAdapter.ProductAddHolder tholder;
                    tholder= tempHolder.getHolder();
                    productAdd.get(tempInt).setDrawable(tempDrawable);
                    ProductAddHelper productAddHelper=productAdd.get(tempInt);
                    tholder.image.setImageDrawable(productAddHelper.getDrawable());
                if(tempInt==productAdd.size()-1){
                    productAdd.add(new ProductAddHelper(getResources().getDrawable(R.drawable.empty)));
                }
                Toast.makeText(getActivity(),"tempInt"+tempInt
                        +"\naddimage"+addImageHelper.getPosition()
                        +"\ntholder"+tholder.getAdapterPosition()
                        +"\nproductaddsize"+productAdd.size()
                        ,Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ProductAddAdapter extends RecyclerView.Adapter<ProductAddAdapter.ProductAddHolder>{
        ArrayList<ProductAddHelper> productAdd;
        public ProductAddAdapter(ArrayList<ProductAddHelper> productAdd){
            this.productAdd = productAdd;
        }

        @NonNull
        @Override
        public ProductAddHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_image,parent,false);
//         View view = View.inflate(parent.getContext(), R.drawable.empty,parent);
            ProductAddHolder productAddHolder = new ProductAddHolder(view);
            return productAddHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ProductAddAdapter.ProductAddHolder holder, int position) {
            //find out the pos
            String str=Integer.toString(position);
            ProductAddHelper productAddHelper = productAdd.get(position);
            holder.image.setImageDrawable(productAddHelper.getDrawable());
            //listner
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                //display
                Toast.makeText(view.getContext(),"Recyceview"+str, Toast.LENGTH_SHORT).show();
                //store holder and position of the photo on clicked
                tempHolder.setHolder(holder);
                findImageposition.setPosition(Integer.parseInt(str));
                //start the activity to get photo from gallery -> i.e photo gallery will pop up
                Intent intent=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,GET_FROM_GALLERY);
                }
            });
            //        holder.image.setImageResource(position);
        }
        @Override
        public int getItemCount() {
            return productAdd.size();
        }

        public class ProductAddHolder extends RecyclerView.ViewHolder{

            ImageView image;
            Drawable drawable;

            public ProductAddHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.empty);
                drawable=image.getDrawable();
            }
        }
    }
    public static class Holder{
       private ProductAddAdapter.ProductAddHolder holder;
       public void setHolder(ProductAddAdapter.ProductAddHolder holder){
           this.holder=holder;
       }
       private ProductAddAdapter.ProductAddHolder getHolder(){
           return holder;
       }
    }

}
