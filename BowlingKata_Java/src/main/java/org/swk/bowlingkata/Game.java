package org.swk.bowlingkata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game
{

        private List<IFrame> frames = new ArrayList<>();
        private IFrame currentFrame = new LastFrame();

        public Game() {
                frames.add(currentFrame);
                for (int i = 0; i < 9; i++)
                {
                        IFrame next = currentFrame;
                        currentFrame = new Frame(next);
                        frames.add(currentFrame);
                }
                Collections.reverse(frames);
                currentFrame = frames.get(0);
        }



        public void addRoll(int score)
        {
                if (currentFrame.isOver()) {
                        currentFrame = currentFrame.getNext();
                }
                currentFrame.addRoll(score);
        }

        public List<IFrame> getFrames()
        {
                return frames;
        }

        public int getTotalScore()
        {
                int total = 0;
                for (IFrame frame : frames)
                {
                        total += frame.getScore();

                }
                return total;
        }
}
