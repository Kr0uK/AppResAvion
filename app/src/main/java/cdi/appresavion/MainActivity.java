package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dao.DAOBase;
import dao.ImportCSV;
import dao.AeroportDAO;
import dbClass.Aeroport;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DAOBase daoBase = new DAOBase(getApplicationContext());
                daoBase.getWDb();


            }
        });
    }
}
