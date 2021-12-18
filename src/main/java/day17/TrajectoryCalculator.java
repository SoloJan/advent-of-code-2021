package day17;

public class TrajectoryCalculator {




    public long findMaxHeight(int lowerX, int upperX, int lowerY, int upperY){
        long yVel = findYVel(lowerX, upperX, lowerY, upperY) ;
        return (yVel * (yVel+1)) / 2;
    }


    public long findCountOfAllowedVelocities(int lowerX, int upperX, int lowerY, int upperY) {
        long count = 0l;
        for(int xStartVel = 0; xStartVel<=400; xStartVel++) {
            for (long yStartVel = -565; yStartVel < 1565; yStartVel++) {
                int xPos = 0;
                long yPos = 0;
                int xVel = xStartVel;
                long yVel = yStartVel;
                while (true) {
                    if (xPos >= lowerX && xPos <= upperX && (yPos >= lowerY && yPos <= upperY)) {
                        count++;
                        break;
                    }
                    if (xPos > upperX || yPos < lowerY) {
                        break;
                    }
                    xPos += xVel;
                    yPos += yVel;
                    if (xVel > 0) {
                        xVel--;
                    }
                    yVel--;
                }
            }
        }
        return count;
    }

    long findYVel(int lowerX, int upperX, int lowerY, int upperY) {
        long maxY = 0l;
        int xStartVel = findXVel(lowerX);
            for(long yStartVel = 0; yStartVel<5000; yStartVel++) {
                int xPos = 0;
                long yPos = 0;
                int xVel = xStartVel;
                long yVel = yStartVel;
                while (true) {
                    if (xPos >= lowerX && xPos <= upperX && (yPos >= lowerY && yPos <= upperY)) {
                        if(yStartVel>maxY){
                            maxY = yStartVel;
                        }
                        break;
                    }
                    if (xPos > upperX || yPos < lowerY) {
                        break;
                    }
                    xPos += xVel;
                    yPos += yVel;
                    if (xVel > 0) {
                        xVel--;
                    }
                    yVel--;
                }
            }
        return maxY;
    }

    int findXVel(int lowerX){
        int xVel = 0;
        while(true){
            xVel++;
            int finalXPost = (xVel * (xVel+1)) / 2;
            if(finalXPost > lowerX){
                return xVel;
            }
        }

    }



}
