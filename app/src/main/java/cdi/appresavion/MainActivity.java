package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        i = 5;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
