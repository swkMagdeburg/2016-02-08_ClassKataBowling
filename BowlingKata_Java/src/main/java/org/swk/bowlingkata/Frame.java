package org.swk.bowlingkata;

public class Frame implements IFrame
{
        private int round = 0;
        private int[] scores = new int[2];
        private boolean lastFrame;
        private boolean strike;
        private boolean spare;
        private IFrame next;


        public Frame(IFrame next)
        {
                this.lastFrame = next.isOver();
                this.next = next;
        }

        protected Frame()
        {
                //
        }

        @Override public int getScore()
        {
                int score = scores[0] + scores[1];
                if (isStrike()) {
                        score += next.getScoreFirstRoll() + next.getScoreSecondRoll();
                } else if (isSpare()) {
                        score += next.getScoreFirstRoll();
                }
                return score;
        }

        @Override public boolean isOver()
        {
                return round == 2 || scores[0] == 10;
        }

        @Override public void addRoll(int score)
        {
                if(isOver())
                        throw new IllegalArgumentException("Der Frame ist bereits geschlossen");
                if(score < 0 || getScore() + score > 10)
                        throw new IllegalArgumentException("Score darf nicht kleiner 0 oder größer 10 sein: "+score);
                scores[round++] = score;
        }

        @Override public boolean isStrike()
        {
                return scores[0] == 10;
        }

        @Override public boolean isSpare()
        {
                if(scores[0] == 10)
                        return false;
                return (scores[0] + scores[1]) == 10;
        }

        @Override public int getScoreFirstRoll()
        {
                return scores[0];
        }

        @Override public int getScoreSecondRoll()
        {
                if(isStrike())
                        return next.getScoreFirstRoll();
                else
                        return scores[1];
        }

        @Override public IFrame getNext()
        {
                return next;
        }
}
