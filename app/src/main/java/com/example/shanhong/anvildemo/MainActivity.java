package com.example.shanhong.anvildemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import trikita.anvil.Anvil;
import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.*;

public class MainActivity extends AppCompatActivity {

    public int ticktock = 0;

    public int numClicks = 0;

    private Handler customHandler = new Handler();

    private long startTime = 0L;

    long timeInMilliseconds = 0L;

    long timeSwapBuff = 0L;

    long updatedTime = 0L;

    private Runnable updateTimerThread = new Runnable() {

        @Override
        public void run() {
            ticktock++;
            Anvil.render();

            customHandler.postDelayed(this, 0);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RenderableView(this) {
            @Override
            public void view() {
                linearLayout(() -> {
                    size(MATCH, MATCH);
                    padding(dip(8));
                    orientation(LinearLayout.VERTICAL);

                    textView(() -> {
                        size(MATCH, WRAP);
                        text("Tick-tock: " + ticktock);
                    });

                    button(() -> {
                        size(MATCH, WRAP);
                        text("Start Tick");
                        onClick(v -> {
                            customHandler.postDelayed(updateTimerThread, 0);
                        });
                    });

                    button(() -> {
                        size(MATCH, WRAP);
                        text("Stop Tick");
                        onClick(v -> {
                            customHandler.removeCallbacks(updateTimerThread);
                        });
                    });

                    button(() -> {
                        size(MATCH, WRAP);
                        text("Close");
                        // Finish current activity when the button is clicked
                        onClick(v -> finish());
                    });

                    textView(() -> {
                        text("Clicks: " + numClicks);
                    });
                    button(() -> {
                        text("Click me");
                        onClick(v -> {
                            numClicks++; // text view will be updated automatically
                        });
                    });
                });
            }
        });
    }
}
