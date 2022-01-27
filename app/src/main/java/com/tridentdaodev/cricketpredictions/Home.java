package com.tridentdaodev.cricketpredictions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;

public class Home extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_home);
//        Button logout = findViewById(R.id.logout);
//        logout.setOnClickListener(view -> {
//            SignOut();
//        });

     CardView whatsapp,call,policy,about;
     whatsapp = findViewById(R.id.view3);
     call = findViewById(R.id.view4);
     policy = findViewById(R.id.view);
     about = findViewById(R.id.view2);

      whatsapp.setOnClickListener(view -> {
          String contact = "+91 9067440789"; // use country code with your phone number
          String url = "https://api.whatsapp.com/send?phone=" + contact+"&text=Hello sir, I want Predication and tips from you.";
          Uri uri = Uri.parse(url); // missing 'http://' will cause crashed
          Intent intent = new Intent(Intent.ACTION_VIEW, uri);
          startActivity(intent);
      });


      call.setOnClickListener(view -> {
          Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9067440789"));
          startActivity(intent);
      });

      policy.setOnClickListener(view -> {
          dialogFun2();
      });


      about.setOnClickListener(view -> {
            dialogFun();
      });


        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
     if (navigationView != null) {
         navigationView.setNavigationItemSelectedListener(this);
     }
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



     ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);

     toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

     drawerLayout.addDrawerListener(toggle);
     toggle.syncState();

    }




    public void dialogFun(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width=WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;

        ((ImageButton) dialog.findViewById(R.id.close)).setOnClickListener(v -> {
            dialog.dismiss();
        });

        ((Button) dialog.findViewById(R.id.btn_call)).setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "8484940289"));
            startActivity(intent);
        });
        dialog.show();
        dialog.getWindow();
    }


    public void dialogFun2(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.privacy);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width=WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height=WindowManager.LayoutParams.WRAP_CONTENT;

        ((ImageButton) dialog.findViewById(R.id.close)).setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
        dialog.getWindow();

    }

    public void SignOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void clearAppData() {
        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId())
             {
                 case R.id.home:
                     drawerLayout.close();
                     break;
                 case R.id.profile:
                     Intent intent2 = new Intent(Home.this,Profile.class);
                     startActivity(intent2);
                     break;
                 case R.id.Logout:
                     SignOut();
                     break;
             }
                return false;
            }
}