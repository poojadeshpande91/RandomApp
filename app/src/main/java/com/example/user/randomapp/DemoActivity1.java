package com.example.user.randomapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 28/9/17.
 */

public class DemoActivity1 extends AppCompatActivity {

    @BindView(R.id.et)
    EditText editText;
    @BindView(R.id.btn)
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_1);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().equals("") &&
                        !editText.getText().toString().equals(null)) {
                    Intent intent = new Intent(getApplicationContext(), DemoActivity2.class);
                    intent.putExtra("text", editText.getText().toString());
                    startActivityForResult(intent, 1);
//                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra("text");
                editText.setText(text);
            }
        }
    }
}
