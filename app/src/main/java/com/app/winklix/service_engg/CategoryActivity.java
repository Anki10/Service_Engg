package com.app.winklix.service_engg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private TextView admistrative_type,user_type;

    private static int SPLASH_TIME_OUT = 3000;
    Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        context = CategoryActivity.this;

        user_type = (TextView)findViewById(R.id.user_type);
        admistrative_type = (TextView)findViewById(R.id.admistrative_type);

        user_type.setOnClickListener(this);
        admistrative_type.setOnClickListener(this);

        StartAnimations();
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        FrameLayout mainLinearLayout=(FrameLayout) findViewById(R.id.frame);
        mainLinearLayout.clearAnimation();
        mainLinearLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    //Intent intent = new Intent(CategoryActivity.this,WelcomeActivity.class);
                   // intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    //startActivity(intent);
                   // Splash_Screen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                   // Splash_Screen.this.finish();
                }

            }
        };
        splashTread.start();

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        SharedPreferences sp=getApplicationContext().getSharedPreferences("userlogin", Context.MODE_PRIVATE);
        System.out.println("sp_sp"+sp);
        String  userlogin = sp.getString("user",null);

        switch (id){
            case R.id.user_type:

                if(userlogin != null&& userlogin.equalsIgnoreCase("service") )
                {
                    startActivity(new Intent(context,MainActivity.class));
                }
                else startActivity(new Intent(context,service_LoginActivity.class));
                break;
            case R.id.admistrative_type:

                if(userlogin != null&& userlogin.equalsIgnoreCase("admin") )
                {
                    startActivity(new Intent(context,Admistrative_MainActivity.class));
                }
                else startActivity(new Intent(context,Admistrative_loginActivity.class));

                break;
        }
    }
}
