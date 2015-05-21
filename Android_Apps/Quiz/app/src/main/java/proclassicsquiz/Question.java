package proclassicsquiz;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Question implements Serializable {

    //this overrides the serializable id
    private static final long serialVersionUID = 6546546516546843135L;

    private String mEnglish;
    private String mForeign;
    private String mLanguage;
    private Set<String> mWrongAnswers = new HashSet<String>();

    public Question(String english, String foreign, String language) {
        this.mEnglish = english;
        this.mForeign = foreign;
        this.mLanguage = language;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public String getForeign() {
        return mForeign;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public Set<String> getWrongAnswers() {
        return mWrongAnswers;
    }

    public boolean addWrongAnswer(String wrongAnswer){
        return mWrongAnswers.add(wrongAnswer);
    }

    public String getQuestionText(){
        return "What is the word of " + mEnglish + "?";
    }
}
