package com.bw.ymy.day18.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.ymy.day18.CustomFlowLayout;
import com.bw.ymy.day18.R;

public class MainActivity extends AppCompatActivity {
     ImageView sousu;
     EditText et;
    CustomFlowLayout customFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.etname);
        customFlowLayout=findViewById(R.id.customFlowLayout);

                //点击跳进购物车
        findViewById(R.id.sousu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=new TextView(MainActivity.this);
                tv.setText(et.getText());
                tv.setTextColor(Color.RED);
                customFlowLayout.addView(tv);
                //点击添加的文字  点击吐司
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,et.getText().toString(),Toast.LENGTH_LONG).show();
                    }
                });

                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
