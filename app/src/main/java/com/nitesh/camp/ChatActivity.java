package com.nitesh.camp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {

    private static final int DRAWING_CODE = 1;
    ImageView callIv, videoIv, endIv, editIv, camIv, micIv;
    EditText editText;
    LinearLayout senderLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = (EditText) findViewById(R.id.inputMsg);
        editIv = (ImageView) findViewById(R.id.edit_imv);
        senderLl = (LinearLayout) findViewById(R.id.sender_ll);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    sendMsg(editText.getText().toString());
                    return true;
                }
                return false;
            }
        });
        editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChatActivity.this, FingerPaintActivity.class);
                startActivityForResult(i,DRAWING_CODE);
            }
        });
    }

    private void sendMsg(String s) {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(0,10,0,0);
        TextView tv=new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setText(s);
        tv.setBackgroundColor(ContextCompat.getColor(this,R.color.colorAccent));
        int px = Utils.dpToPx(this,8);
        tv.setPadding(px,px,px,px);
        tv.setTextColor(ContextCompat.getColor(this,R.color.white));
        editText.setText("");
        senderLl.addView(tv);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==DRAWING_CODE && resultCode==RESULT_OK){
            Bitmap b = BitmapFactory.decodeByteArray( data.getByteArrayExtra("image"),0,data.getByteArrayExtra("image").length);
            String ex = data.getStringExtra("extra");
            ImageView img = new ImageView(this);
            img.setImageBitmap(b);
            senderLl.addView(img);
        }
    }
}