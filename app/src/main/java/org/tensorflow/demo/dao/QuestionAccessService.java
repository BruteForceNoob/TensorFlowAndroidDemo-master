package org.tensorflow.demo.dao;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.demo.QuestionActivity;
import org.tensorflow.demo.QuestionEnum;
import org.tensorflow.demo.core.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAccessService {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //QuestionActivity questionActivity = new QuestionActivity();
    public QuestionAccessService() {
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        //this.databaseReference = firebaseDatabase.getReference(QuestionEnum.QUESTIONS.name());


    }

    public DatabaseReference getDatabaseReference() {
        try {
            //databaseReference = firebaseDatabase.getReference(QuestionEnum.QUESTIONS.name());
        } catch (Exception e) {
            Log.e("dao error", "reference for question id not found");
        }
        return databaseReference;

    }

    public void populateQuestions() {

        Question questionBasketBall = new Question();
        questionBasketBall.setQuestion("Who is the greatest of all time?");
        List<String> optionsList=new ArrayList<>();
        optionsList.add("Michael Jordan");
        optionsList.add("Lebron James");
        questionBasketBall.setOptions(optionsList);
        Map<String,Question> questionMap= new HashMap<String,Question>();
        questionMap.put("basketball", questionBasketBall);
       // databaseReference=getDatabaseReference();
        databaseReference.setValue(questionMap);


    }



}
