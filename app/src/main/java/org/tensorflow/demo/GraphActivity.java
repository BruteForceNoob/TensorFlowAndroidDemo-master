package org.tensorflow.demo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.demo.R;
import org.tensorflow.demo.core.Question;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphActivity extends Activity {

    ArrayList<BarEntry> barEntries;
    ArrayList<String> barentrylabel;
    BarDataSet barDataSet;
    BarData barData;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    DatabaseReference databaseCommentReference;
    Map<String, String> MJ;
    Map<String, String> comments;
    int option1=0,option2=0;

    RadioButton rbtn1;
    RadioButton rbtn2;
    Question q;

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this, CameraActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("ExitMe", true);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        rbtn1= (RadioButton) findViewById(R.id.rbtn1);
        rbtn2= (RadioButton) findViewById(R.id.rbtn2);



        Intent intent = getIntent();
        q = (Question)intent.getSerializableExtra("tag");


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseCommentReference = firebaseDatabase.getReference(QuestionEnum.COMMENTS.name());
        databaseReference = firebaseDatabase.getReference(q.getQuestionID());

        final TextView textView = (TextView) findViewById(R.id.commentTextView);

        databaseCommentReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                comments = (Map<String, String>)dataSnapshot.getValue();
                String str="";
                String key2="";
                Log.e("Comments: ",comments.toString());
                for(String key : comments.keySet()){
                    if (key.equals("80a22beda8ad1da")){
                         key2 = "Akshay";
                    }
                    if (key.equals("927030b893f4fbe3")){
                        key2 = "Sujeeth";
                    }
                    str = str + "\n" +  key2 + " : "+ comments.get(key).toString() + "\n";
                    textView.setText(str);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                option1=0;option2=0;
                MJ = (Map<String, String>)dataSnapshot.getValue();
                Log.e("DataSnapShot is", MJ.toString());

                for (String str : MJ.values()){
                   if (str.equals(q.getOptions().get(0))){
                       option1+=1;
                   }
                   if (str.equals(q.getOptions().get(1))){
                        option2+=1;
                    }
                }

                Log.e("Option1", String.valueOf(option1));
                Log.e("Option2", String.valueOf(option2));
                renderChart(option1,option2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    public void renderChart(int option1, int option2)
    {
        BarChart barChart = (BarChart) findViewById(R.id.bargraph);
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(option1, 0));
        barEntries.add(new BarEntry(option2, 1));
        barDataSet = new BarDataSet(barEntries, "Poll Results");
        barChart.setAutoScaleMinMaxEnabled(false);
        barentrylabel = new ArrayList<String>();
        barentrylabel.add(q.getOptions().get(0));
        barentrylabel.add(q.getOptions().get(1));
        barData = new BarData(barentrylabel, barDataSet);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.setData(barData);
        barChart.setDescription("");
        barChart.animateY(3000);
        barChart.setTouchEnabled(true);
    }
}
