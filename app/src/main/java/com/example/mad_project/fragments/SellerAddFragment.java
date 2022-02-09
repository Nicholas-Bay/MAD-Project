package com.example.mad_project.fragments;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    ArrayList<Uri> batchImageUpload;
    ArrayList<ImageReference> imageReference;
//firesotre stuff
    FirebaseFirestore db;
//firebase storage stuff
    FirebaseStorage storage;
    StorageReference storageReference;
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
        ArrayAdapter<CharSequence> spinnerAdapter =ArrayAdapter.createFromResource(getActivity()
                ,R.array.category, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        //recycler view settings
        productAddRecycler=view.findViewById(R.id.product_addPhoto);
        productAddRecycler();
        //firestore settings
        db=FirebaseFirestore.getInstance();
        //firestorage settings
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        batchImageUpload=new ArrayList<>();
        //button settings
        button=view.findViewById(R.id.product_add);
        button.setOnClickListener(listProduct);

        return view;
    }
//recycler method
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
// UploadImage method
    private void uploadImage(String strProduct) {
        // Defining storageReference stuff
        String strUid;
        StorageReference ref;
        Uri filePath;
        for(int i=0;i<batchImageUpload.size();i++){
            //generate a uuid
            strUid=UUID.randomUUID().toString();
            //set ref
            ref= storageReference.child("images/Seller").child(username).child(strProduct).child(strUid);
            imageReference.add(new ImageReference(ref.getPath()));
            filePath=batchImageUpload.get(i);
            if (filePath != null) {

                // Code for showing progressDialog while uploading
                ProgressDialog progressDialog
                        = new ProgressDialog(getActivity());
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                // adding listeners on upload
                // or failure of image
                ref.putFile(filePath)
                        .addOnSuccessListener(
                                new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onSuccess(
                                            UploadTask.TaskSnapshot taskSnapshot) {

                                        // Image uploaded successfully
                                        // Dismiss dialog
                                        progressDialog.dismiss();
                                        Toast
                                                .makeText(getActivity(),
                                                        "Image Uploaded!!",
                                                        Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                // Error, Image not uploaded
                                progressDialog.dismiss();
                                Toast
                                        .makeText(getActivity(),
                                                "Failed " + e.getMessage(),
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .addOnProgressListener(
                                new OnProgressListener<UploadTask.TaskSnapshot>() {

                                    // Progress Listener for loading
                                    // percentage on the dialog box
                                    @Override
                                    public void onProgress(
                                            UploadTask.TaskSnapshot taskSnapshot) {
                                        double progress
                                                = (100.0
                                                * taskSnapshot.getBytesTransferred()
                                                / taskSnapshot.getTotalByteCount());
                                        progressDialog.setMessage(
                                                "Uploaded "
                                                        + (int) progress + "%");
                                    }
                                });
            }
        }

    }
//upload into database
    public View.OnClickListener listProduct=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //get the infomation when button is clicked
            String strProduct=productName.getEditText().getText().toString().trim();
            String strQuantity=productQuantity.getEditText().getText().toString().trim();
            String category=spinner.getSelectedItem().toString();
            String description=productDescription.getText().toString().trim();
            //check if there is correct data keyed in
            if(strProduct==null ||strProduct.equals("")) productName
                    .setError("Empty Product Name is Not Allowed");
            else productName.setError(null);
            if(spinner.getSelectedItemPosition()==0) Toast.makeText(getActivity()
                    ,"Please Select a Category",Toast.LENGTH_SHORT).show();
            if (strQuantity==null || strQuantity.equals("")) productQuantity
                    .setError("Empty Product Quantity is not Allowed");
            else productQuantity.setError(null);
            //below is true if correct data is key in
            if(productName.getError()==null &&
                productQuantity.getError()==null &&
                spinner.getSelectedItemPosition()!=0){
            //getting the refernce of the image
            imageReference=new ArrayList<>();
            //uploading image into the FireBase Storage
            uploadImage(strProduct);
            //converting it to reference
            //saving the stuff
            Map<String,Object>something=new HashMap<>();
            something.put("product",strProduct);
            something.put("category",category);
            something.put("description",description);
            something.put("quantity",Integer.parseInt(strQuantity));
            something.put("username",username);
            //saving imageRefernce as string
            ArrayList<String> images=new ArrayList<>();
            for(int i=0;i<imageReference.size();i++){
                images.add(imageReference.get(i).getImageRef());
            }
            String imageArray[]=images.toArray(new String[images.size()]);
            something.put("image", Arrays.asList(imageArray));
            String uuid=UUID.randomUUID().toString();
            db.collection("Seller").document(""+uuid+strProduct+username)
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

            }  else Toast.makeText(getActivity(),"Please Complete the form",Toast.LENGTH_SHORT).show();
        }
    };
//this function obtains the image to phone from the gallery
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
                if(tempInt==productAdd.size()-1){//if the empty image is clicked,change away the empty image and inflate
                    batchImageUpload.add(selectedImage);
                    productAdd.add(new ProductAddHelper(getResources().getDrawable(R.drawable.empty)));
                }else{
                    batchImageUpload.set(tempInt,selectedImage);
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
//recyclerview class
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
            //listener
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
//a static class to obtain the adapter so can use in various function
    public static class Holder{
       private ProductAddAdapter.ProductAddHolder holder;
       public void setHolder(ProductAddAdapter.ProductAddHolder holder){
           this.holder=holder;
       }
       private ProductAddAdapter.ProductAddHolder getHolder(){
           return holder;
       }
    }
//another static class to obtain image reference
    public static class  ImageReference{
        private String imageRef;
    public ImageReference(String imageRef){
        this.imageRef=imageRef;
    }
    public String getImageRef() {
        return imageRef;
    }
    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }
    }
}
