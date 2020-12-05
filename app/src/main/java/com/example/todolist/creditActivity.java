package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class creditActivity extends AppCompatActivity {

    public Button doneBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        doneBtn = findViewById(R.id.doneBtn);
        Done(this);
    }

    private void Done(final Context context) {
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainAct = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMainAct);
            }
        });
    }
}
