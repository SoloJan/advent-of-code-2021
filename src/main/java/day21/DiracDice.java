package day21;

import java.util.HashMap;

public class DiracDice {

    /**
        All possible outcomes of throwing the dirac dice three times, with the weight in which it can occur,
        For example you get the value 3 if you throw 1,1,1 this is the only way of getting three therefore the weight is 1
        You can get a 4 by throwing 1,1,2, 1,2,1 or 2,1,1 so this scenario gets a weight of 3
     */
    private static HashMap<Integer, Integer> steps = new HashMap<>();
    static {
        steps.put(3, 1);
        steps.put(4, 3);
        steps.put(5, 6);
        steps.put(6, 7);
        steps.put(7, 6);
        steps.put(8, 3);
        steps.put(9, 1);
    }

    long player1Wins = 0l;
    long player2Wins = 0l;


    /**
     * This method plays for all different outcomes which can occur when playing with a dirac dice which has the values 1, 2, or 3
     * @param player1Pos the starting position of player 1
     * @param player2Pos the starting position of player 2
     * @return the amount of times the player with the most wins wins
     */
    public long play( int player1Pos, int player2Pos){
        play(0, 0, player1Pos, player2Pos, 1, true);
        return player1Wins >= player2Wins ? player1Wins : player2Wins;

    }

    private void play(int player1Score, int player2Score, int player1Pos, int player2Pos, long weight, boolean player1Turn){
        if(player1Score >= 21){
            player1Wins = player1Wins + weight;
            return;
        }
        if(player2Score >= 21){
            player2Wins = player2Wins + weight;
            return;
        }
        if(player1Turn){
            player1Moves(player1Score, player2Score, player1Pos, player2Pos, weight);
        }
        else{
            player2Moves(player1Score, player2Score, player1Pos, player2Pos, weight);
        }
    }


    void player2Moves(int player1Score, int player2Score, int player1Pos, int player2Pos, long weight){
        for(Integer step: steps.keySet()){
            int newPosition = getNewPosition(player2Pos, step);
            play(player1Score, player2Score+newPosition, player1Pos, newPosition, weight*steps.get(step), true);
        }
    }

    void player1Moves(int player1Score, int player2Score, int player1Pos, int player2Pos, long weight){
        for(Integer step: steps.keySet()){
            int newPosition = getNewPosition(player1Pos, step);
            play(player1Score+newPosition, player2Score, newPosition, player2Pos, weight*steps.get(step), false);
        }
    }

    public int getNewPosition(int oldPosition, int stepsToTake){
        int newPos = oldPosition + stepsToTake;
        if(newPos > 10){
            newPos = newPos-10;
        }
        return newPos;
    }

}
