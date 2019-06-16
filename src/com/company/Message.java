package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    public String adId;
    public String message;
    public Integer reward;
    public Integer expiresIn;
    public String probability;

    public Message(String adId, String message, Integer reward, Integer expiresIn, String probability){
        this.adId = adId;
        this.expiresIn = expiresIn;
        this.message = message;
        this.reward = reward;
        this.probability = probability;

    }

    public static Message searchForAMessageToSolve(ArrayList<Message> messages) {
        ArrayList<Message> highProbabilityMessages = new ArrayList<>();
        ArrayList<Message> mediumProbabilityMessages = new ArrayList<>();
        ArrayList<Message> lowProbabilityMessages = new ArrayList<>();

        rankMessages(highProbabilityMessages, mediumProbabilityMessages, lowProbabilityMessages, messages);

        Message messageChosen;

        if (highProbabilityMessages.size() > 0) {
            messageChosen = findHighestRewardingMessage(highProbabilityMessages);
        } else if (mediumProbabilityMessages.size() > 0) {
            messageChosen = findHighestRewardingMessage(mediumProbabilityMessages);
        } else {
            messageChosen = findHighestRewardingMessage(lowProbabilityMessages);
        }

        return messageChosen;
    }

    public static void rankMessages(ArrayList<Message> highProbabilityToSolveMessages, ArrayList<Message> mediumProbabilityToSolveMessages, ArrayList<Message> lowProbabilityToSolveMessages, ArrayList<Message> messages) {
        for (Message message : messages) {
            if (message.probability.equals("Suicide mission")||message.probability.equals("Impossible")||message.message.contains("Steal")){
                lowProbabilityToSolveMessages.add(message);
            } else if (message.probability.equals("Sure thing") || message.probability.equals("Piece of cake")||message.probability.equals("Walk in the park")||message.probability.equals("Quite likely")) {
                highProbabilityToSolveMessages.add(message);
            } else  {
                mediumProbabilityToSolveMessages.add(message);
            }
        }
    }

    public static Message findHighestRewardingMessage(ArrayList<Message> Messages) {
        Message messageChosen = new Message(null, null, 0, null, null);
        for (Message message : Messages) {
            // if current message reward is higher than message previously chosen set message chosen to current message
            if (message.reward > messageChosen.reward) {
                if(message.adId.length() > 8){
                    Messages.remove(message);
                    messageChosen = findHighestRewardingMessage(Messages);
                    return messageChosen;
                }
                messageChosen = message;
            }
        }
        return messageChosen;
    }

}
