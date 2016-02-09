package org.swk.bowlingkata;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LastFrameTest
{
        private LastFrame sut;

        @Before
        public void initSut(){
                 sut = new LastFrame();
        }


        @Test
        public void testLastRoundNotYetOver() throws Exception
        {
                sut.addRoll(10);
                sut.addRoll(10);
                assertFalse(sut.isOver());
        }

        @Test
        public void testLastRoundOver() throws Exception
        {
                sut.addRoll(10);
                sut.addRoll(10);
                sut.addRoll(10);
                assertTrue(sut.isOver());
        }
}