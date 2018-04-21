package com.booboo.feedbackform;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPhone, etFdbck;
    Button btnSubmit;
    Spinner spnSub;
    RadioGroup rgGender;
    RatingBar rbReview;
    FloatingActionButton fabSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etFdbck = (EditText) findViewById(R.id.etFdbck);
        spnSub = (Spinner) findViewById(R.id.spnSub);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        rbReview = (RatingBar) findViewById(R.id.rbReview);
        fabSend = (FloatingActionButton) findViewById(R.id.fabSend);

        final String[] subject = {"Android", "Java", "Oracle SQL"};
        ArrayAdapter<String> subAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subject);
        spnSub.setAdapter(subAdapter);

        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if (name.length() == 0) {
                    Snackbar.make(v, "Enter Name Please", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                String phone = etPhone.getText().toString();
                if (phone.length() != 10) {
                    Snackbar.make(v, "Enter Proper Phone Please", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (etFdbck.getText().toString().length() == 0) {
                    Snackbar.make(v, "Enter Feedback Please", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                String subjectt = subject[spnSub.getSelectedItemPosition()];
                RadioButton rbSel = (RadioButton) findViewById(rgGender.getCheckedRadioButtonId());

                String message = "Name: " + name + "\nPhone:" + phone + "\nGender: " + rbSel.getText().toString() +
                        "\nSubject: " + subjectt + "\nRating: " + rbReview.getRating() + "\nFeedback: " + etFdbck.getText().toString();

                Intent i = new Intent(MainActivity.this, SendActivity.class);
                i.putExtra("msg", message);
                startActivity(i);
            }
        });

    }
}
