package space.goryanoy.finalprojectquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {
    MediaPlayer menusound;
    MediaPlayer fontmusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        //sound
        menusound = MediaPlayer.create(this, R.raw.universeound);
        fontmusic = MediaPlayer.create(this, R.raw.music);
        fontmusic.setLooping(true);
        fontmusic.setVolume(10,10);
        fontmusic.start();

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level",1);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menusound.start();

                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                }
            }
        });
        //button to level 1
        TextView txt_level1 = (TextView)findViewById(R.id.txt_level1);
        txt_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menusound.start();

                try {
                    if(level>=1) {
                        Intent intent = new Intent(GameLevels.this, Level1.class);
                        startActivity(intent);
                        finish();
                    }else {
                        //empty
                    }
                }catch (Exception e) {
                    //empty
                }

            }
        });

        //button to level 2
        TextView txt_level2 = (TextView)findViewById(R.id.txt_level2);
        txt_level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menusound.start();

                try {
                    if(level>=2) {
                        Intent intent = new Intent(GameLevels.this, Level2.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //empty
                    }
                }catch (Exception e) {
                    //empty
                }
            }
        });

        //button to level 3
        TextView txt_level3 = (TextView)findViewById(R.id.txt_level3);
        txt_level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menusound.start();

                try {
                    if(level>=3) {
                        Intent intent = new Intent(GameLevels.this, Level3.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //empty
                    }
                }catch (Exception e) {
                    //empty
                }
            }
        });

        final int[] x={
                R.id.txt_level1,
                R.id.txt_level2,
                R.id.txt_level3,
                R.id.txt_level4,
                R.id.txt_level5,
                R.id.txt_level6,
        };

        for(int i=1; i<level; i++){
            TextView tv = findViewById(x[i]);
            tv.setText(R.string.txt_leve1+(i));
        }
    }
    // overiding system back button
    @Override
    public void onBackPressed(){

        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);finish();
        }catch (Exception e){
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        fontmusic.stop();
        fontmusic.release();
    }

}