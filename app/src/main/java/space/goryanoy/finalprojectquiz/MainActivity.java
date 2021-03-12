package space.goryanoy.finalprojectquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long backPressedTime;
    MediaPlayer buttonsound;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonsound = MediaPlayer.create(this, R.raw.universeound);

        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonsound.start();

                try {
                    Intent intent = new Intent(MainActivity.this,GameLevels.class);
                    startActivity(intent); finish();
                }catch (Exception e) {
                }
            }
        });

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    //system BackButton

    @Override
    public void onBackPressed() {


        if (backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(), "clic exit again",Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();

    }
}