package com.shubham.digitrecognizer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private final String TAG=this.getClass().getSimpleName();
    private DigitRecognizer digitRecognizer;
    private static  final int PIXEL_WIDTH=28;

    TextView textDetected;
    Button buttonDetect;
    Button buttonClear;
    PaintView paintView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        digitRecognizer=new DigitRecognizer(this);


        textDetected=findViewById(R.id.buttonDetect);
        buttonClear=findViewById(R.id.buttonClear);
        buttonDetect=findViewById(R.id.buttonDetect);
        paintView=findViewById(R.id.paintView);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textDetected.setText("");
                paintView.clear();
            }
        });

        buttonDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap scaledBitmap=Bitmap.createScaledBitmap(paintView.getmBitmap(),PIXEL_WIDTH,PIXEL_WIDTH,false);
                int digit= digitRecognizer.classify(scaledBitmap);

                if(digit>=0)
                    textDetected.setText("Digit Detected: "+String.valueOf(digit));
                else
                    textDetected.setText("Digit not detected");

            }
        });



    }
}
