package com.example.finalproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.model.GioHang;
import com.example.finalproject.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbarChiTiet;
    ImageView imgChiTiet;
    TextView tvTen,tvGiaCu, tvGia, tvMoTa;
    Spinner spinner;
    Button btnDatMua;

    int id = 0;
    String tenChiTiet = "";
    int giaChiTiet = 0;
    int discount = 0;
    String anhChiTiet ="";
    String moTaChiTiet = "";
    int idCate =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        init();
        actionToolBar();
        getInformation();
        catchEventSpinner();
        eventButton();
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
    private void eventButton() {
        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mangGioHang.size()>0){//nếu giỏ hàng đã có sản phầm
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    Boolean exits = false;
                    for (int i=0; i<MainActivity.mangGioHang.size();i++){
                        if(MainActivity.mangGioHang.get(i).getIdsp()==id){
                            //cập nhật lại sl trong giỏ hàng
                            int slTrongMang = MainActivity.mangGioHang.get(i).getSoluong();
                            MainActivity.mangGioHang.get(i).setSoluong(slTrongMang+sl);
                            if(MainActivity.mangGioHang.get(i).getSoluong()>=10){
                                MainActivity.mangGioHang.get(i).setSoluong(10);
                            }
                            //Cap nhat lai gia trong gio hang
                            MainActivity.mangGioHang.get(i).setGiasp(giaChiTiet*MainActivity.mangGioHang.get(i).getSoluong());
                            exits = true;
                        }
                    }
                    if (exits == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        int giaMoi = soluong*giaChiTiet;
                        MainActivity.mangGioHang.add(new GioHang(id,tenChiTiet,giaMoi,anhChiTiet,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    int giaMoi = soluong*giaChiTiet;
                    MainActivity.mangGioHang.add(new GioHang(id,tenChiTiet,giaMoi,anhChiTiet,soluong));
                }
                Intent intent= new Intent(getApplicationContext(), com.example.finalproject.activity.GioHang.class);
                startActivity(intent);
            }
        });
    }

    private void catchEventSpinner() {
        Integer[] soLuong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soLuong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getInformation() {

        Product product = (Product) getIntent().getSerializableExtra("thongtinsanpham");
        id = product.getId();
        tenChiTiet = product.getName();
        if (product.getDiscount()>0){
            giaChiTiet = product.getPrice()- (product.getPrice()*product.getDiscount())/100;
        }else {
            giaChiTiet = product.getPrice();
        }

        discount = product.getDiscount();
        anhChiTiet = product.getImage();
        moTaChiTiet = product.getDescription();
        idCate = product.getId_Category();


        tvTen.setText(tenChiTiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        if (product.getDiscount()>0){
            int giaMoi = product.getPrice()- (product.getPrice()*product.getDiscount())/100;
            tvGiaCu.setPaintFlags(tvGiaCu.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvGiaCu.setText("Giá cũ: "+decimalFormat.format(product.getPrice())+"đ");
            tvGia.setText("Giá: "+decimalFormat.format(giaMoi)+"đ");
        }else {
            tvGiaCu.setVisibility(View.INVISIBLE);
            tvGia.setText("Giá: "+decimalFormat.format(product.getPrice())+"đ");
        }
        tvMoTa.setText(moTaChiTiet);
        Picasso.with(getApplicationContext()).load(anhChiTiet).into(imgChiTiet);

    }

    private void actionToolBar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        toolbarChiTiet = (Toolbar)findViewById(R.id.toolbar_ChiTietSP);
        imgChiTiet = (ImageView) findViewById(R.id.imageview_ChiTietSP);
        tvTen = (TextView) findViewById(R.id.textview_TenChiTietSP);
        tvGiaCu = (TextView) findViewById(R.id.textview_GiaCuChiTiet);
        tvGia = (TextView) findViewById(R.id.textview_GiaChiTietSP);
        //tvGia.setPaintFlags(tvGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvMoTa = (TextView) findViewById(R.id.textview_MoTaChiTietSQP);
        btnDatMua = (Button)findViewById(R.id.button_DatMua);
        spinner = (Spinner)findViewById(R.id.spinner);
    }
}