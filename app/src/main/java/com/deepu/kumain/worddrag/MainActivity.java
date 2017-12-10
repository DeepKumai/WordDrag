package com.deepu.kumain.worddrag;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView td,word,t4,t5,t6,t7,t8,t9;
    int d=0,c=0;
    public String s,fs;
    private Firebase w,T1,T2,T3,T4,T5,T6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        t4=(TextView) findViewById(R.id.t4);
        t4.setOnTouchListener(touchlisten);
        t5=(TextView)findViewById(R.id.t5);
        t5.setOnTouchListener(touchlisten);
        t6=(TextView)findViewById(R.id.t6);
        t6.setOnTouchListener(touchlisten);
        t7=(TextView)findViewById(R.id.t7);
        t7.setOnTouchListener(touchlisten);
        t8=(TextView)findViewById(R.id.t8);
        t8.setOnTouchListener(touchlisten);
        t9=(TextView)findViewById(R.id.t9);
        t9.setOnTouchListener(touchlisten);
        word=(TextView)findViewById(R.id.word);
        word.setOnDragListener(draglisten);
        updateWord();
    }

    public void updateWord(){
        Random r = new Random();
        d=r.nextInt(5);
        if(d!=c) {
            c=d;
            w = new Firebase("https://worddrag-97a8b.firebaseio.com/" + c + "/word");
            w.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String q = dataSnapshot.getValue(String.class);
                    word.setText(q);
                    fs = q;
                    s = q;
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            T1 = new Firebase("https://worddrag-97a8b.firebaseio.com/" + c + "/c1");
            T1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String q = dataSnapshot.getValue(String.class);
                    t4.setText(q);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            T2 = new Firebase("https://worddrag-97a8b.firebaseio.com/" + c + "/c2");
            T2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String q = dataSnapshot.getValue(String.class);
                    t5.setText(q);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            T3 = new Firebase("https://worddrag-97a8b.firebaseio.com/" + c + "/c3");
            T3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String q = dataSnapshot.getValue(String.class);
                    t6.setText(q);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            T4 = new Firebase("https://worddrag-97a8b.firebaseio.com/" + c + "/c4");
            T4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String q = dataSnapshot.getValue(String.class);
                    t7.setText(q);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            T5 = new Firebase("https://worddrag-97a8b.firebaseio.com/" + c + "/c5");
            T5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String q = dataSnapshot.getValue(String.class);
                    t8.setText(q);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            T6 = new Firebase("https://worddrag-97a8b.firebaseio.com/" + c + "/c6");
            T6.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String q = dataSnapshot.getValue(String.class);
                    t9.setText(q);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else{
            updateWord();
        }
    }

    View.OnTouchListener touchlisten = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(data, shadowBuilder, view, 0);
                } else {
                    view.startDrag(data, shadowBuilder, view, 0);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    View.OnDragListener draglisten = new View.OnDragListener(){

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int a = event.getAction();
            switch (a) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    View view = (View) event.getLocalState();
                    if(view.getId()==R.id.t4){
                        String b=t4.getText().toString();
//                        Log.d("ADebugTag", "Value: " + (b));
                        if(fs.contains(b)){
                            changecolor(b);
                            break;
                        }
                        Toast.makeText(getApplicationContext(),"Wrong Letter", Toast.LENGTH_SHORT).show();
                    }
                    if(view.getId()==R.id.t5){
                        String b=t5.getText().toString();
//                        Log.d("ADebugTag", "Value: " + (b));
                        if(fs.contains(b)){
                            changecolor(b);
                            break;
                        }
                        Toast.makeText(getApplicationContext(),"Wrong Letter", Toast.LENGTH_SHORT).show();
                    }
                    if(view.getId()==R.id.t6){
                        String b=t6.getText().toString();
                        if(fs.contains(b)){
//                            Log.d("ADebugTag", "Value: " + (b));
                            changecolor(b);
                            break;
                        }
                        Toast.makeText(getApplicationContext(),"Wrong Letter", Toast.LENGTH_SHORT).show();
                    }
                    if(view.getId()==R.id.t7){
                        String b=t7.getText().toString();
                        if(fs.contains(b)){
                            changecolor(b);
                            break;
                        }
                        Toast.makeText(getApplicationContext(),"Wrong Letter", Toast.LENGTH_SHORT).show();
                    }
                    if(view.getId()==R.id.t8){
                        String b=t8.getText().toString();
                        if(fs.contains(b)){
                            changecolor(b);
                            break;
                        }
                        Toast.makeText(getApplicationContext(),"Wrong Letter", Toast.LENGTH_SHORT).show();
                    }
                    if(view.getId()==R.id.t9){
                        String b=t9.getText().toString();
                        if(fs.contains(b)){
                            changecolor(b);
                            break;
                        }
                        Toast.makeText(getApplicationContext(),"Wrong Letter", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
            }
            return true;
        }

    };

    private void changecolor(String a){
        char c=a.charAt(0);
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)==c){
                SpannableStringBuilder builder = new SpannableStringBuilder();
                SpannableString redSpannable= new SpannableString(word.getText());
                redSpannable.setSpan(new ForegroundColorSpan(Color.MAGENTA), j, j+1, 0);
                builder.append(redSpannable);
                word.setText(builder, TextView.BufferType.SPANNABLE);
                fs=fs.replaceFirst(a,"");
                //Log.d("ADebugTag", "Value: " + (fs));
                if(fs.length()==0){
                    Toast.makeText(getApplicationContext(),"Congrats, You placed all letters correctly", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            updateWord();
                        }
                    }, 2000);
                }
            }
        }
    }
}


