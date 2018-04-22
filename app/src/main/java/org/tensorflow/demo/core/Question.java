package org.tensorflow.demo.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question implements Serializable{
    private String question;
    private String questionID;

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    List<String> options = new ArrayList<String>();

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }



    public String getQuestion() {
        return question;
    }



    public void setQuestion(String question) {
        this.question = question;
    }


}
