package com.fa.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class DynamicFragment extends AppCompatActivity {

    private Button button;
    private Button button2;
    private simplefragment simplefragment;
    private simplefragment2 simplefragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);

        button = findViewById(R.id.btn_Change);
        button2 = findViewById(R.id.btn_Change2);

        simplefragment = new simplefragment();
        simplefragment2 =new simplefragment2();

        //Fragment Manager
        FragmentManager fmanager = getSupportFragmentManager();

        //Buat object Fragment Transactiom
        FragmentTransaction ft = fmanager.beginTransaction();
        //Tamabahkan objek simple fragment (object) ke frame
        ft.add(R.id.frame_layout,simplefragment2);
        ft.hide(simplefragment2);
        ft.add(R.id.frame_layout, new simplefragment());
        //kemudian commit().
        ft.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ketika button go to another fragment di klik, akan pindah ke fragment lain
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //ft.add(R.id.frame_layout, new anotherfragment());
                if (simplefragment2.isAdded()){
                   ft.show(simplefragment2);
                   ft.remove(simplefragment);
                   Toast.makeText(getApplicationContext(), "Fragment sudah di tambahkan sebelumnya",Toast.LENGTH_SHORT).show();
                }
                else {
                   ft.replace(R.id.frame_layout, simplefragment2);
                }
                ft.addToBackStack(null);
                ft.commit();

                button2.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ketika button go to simple fragment di klik , akan pindah ke fragment lain
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //ft.add(R.id.frame_layout, new anotherfragment());
                ft.replace(R.id.frame_layout, new simplefragment());
                ft.commit();

                button2.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
            }
        });

    }
}
