package space.goryanoy.finalprojectquiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    MediaPlayer incorrect;
    MediaPlayer correct;
    MediaPlayer universesound;
    MediaPlayer dialogmusic;


    public int numLeft;//var for left card
    public int numRight;//var for right card
    Array array = new Array();
    Random random = new Random();
    public int count = 0; // counter of true answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.txt_leve2);
        incorrect = MediaPlayer.create(this, R.raw.incorrect);
        correct = MediaPlayer.create(this, R.raw.correct);
        universesound = MediaPlayer.create(this, R.raw.universeound);
        dialogmusic = MediaPlayer.create(this, R.raw.font1);
        dialogmusic.setLooping(true);




        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        // rounding corners of left card
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        // rounding corners of right card
        img_right.setClipToOutline(true);
        //path to left textview
        final TextView text_left = findViewById(R.id.text_left);
        //path to right textview
        final TextView text_right = findViewById(R.id.text_right);



        //open a game to full screen
        Window w =getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //dialog window before level 2
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//hiding heading
        dialog.setContentView(R.layout.previewdialog);//path to the dialog box layout
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent font of dialog window
        dialog.setCancelable(false);//can't close the window by system back button

        //set a picture into dialog window
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimgtwo);

        //set description
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.leveltwo);

        //dialog close button
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                universesound.start();

                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);finish();

                }catch (Exception e){
                    //empty
                }
                dialog.dismiss();//close the dialog window
                
            }
        });

        //continue button to level1
        Button btncotinue  = (Button)dialog.findViewById(R.id.btncontinue);
        btncotinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                universesound.start();
                dialog.dismiss();//closing dialog window


            }
        });


        dialog.show();


        //_____________________________________________________
        //dialog window after level 1
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//hiding heading
        dialogEnd.setContentView(R.layout.dialogend);//path to the dialog box layout
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent font of dialog window
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//can't close the window by system back button


        //interesting fact
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.leveltwoEnd);

        //dialog close button
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                universesound.start();

                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);finish();

                }catch (Exception e){
                    //empty
                }
                dialogEnd.dismiss();//close the dialog window
                dialogmusic.stop();

            }
        });

        //continue button to level3
        Button btncotinue2  = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncotinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                universesound.start();
                try {
                    Intent intent = new Intent(Level2.this, Level3.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e){
                    //empty
                }

                dialogEnd.dismiss();//closing dialog window
                dialogmusic.stop();

            }
        });


        //_____________________________________________________

        //set back button
        Button btn_back  = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                universesound.start();

                try {
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                    //empty
                }
            }
        });

        //an array for progression bar
        final int[] progress= {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };

        //set animation
        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);

        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images2[numLeft]);//get pic from an array
        text_left.setText(array.texts2[numLeft]);//get the text from an array

        numRight = random.nextInt(10);
        //if numbers are equal then change right number
        while(numLeft==numRight){
            numRight = random.nextInt(10);
        }

        img_right.setImageResource(array.images2[numRight]);//get pic from an array
        text_right.setText(array.texts2[numRight]);//get the text from an array

        //left button onTouch handler
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);//blocking right card
                    if(numLeft>numRight){
                        correct.start();
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        incorrect.start();
                        img_left.setImageResource(R.drawable.img_false);
                    }

                }else if(event.getAction()==MotionEvent.ACTION_UP){

                    if(numLeft>numRight){
                        if(count<20){
                            count = count+1;
                        }

                            for(int i=0; i<20; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }

                            // coloring to green
                            for(int i=0; i<count; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }

                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count= count-2;
                            }
                        }
                        for(int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        // coloring to green
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    if(count==20){
                        //exit from level
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if(level>2){
                            //empty
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",3);
                            editor.commit();
                        }

                        dialogEnd.show();
                        dialogmusic.start();
                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);//get pic from an array
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);//get the text from an array

                        numRight = random.nextInt(10);
                        //if numbers are equal then change right number
                        while(numLeft==numRight){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);//get pic from an array
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);//get the text from an array
                        img_right.setEnabled(true);//activating right card

                    }

                }
                return true;
            }
        });



        //right button onTouch handler
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);//blocking left card
                    if(numLeft<numRight){
                        correct.start();
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        incorrect.start();
                        img_right.setImageResource(R.drawable.img_false);
                    }

                }else if(event.getAction()==MotionEvent.ACTION_UP){

                    if(numLeft<numRight){
                        if(count<20){
                            count = count+1;
                        }

                        for(int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        // coloring to green
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count= count-2;
                            }
                        }
                        for(int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        // coloring to green
                        for(int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    if(count==20){
                        //exit from level
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if(level>2){
                            //empty
                        }else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",3);
                            editor.commit();
                        }

                        dialogEnd.show();
                        dialogmusic.start();
                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images2[numLeft]);//get pic from an array
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);//get the text from an array

                        numRight = random.nextInt(10);
                        //if numbers are equal then change right number
                        while(numLeft==numRight){
                            numRight = random.nextInt(10);
                        }

                        img_right.setImageResource(array.images2[numRight]);//get pic from an array
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);//get the text from an array
                        img_left.setEnabled(true);//activating left card

                    }

                }
                return true;
            }
        });


    }
    // set system back button
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);finish();
        }catch (Exception e){
            //empty
        }
    }
}