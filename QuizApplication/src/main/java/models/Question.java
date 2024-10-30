package models;

import java.util.List;

public class Question {
    private int questionId;
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question() {}

    public Question(int questionId, String questionText, List<String> options, int correctOption) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public int getQuestionId() { 
        return questionId; 
    }
    
    public void setQuestionId(int questionId) { 
        this.questionId = questionId; 
    }
    
    public String getQuestionText() { 
        return questionText; 
    }
    
    public void setQuestionText(String questionText) { 
        this.questionText = questionText; 
    }
    
    public List<String> getOptions() { 
        return options; 
    }
    
    public void setOptions(List<String> options) { 
        this.options = options; 
    }
    
    public int getCorrectOption() { 
        return correctOption; 
    }
    
    public void setCorrectOption(int correctOption) { 
        this.correctOption = correctOption; 
    }
    
    public boolean isCorrect(int answer) { 
        return this.correctOption == answer; 
    }
}
