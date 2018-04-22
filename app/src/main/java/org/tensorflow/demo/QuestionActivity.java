package org.tensorflow.demo;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.*;
import android.provider.Settings.Secure;
import android.content.Context;
public class QuestionActivity extends Activity {

FirebaseDatabase database;
DatabaseReference myRef;
DatabaseReference newRef;
private String android_id;


    public void SubmitClicked(View view){
        //android.app.Activity host2 = (android.app.Activity) this.getContext();
        android_id=Secure.getString(this.getContentResolver(),Secure.ANDROID_ID);
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference(android_id);
               RadioButton rbtnMJ = (RadioButton) findViewById(R.id.rbtn1);
        RadioButton rbtnLJ = (RadioButton) findViewById(R.id.rbtn2);
        if (rbtnMJ.isChecked()){

            android.util.Log.e("Selected Answer is", rbtnMJ.getText().toString());
        myRef.setValue(rbtnMJ.getText().toString());
        }

        if (rbtnLJ.isChecked()){

            android.util.Log.e("Selected Answer is", rbtnLJ.getText().toString());
        myRef.setValue(rbtnLJ.getText().toString());

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }
}
