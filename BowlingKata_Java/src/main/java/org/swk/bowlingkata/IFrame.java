package org.swk.bowlingkata;

public interface IFrame
{
        int getScore();

        boolean isOver();

        void addRoll(int score);

        boolean isStrike();

        boolean isSpare();

        int getScoreFirstRoll();

        int getScoreSecondRoll();

        IFrame getNext();
}
