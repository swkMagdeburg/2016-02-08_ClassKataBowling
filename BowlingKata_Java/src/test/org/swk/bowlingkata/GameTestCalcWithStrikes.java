package org.swk.bowlingkata;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTestCalcWithStrikes
{
        @Test
        public void data()
        {
                bla(18, 5, 5, 4);
                bla(44, 10, 10, 2, 4);
                bla(300, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
                bla(1, 1);
                bla(5, 1, 4);
        }

        public void bla(int expected, int... rolls)
        {
                final Game sut = new Game();
                for (int roll : rolls)
                {
                        sut.addRoll(roll);
                }
                assertEquals(expected, sut.getTotalScore());
        }
}
