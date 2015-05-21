package proclassicsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import proclassicsquiz.R;


public class QuizActivity extends ActionBarActivity {




    private Button mExitButton, mLatinButton, mGreekButton, mMixedButton;
    private String mName;
    private EditText mNameEditText;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //define behaviors
        mNameEditText = (EditText) findViewById(R.id.editName);

        //exit button
        mExitButton = (Button) findViewById(R.id.exitButton);
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Button button = (Button) v;
                //button.setBackgroundColor(Color.CYAN);

                finish();
            }
        });

        //start button
        mLatinButton = (Button) findViewById(R.id.latinButton);
        mLatinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startMe(0);
            }
        });

        mGreekButton = (Button) findViewById(R.id.greekButton);
        mGreekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startMe(1);
            }
        });

        mMixedButton = (Button) findViewById(R.id.mixedButton);
        mMixedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startMe(2);
            }
        });


    }

    private void startMe(int i) {
        mName = mNameEditText.getText().toString();
        QuizTracker.getInstance().setName(mName);
        if(i == 0){
            askQuestionLatin(1);
        }
        else if(i == 1){
            askQuestionGrk(1);
        }
        else{
            askQuestionMix(1);
        }
    }

    private void askQuestionLatin(int number) {
        QuizTracker.getInstance().setQuestionNum(number);
        Intent intent = new Intent(QuizActivity.this, QuestionActivityLatin.class);
        startActivity(intent);
    }

    private void askQuestionGrk(int number) {
        QuizTracker.getInstance().setQuestionNum(number);
        Intent intent = new Intent(QuizActivity.this, QuestionActivityGrk.class);
        startActivity(intent);
    }

    private void askQuestionMix(int number) {
        QuizTracker.getInstance().setQuestionNum(number);
        Intent intent = new Intent(QuizActivity.this, QuestionActivityMixed.class);
        startActivity(intent);
    }



    //these are for the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuExit:
                finish();
                return true;
            case R.id.menuLatin:
                startMe(0);
                return true;
            case R.id.menuGreek:
                startMe(1);
                return true;
            case R.id.menuMixed:
                startMe(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
