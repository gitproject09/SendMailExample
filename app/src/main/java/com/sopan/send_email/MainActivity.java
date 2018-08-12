package com.sopan.send_email;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crazygeeksblog.androidsendemail.R;

public class MainActivity extends AppCompatActivity {

    private EditText etToMailId, etMailSubject, etMailMessage;
    private Button btnSendEmail;
    String toMailId, mailSubject, mailMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToMailId = (EditText) findViewById(R.id.etToMail);
        etMailSubject = (EditText) findViewById(R.id.etSubject);
        etMailMessage = (EditText) findViewById(R.id.etMessage);
        btnSendEmail = (Button) findViewById(R.id.btnSendMail);
    }

    public void btnSendMailClick(View view) {
        // Get all edit text values
        toMailId = etToMailId.getText().toString();
        mailSubject = etMailSubject.getText().toString();
        mailMessage = etMailMessage.getText().toString();


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        //Add To Mail Address
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{toMailId});
        //Add CC Mail Address
        //emailIntent.putExtra(Intent.EXTRA_CC, new String[]{ cc mail id here});

        //Add BCC Mail Address
        //emailIntent.putExtra(Intent.EXTRA_BCC, new String[]{bcc mail id here});

        // Add Email Subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);

        // Add Email Message
        emailIntent.putExtra(Intent.EXTRA_TEXT, mailMessage);

        //Add this to prompt email client only
        emailIntent.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(emailIntent, "Choose App To Complete"));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
