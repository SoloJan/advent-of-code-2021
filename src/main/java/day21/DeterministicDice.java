package day21;

public class DeterministicDice {

    private int diceValue = 0;
    private int diceRolled = 0;
    private int player1Pos = 0;
    private int player2Pos = 0;
    private int player1SCore = 0;
    private int player2Score = 0;

    public int play(int startPos1, int startPos2){
        this.player1Pos = startPos1;
        this.player2Pos = startPos2;
        while(player1SCore <1000 && player2Pos < 1000) {
            player1Moves();
            if(player1SCore >= 1000){
                return player2Score * diceRolled;
            }
            player2Moves();
            if(player2Score >= 1000){
                return player1SCore * diceRolled;
            }
        }
        return getLowest(player1SCore, player2Score) * diceRolled;
    }

    private void player2Moves(){
        int newPos = getNewPosition(player2Pos);
        player2Pos = newPos;
        player2Score+=newPos;
    }

    private void player1Moves(){
        int newPos = getNewPosition(player1Pos);
        player1Pos = newPos;
        player1SCore+=newPos;
    }

    private int getNewPosition(int oldPosition){
        int stepsToTake = rollDiceThreeTimes();
        int remainingStepsAfterFullCircles = stepsToTake%10;
        int newPos = oldPosition + remainingStepsAfterFullCircles;
        if(newPos > 10){
            newPos = newPos-10;
        }
        return newPos;
    }

    private int rollDiceThreeTimes(){
        return rolDice()+rolDice()+rolDice();
    }

    private int rolDice(){
        diceRolled++;
        diceValue++;
        if(diceValue > 100){
            diceValue = 1;
            return diceValue;
        }

        return diceValue;
    }

    private int getLowest(int x, int x2) {
        return x <= x2 ? x : x2;
    }

}
