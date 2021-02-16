package com.example.challengingalarmproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizChallenge extends AppCompatActivity{

    ImageView mQuizImage;
    TextView mQuestionView;
    TextView mQuizNumView;
    MediaPlayer mp;
    String mAnswer;
    int mScore = 0;
    int mQuizNum = 1;
    int QuestionNum = 0;
    int Minute = 120 ;
    boolean done;
    PendingIntent pendingIntent = null;
    AlarmManager alarmManager;
    public static final int RECEIVER_REQ = 280192;

    // יצירת אובייקט של המחלקה
    Questions mQuestions = new Questions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_challenge);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        mQuestionView = findViewById(R.id.tvQuestions);
        mQuizNumView = findViewById(R.id.tvNumQuiz);
        done = false;

        updateQuestion();

        Button Submit = findViewById(R.id.btn);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestions.getType(QuestionNum) == "radiobutton") {
                    if(mQuestions.getCorrectAnswers(QuestionNum).equals(mAnswer))
                    {
                        mScore++;
                        displayToastCorrectAnswer();
                    }
                    else
                    {
                        displayToastWrongAnswer();
                    }
                }
                //כאשר המשתמש ענה על 3 תשובות נכונות מעבר לאתגר הבא + איפוס המחסנית
                if(mScore == 3){
                    done = true;
                    Intent intent1 = new Intent(v.getContext(), MemoryChallenge.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            QuizChallenge.this.startActivity(intent1);
                    finish();
                }
                SystemClock.sleep(500);
                //איפוס הנתונים כאשר סיימנו את כל השאלות
                if (QuestionNum == mQuestions.getLength() - 1){
                    QuestionNum = 0;
                    mQuizNum = 0;
                    mScore = 0;
                }
                // מעבר לשאלה הבאה
                else {
                    QuestionNum++;
                    mQuizNum++;
                }
                updateQuestion();
            }
        });
    }
    //פונקציה להפעלת והפסקת המוזיקה
    @Override
    protected void onStart() {
        super.onStart();
        mp = MediaPlayer.create(this,R.raw.disney);
        mp.setLooping(true);
        mp.start();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }
        if(!done){
                Intent intent = new Intent(this, MyReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(
                        this, RECEIVER_REQ, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (Minute * 1000), pendingIntent);
        }
        mp.release();

    }

    //הצגת הודעה על תשובה נכונה
    private void displayToastCorrectAnswer() {
        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
    }
    // הצגת הודעה על הודעה שגויה
    private void displayToastWrongAnswer() {
        Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
    }

    // פונקציה לעדכון פרטי השאלה
    //מחיקת הפרטים הקודמים ועדכון פרטים כמו:
    //תמונות, בדיקת סוג השאלה והצגת האפשרויות הצגת מספר השאלה הנוכחי ועוד..
    private void updateQuestion(){
        LinearLayout answer_layout= findViewById(R.id.llAnswers);
        answer_layout.removeAllViews();
        mAnswer="";
        mQuizNumView.setText(" "+mQuizNum+"/"+String.valueOf(mQuestions.getLength()));
        mQuestionView.setText(mQuestions.getQuestions( QuestionNum));
        if (mQuestions.getType(QuestionNum) == "radiobutton"){
            showRadioButtonAnswers(QuestionNum);
        }
        showImage();
        ScrollView sv = findViewById(R.id.scrollView);
        sv.smoothScrollTo(0,0);
    }

// פונקציה להצגת התמונה המתאימה לפי מספר השאלה
    public void showImage() {
        mQuizImage = findViewById(R.id.ivQuiz);
        String img = mQuestions.getImages(QuestionNum);
        mQuizImage.setImageResource(getResources().getIdentifier(img, "drawable", getPackageName()));
    }
//פונקציה המציגה את התשובות האפשריות לפי מספר השאלה
    private void showRadioButtonAnswers(int qnum) {
        final LinearLayout answerLayout = findViewById(R.id.llAnswers);
        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        rg.setLayoutParams(lp);
        rg.setPadding(90, 0, 0, 0);
        final RadioButton[] rb1 = new RadioButton[3];//יצירת 3 כפתורים לאפשרויות
        //לולאה להכנסת הנתונים לכפתורים + יצירת העיצוב
        for (int i = 0; i <= 2; i++) {
            rb1[i] = new RadioButton(this);
            rb1[i].setText(mQuestions.getChoice(qnum)[i]);
            rb1[i].setTextColor(Color.BLACK);
            rb1[i].setPadding(8, 16, 8, 16);
            rb1[i].setTextSize(25);
            rb1[i].setId(i);
            rb1[i].setWidth(500);
            rg.addView(rb1[i]);//LinearLayout <-- הוספת הכפתור עם הנתונים לתוך
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int Id) {
                mAnswer = mQuestions.getChoice(QuestionNum)[Id]; // התשובה שהתקבלה מהמשתמש
            }
        });

        answerLayout.addView(rg);
    }
}