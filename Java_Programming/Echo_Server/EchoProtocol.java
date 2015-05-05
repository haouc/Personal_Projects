/**
 * Created by Hao on 1/13/2015.
 */

public class EchoProtocol {

    private int state = 0;
    private int currentTalk = 0;

    private String[] info = {"Copy that, are you alright?", "Copy that, please hold your position!", "Okay, good luck!", "Bye."};
    private String[] answers = {"This is delta one, do you copy?", "Base, we are on position.", "Roger that.", "Thanks base."};

    public String processInput(String theInput){
        String theOutput = null;

        if(state == 0){
            theOutput = "This is base, how are you doing today!";
            state++;
        }

        else if (state == 1){
            if(theInput.equalsIgnoreCase(answers[currentTalk])){
                theOutput = info[currentTalk];
                state++;
                currentTalk++;
            }
            else {
                theOutput = "You are supposed to say, " + answers[currentTalk];
            }
        }
        else if (state == 2){
            if(theInput.equalsIgnoreCase(answers[currentTalk])){
                theOutput = info[currentTalk];
                state++;
                currentTalk++;
            }
            else {
                theOutput = "You are supposed to say " + answers[currentTalk] + "! Try again.";
            }
        }
        else if (state == 3){
            if(theInput.equalsIgnoreCase(answers[currentTalk])){
                theOutput = info[currentTalk];
                state++;
                currentTalk++;
            }
            else {
                theOutput = "You are supposed to say " + answers[currentTalk] + " Try again.";
            }
        }
        else if (state == 4){
            if(theInput.equalsIgnoreCase(answers[currentTalk])){
                theOutput = info[currentTalk];
                state = 0;
                currentTalk = 0;
            }
            else {
                theOutput = "You are supposed to say " + answers[currentTalk] + " Try again.";
            }
        }
        return theOutput;
    }
}
