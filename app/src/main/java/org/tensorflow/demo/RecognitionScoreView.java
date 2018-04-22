/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package org.tensorflow.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.demo.Classifier.Recognition;
import org.tensorflow.demo.core.Question;
import org.tensorflow.demo.dao.QuestionAccessService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecognitionScoreView extends View {
  private static final float TEXT_SIZE_DIP = 24;
  private List<Recognition> results;
  private final float textSizePx;
  private final Paint fgPaint;
  private final Paint bgPaint;
  private FirebaseDatabase firebaseDatabase;
  private DatabaseReference databaseReference;
  private Map<String, String> MJ;
  private QuestionAccessService questionAccessService=new QuestionAccessService();

  public RecognitionScoreView(final Context context, final AttributeSet set) {
    super(context, set);

    textSizePx =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
    fgPaint = new Paint();
    fgPaint.setTextSize(textSizePx);

    bgPaint = new Paint();
    bgPaint.setColor(0xcc4285f4);
    //questionAccessService.populateQuestions();
  }

  public void setResults(final List<Recognition> results) {
    this.results = results;

    postInvalidate();
  }

  @Override
  public void onDraw(final Canvas canvas) {
    final int x = 10;
    int y = (int) (fgPaint.getTextSize() * 1.5f);

    canvas.drawPaint(bgPaint);

    if (results != null) {
      for (final Recognition recog : results) {
        canvas.drawText(recog.getTitle() + ": " + recog.getConfidence(), x, y, fgPaint);
        y += fgPaint.getTextSize() * 1.5f;
      }


        if (!results.isEmpty())
        android.util.Log.e("Answer is : ", results.get(0).getTitle());

        try{
          android.app.Activity host = (android.app.Activity) this.getContext();
          String str = results.get(0).getTitle();
          if (str.equals("basketball")){


            Question qBasketBall = new Question();
            List<String> options = new ArrayList<>();
            options.add("Michael Jordan");
            options.add("LeBron James");
            qBasketBall.setOptions(options);

              qBasketBall.setQuestionID(QuestionEnum.BASKETBALL_LEGENDS.name());
              qBasketBall.setQuestion("Who is the greatest of all time?");







//
//                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(host, QuestionActivity.class);
            //intent.putExtras(bundle);
/*
                    intent.putExtra("questionLabel", QuestionEnum.BASKETBALL_LEGENDS.name());
                    String[] options2 = {"Michael Jordan", "Lebron James"};
                    intent.putExtra("options", options2);
                    intent.putExtra("Question", "Who is the greatest of all time?");*/
            intent.putExtra("key", qBasketBall);
            host.startActivity(intent);


          }
          if (str.equals("loudspeaker")){

            Question qLoudSpeaker = new Question();
            qLoudSpeaker.setQuestion("Is Global Warming a Hoax?");
            List<String> options = new ArrayList<>();
            options.add("Yes");
            options.add("No");
            qLoudSpeaker.setOptions(options);
            qLoudSpeaker.setQuestionID(QuestionEnum.NEWS.name());
//
//                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(host, QuestionActivity.class);
            //intent.putExtras(bundle);
/*
                    intent.putExtra("questionLabel", QuestionEnum.BASKETBALL_LEGENDS.name());
                    String[] options2 = {"Michael Jordan", "Lebron James"};
                    intent.putExtra("options", options2);
                    intent.putExtra("Question", "Who is the greatest of all time?");*/
            intent.putExtra("key", qLoudSpeaker);
            host.startActivity(intent);


          }

          if (str.equals("joystick")){

            Question joyStick = new Question();
            joyStick.setQuestion("Is this the most epic move in DOTA?");
            List<String> options = new ArrayList<>();
            options.add("Yes");
            options.add("No");
            joyStick.setOptions(options);
            joyStick.setQuestionID(QuestionEnum.GAME.name());
//
//                    Bundle bundle = new Bundle();
            Intent intent = new Intent(host, QuestionActivity.class);
            //intent.putExtras(bundle);
/*
                    intent.putExtra("questionLabel", QuestionEnum.BASKETBALL_LEGENDS.name());
                    String[] options2 = {"Michael Jordan", "Lebron James"};
                    intent.putExtra("options", options2);
                    intent.putExtra("Question", "Who is the greatest of all time?");*/
            intent.putExtra("key", joyStick);
            host.startActivity(intent);


          }
        }
        catch(Exception e){

}


    }

  }
}
