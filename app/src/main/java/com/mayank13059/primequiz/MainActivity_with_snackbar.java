package com.mayank13059.primequiz;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Mayank Vachher (2013059) on 17/08/16.
 */
public class MainActivity_with_snackbar extends AppCompatActivity {

    private static TextView numberDisplayed;
    private CoordinatorLayout coordinatorLayout;
    private static Button yesButton, noButton, skipButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_snackbar);

        numberDisplayed = (TextView) findViewById(R.id.numberDisplayed);
        yesButton = (Button) findViewById(R.id.button_yes);
        noButton = (Button) findViewById(R.id.button_no);
        skipButton = (Button) findViewById(R.id.button_skip);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinatorLayout);

        genRandomNumberAndSet(numberDisplayed);

        if (yesButton != null) {
            yesButton.setOnClickListener(isPrimeListener);
        }
        if (noButton != null) {
            noButton.setOnClickListener(isPrimeListener);
        }
        if (skipButton != null) {
            skipButton.setOnClickListener(genNextNumberListener);
        }
    }

    private View.OnClickListener isPrimeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView numberDisplayed = (TextView) findViewById(R.id.numberDisplayed);
            Integer numberToCheck = null;
            if (numberDisplayed != null) {
                numberToCheck = Integer.parseInt(numberDisplayed.getText().toString());
            }

            if(numberToCheck == null) {
                return;
            }

            Boolean result = checkPrime(numberToCheck);
            String result_value = "The number "+numberToCheck+" is ";
            int result_color = 0;

            switch (v.getId()) {
                case R.id.button_yes:
                    if(result) {
                        result_color = ContextCompat.getColor(getApplicationContext(), R.color.colorSuccess);
                        result_value += "Prime. Correct!";
//                        Log.i("Primus", "Correct!");
                    }
                    else {
                        result_color = ContextCompat.getColor(getApplicationContext(), R.color.colorFailure);
//                        Log.i("Primus", "Incorrect!");
                        result_value += "not Prime. Incorrect!";
                    }
                    break;
                case R.id.button_no:
                    if(result) {
                        result_color = ContextCompat.getColor(getApplicationContext(), R.color.colorFailure);
//                        Log.i("Primus", "Incorrect!");
                        result_value += "Prime. Incorrect!";
                    }
                    else {
                        result_color = ContextCompat.getColor(getApplicationContext(), R.color.colorSuccess);
//                        Log.i("Primus", "Correct!");
                        result_value += "not Prime. Correct!";
                    }
                    break;
                default:
                    break;
            }
            Snackbar resultSnackbar = Snackbar.make(coordinatorLayout, result_value, Snackbar.LENGTH_SHORT);
            resultSnackbar.getView().setBackgroundColor(result_color);
            resultSnackbar.show();
        }
    };

    private View.OnClickListener genNextNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            genRandomNumberAndSet(numberDisplayed);
        }
    };

    private Boolean checkPrime(int num) {

        if(num <= 2 || num%2 == 0) {
            return Boolean.FALSE;
        }

        for(int i = 3; i*i <= num; i+=2) {
            if((num % i) == 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private void genRandomNumberAndSet(TextView numberDisplayed) {
        Random randomGenerator = new Random();
        int toSet = randomGenerator.nextInt(1000) + 1;
        numberDisplayed.setText(toSet+"");
    }
}
