package org.tensorflow.demo;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.BarChart;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.*;
import android.provider.Settings.Secure;
import android.content.Context;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.tensorflow.demo.core.Question;
import org.tensorflow.demo.dao.QuestionAccessService;

import java.util.HashMap;
import java.util.Map;

public class QuestionActivity extends Activity {

FirebaseDatabase database;
DatabaseReference  myRef ;
//DatabaseReference newRef;
private String android_id;
public HashMap<String, Question> databaseQuestionMap = new HashMap<>();
private QuestionAccessService questionAccessService=new QuestionAccessService();
private Question q;


    public void SubmitClicked(View view){
        //android.app.Activity host2 = (android.app.Activity) this.getContext();
        //Question question= new Question();
        //question.setQuestionId("1");
        //databaseQuestionMap.put(question.getQuestionId(), question);

        android_id=Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);

        database= FirebaseDatabase.getInstance();


        myRef = database.getReference(q.getQuestionID());
        Log.e("check map is",myRef.toString());
      //  Map<String,String> checkMap=(HashMap<String,String>) myRef.child(QuestionEnum.BASKETBALL_LEGENDS.name());






       // myRef = database.getReference(android_id);
        RadioButton rbtn1 = (RadioButton) findViewById(R.id.rbtn1);
        RadioButton rbtn2 = (RadioButton) findViewById(R.id.rbtn2);
        if (rbtn1.isChecked()){

            android.util.Log.e("Selected Answer is", rbtn1.getText().toString());
        //myRef.setValue(rbtnMJ.getText().toString());
            //question.setAnswerMap(android_id,rbtnMJ.getText().toString());
            //myRef.push().child(android_id).setValue(rbtnMJ.getText().toString());
            myRef.child(android_id);


            myRef.child(android_id).setValue(rbtn1.getText().toString());
            //myRef.child(rbtnLJ.getText().toString());
        }

        if (rbtn2.isChecked()){

            android.util.Log.e("Selected Answer is", rbtn2.getText().toString());
       // myRef.setValue(rbtnLJ.getText().toString());
            //question.setAnswerMap(android_id,rbtnLJ.getText().toString());
            myRef.child(android_id);
            myRef.child(android_id).setValue(rbtn2.getText().toString());


        }

        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("tag", q);
        startActivity(intent);
    }

    public void publishClicked(View view){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(QuestionEnum.COMMENTS.name());

        android_id=Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);
        EditText editText = (EditText) findViewById(R.id.editTextComment);
        myRef.child(android_id).setValue(editText.getText().toString());
        editText.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        /*String questionLabel = intent.getStringExtra("questionLabel");
        String[] options = intent.getStringArrayExtra("options");
        String question = intent.getStringExtra("Question");
        */
        q = (Question)intent.getSerializableExtra("key");


        TextView questionTextView = (TextView) findViewById(R.id.textViewQuestion);
        questionTextView.setText(q.getQuestion());

        RadioButton rbtn1 = (RadioButton) findViewById(R.id.rbtn1);
        RadioButton rbtn2 = (RadioButton) findViewById(R.id.rbtn2);
        rbtn1.setText(q.getOptions().get(0));

        rbtn2.setText(q.getOptions().get(1));


        Log.e("RBTN1", rbtn1.getText().toString());
        Log.e("RBTN2", rbtn2.getText().toString());


//        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup2);
//        for(int i= 0;i<options.length;i++){
//            RadioButton rdbtn = new Radio
//        }

    }
}
