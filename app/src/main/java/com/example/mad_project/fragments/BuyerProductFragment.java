package com.example.mad_project.fragments;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mad_project.R;
import com.example.mad_project.activities.Applewatchforms;
import com.example.mad_project.activities.Clothingforms;
import com.example.mad_project.activities.Iphone13forms;
import com.example.mad_project.activities.MainPageBuyerActivity;
import com.example.mad_project.activities.ProductAddHelper;
import com.example.mad_project.activities.Ps5forms;
import com.example.mad_project.activities.Samsungforms;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BuyerProductFragment extends Fragment {
//back button stuff
//    private FragmentManager fragmentManager;
//    private AnimatedBottomBar animatedBottomBar;
    ImageView backbutton;
    Button iphonedetails, watchdetails , clothingdetails , samsungdetails , ps5details;
//firebasestuff firestore
    FirebaseFirestore db;
//photo storage
    FirebaseStorage storage;
    StorageReference storageReference;
//Seller class
    ArrayList<ProductList> productList;
//recyclerview
    RecyclerView recyclerView;
    BuyerProductAdapter adapter;

    public BuyerProductFragment() {
        super(R.layout.fragment_buyer_product);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buyer_product, container, false);
        //hooks
        //animatedBottomBar=view.findViewById(R.id.animatedBuyerBottomBar);
        backbutton = view.findViewById(R.id.back_product);
        iphonedetails = view.findViewById(R.id.iphone_webpage);
        watchdetails = view.findViewById(R.id.watch_webpage);
        clothingdetails = view.findViewById(R.id.clothing_webpage);
        samsungdetails = view.findViewById(R.id.s21_webpage);
        ps5details = view.findViewById(R.id.ps5_webpage);
        //Firestore initialization
        db=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        //buyerproduct
        productList=new ArrayList<>();
        //retrive infomation from firestore
        db.collection("Seller").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>(){
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots){
                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d:list){
                                ProductList temp=null;
                                Log.d("buyerRead",d.getId()+"=>"+d.getData());
                                String category=d.get("category").toString();
                                String description=d.get("description").toString();
                                String product=d.get("product").toString();
                                String quantity=d.get("quantity").toString();
                                String username=d.get("username").toString();
                                ArrayList<String> image= (ArrayList<String>) d.get("image");
                                temp=new ProductList(username,category,product,Integer.parseInt(quantity),description,image);
                                productList.add(temp);

                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go back to home page
                Intent intent=new Intent(getActivity().getApplication(), MainPageBuyerActivity.class);
                startActivity(intent);
                //Fragment fragment=null;
                //fragment=new BuyerHomeFragment();
                //getActivity().getSupportFragmentManager().beginTransaction()
                //.replace(R.id.fragment_buyer_container,fragment,"findThisFragment")
                //.addToBackStack(null)
                //.commit();
                //animatedBottomBar.selectTabById(R.id.home_buyer,false);
            }
        });

        iphonedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity().getApplication(), Iphone13forms.class);
                startActivity(intent1);
            }
        });

        watchdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity().getApplication(), Applewatchforms.class);
                startActivity(intent2);
            }
        });

        clothingdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity().getApplication(), Clothingforms.class);
                startActivity(intent3);
            }
        });

        samsungdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getActivity().getApplication(), Samsungforms.class);
                startActivity(intent4);
            }
        });

        ps5details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getActivity().getApplication(), Ps5forms.class);
                startActivity(intent5);
            }
        });
        //
        recyclerView=view.findViewById(R.id.buyer_product_recycler);
        buyerProductRecycler();



        return view;
    }
    private void buyerProductRecycler(){
        //default
//        productAddRecycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //slide
//        productAddRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //side by side
//        StaggeredGridLayoutManager lm=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager lm=new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        adapter=new BuyerProductAdapter(productList);
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    public class BuyerProductAdapter extends RecyclerView.Adapter<BuyerProductAdapter.BuyerProductHolder>{
        private ArrayList<ProductList> productList;
        public BuyerProductAdapter(ArrayList<ProductList> productList){
            this.productList = productList;
        }

        @NonNull
        @Override
        public BuyerProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //inflate from this xml
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buyer_product_recycler,parent,false);
//            View view = View.inflate(parent.getContext(), R.drawable.empty,parent);
            BuyerProductHolder buyerProductHolder = new BuyerProductHolder(view);
            return buyerProductHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull BuyerProductHolder holder, int position) {
            //find out the pos
            String str=Integer.toString(position);
            ProductList tempList=productList.get(position);
            String tempProductName=tempList.getProduct();
            holder.productName.setText(tempProductName);

            ArrayList<String> tempImageRef=tempList.getImage();
            if (!tempImageRef.isEmpty()) {
            String tempImage1=tempImageRef.get(0);
            StorageReference imageStorage= storage.getReference(tempImage1);
            String fileName=imageStorage.getName();
            try{
                final File localFile= File.createTempFile(fileName,"jpg");
                imageStorage.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getActivity(),"Picture Loaded Success",Toast.LENGTH_SHORT).show();
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                holder.image.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Picture Loaded Failed",Toast.LENGTH_SHORT).show();

                    }
                });

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            }else{
                holder.image.setImageResource(R.drawable.empty);
            }
//            BuyerProductHolder buyerProductHolder = product.get(position);
//            holder.image.setImageDrawable(buyerProductHolder.getDrawable());
            //listener
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //display
                    Toast.makeText(view.getContext(),"Recyceview"+str, Toast.LENGTH_SHORT).show();
                    //store holder and position of the photo on clicked
//                    /tempHolder.setHolder(holder);
//                    findImageposition.setPosition(Integer.parseInt(str));
                    //start the activity to get photo from gallery -> i.e photo gallery will pop up
//                    Intent intent=new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                    startActivityForResult(intent,GET_FROM_GALLERY);
                }
            });
            //        holder.image.setImageResource(position);
        }
        @Override
        public int getItemCount() {
            return productList.size();
        }

        public class BuyerProductHolder extends RecyclerView.ViewHolder{

            ImageView image;//image
            Drawable drawable;//image
            TextView productName;//product name
            Button button;//details
            public BuyerProductHolder(View itemView) {
                super(itemView);
                productName=itemView.findViewById(R.id.product_buyer_productname);
                image = itemView.findViewById(R.id.buyer_product_image1);
                drawable=image.getDrawable();
                button=itemView.findViewById(R.id.product_buyer_button);
            }

            public Drawable getDrawable() {
                return drawable;
            }
        }
    }

    public class ProductList{
        private String username,category,description,product;
        private ArrayList<String>image;
        private int quantity;
        public ProductList(String username,String category
                ,String product,int quantity,String description,ArrayList<String> image){
            this.username=username;
            this.category=category;
            this.product=product;
            this.quantity=quantity;
            this.description=description;
            this.image=image;
        }

        public ArrayList<String> getImage() {
            return image;
        }

        public String getProduct() {
            return product;
        }
    }

}