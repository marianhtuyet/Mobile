package com.example.finalproject.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.finalproject.R;
import com.example.finalproject.adapter.CategoryAdapter;
import com.example.finalproject.adapter.ProductAdapter;
import com.example.finalproject.model.Category;
import com.example.finalproject.model.GioHang;
import com.example.finalproject.model.Product;
import com.example.finalproject.ultil.CheckInternetCnn;
import com.example.finalproject.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<Category> categoryArray;
    CategoryAdapter categoryAdapter;
    ArrayList<Product> productArray;
    ProductAdapter productAdapter;

    int id = 0;
    String nameCategory = "";
    String imageCategory = "";

    int id_product =0;
    String productName ="";
    Integer productPrice = 0;
    Integer discount =0;
    String productImage = "";
    String description = "";
    int id_category = 0;

    public static ArrayList<GioHang> mangGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(CheckInternetCnn.haveNetworkConnection(getApplicationContext())){
            actionBar();
            actionViewFlipper();
            GetCategoryList();
            getNewProduct();
            CatchOnItemListView();
        }else {
            CheckInternetCnn.NotificationCnn(getApplicationContext(),"No Internet Connection");
        }

    }

    @Override//Tao menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    //bat su kien item menu


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuGioHang:
                Intent intent = new Intent(getApplicationContext(), com.example.finalproject.activity.GioHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case  0 :
                        if(CheckInternetCnn.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            CheckInternetCnn.NotificationCnn(getApplicationContext(),"No Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckInternetCnn.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,SoMiNamActivity.class);
                            intent.putExtra("idCategory",categoryArray.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckInternetCnn.NotificationCnn(getApplicationContext(),"No Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckInternetCnn.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,QuanNamActivity.class);
                            intent.putExtra("idCategory",categoryArray.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckInternetCnn.NotificationCnn(getApplicationContext(),"No Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckInternetCnn.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,GiayNam.class);
                            intent.putExtra("idCategory",categoryArray.get(position).getId());
                            startActivity(intent);
                        }else {
                            CheckInternetCnn.NotificationCnn(getApplicationContext(),"No Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckInternetCnn.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,LienHeActivity.class);
                            startActivity(intent);
                        }else {
                            CheckInternetCnn.NotificationCnn(getApplicationContext(),"No Internet");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void getNewProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());//ho tro doc JSON
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlNewProduct,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response != null){
                            Log.i("mysize","size: "+response.length());
                            for (int j=0;j<response.length();j++){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(j);
                                    id_product = jsonObject.getInt("id");
                                    productName = jsonObject.getString("name");
                                    productPrice = jsonObject.getInt("price");
                                    discount = jsonObject.getInt("discount");
                                    productImage = jsonObject.getString("image");
                                    description = jsonObject.getString("description");
                                    id_category = jsonObject.getInt("idCategory");

                                    productArray.add(new Product(id_product,productName,productPrice,discount,productImage,description,id_category));
                                    productAdapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckInternetCnn.NotificationCnn(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetCategoryList() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());//ho tro doc JSON
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlCategory,
                    new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            nameCategory = jsonObject.getString("name");
                            imageCategory = jsonObject.getString("image");

                            categoryArray.add(new Category(id,nameCategory,imageCategory));
                            categoryAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    categoryArray.add(new Category(0,"Liên hệ","https://www.freeiconspng.com/uploads/contact-icons-png-contact-me-png-health-us-icon-8.png"));
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckInternetCnn.NotificationCnn(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void actionViewFlipper() {
        ArrayList<String> advImgs = new ArrayList<>();
        advImgs.add("https://theme.hstatic.net/1000296531/1000427833/14/slideshow_1.jpg?v=334");
        advImgs.add("https://theme.hstatic.net/1000296531/1000427833/14/slideshow_4.jpg?v=334");
        advImgs.add("https://theme.hstatic.net/1000296531/1000427833/14/slideshow_3.jpg?v=334");

        for (int i=0;i<advImgs.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(advImgs.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_mainScreen);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewflipper);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        navigationView = (NavigationView)findViewById(R.id.navigationview);
        listView = (ListView) findViewById(R.id.listview_mainScreen);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        categoryArray = new ArrayList<>();
        categoryArray.add(0,new Category(0,"Trang chủ","https://www.freeiconspng.com/uploads/home-page-icon-21.png"));
        categoryAdapter = new CategoryAdapter(categoryArray,getApplicationContext());

        listView.setAdapter(categoryAdapter);

        productArray = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(),productArray);
        recyclerView.setHasFixedSize(true);//chia thanh 2 cot
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(productAdapter);

        if(mangGioHang!=null){

        }else{
            mangGioHang = new ArrayList<>();
        }
    }
}
