package org.swk.bowlingkata;

public class LastFrame implements IFrame
{
        int round;
        int[] scores = new int[3];

        public LastFrame()
        {

        }

        @Override public boolean isOver()
        {
                return round == 3;
        }

        @Override public void addRoll(int score)
        {
                scores[round++] = score;
        }

        @Override public boolean isStrike()
        {
                return false;
        }

        @Override public boolean isSpare()
        {
                return false;
        }

        @Override public int getScore()
        {
                int score = scores[0]+scores[1]+scores[2];
                if(scores[0]==10)
                        score += scores[1]+scores[2];
                if(scores[1] == 10)
                        score += scores[2];
                return score;
        }

        @Override public int getScoreFirstRoll()
        {
                return 0;
        }

        @Override public int getScoreSecondRoll()
        {
                return 0;
        }

        @Override public IFrame getNext()
        {
                return null;
        }
}
