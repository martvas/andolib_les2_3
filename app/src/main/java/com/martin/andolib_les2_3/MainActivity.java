package com.martin.andolib_les2_3;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    Button btnHello;
    Button btnBye;
    TextView textView;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHello = findViewById(R.id.hello_btn);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello();
            }
        });
        btnBye = findViewById(R.id.bye_btn);
        btnBye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bye();
            }
        });
        textView = findViewById(R.id.textView);

        RxBus.getInstance()
                .getEvents()
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if (o instanceof HelloEvent) textView.setText("Hello");
                        else if (o instanceof ByeEvent) textView.setText("Bye");
                        else textView.setText("Unknown");
                    }
                });
    }

    public void hello() {
        RxBus.getInstance().send(new HelloEvent());
    }

    public void bye() {
        RxBus.getInstance().send(new ByeEvent());
    }

}
