package org.swk.bowlingkata;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FrameTest
{
        private Frame sut;

        @Before
        public void createSut ()
        {
                sut = new Frame(new LastFrame());
        }


        @Test
        public void testGetScore() throws Exception
        {
                assertEquals(0, sut.getScore());
                sut.addRoll(5);
                assertEquals(5, sut.getScore());
        }

        @Test
        public void testIsOver() throws Exception
        {
                sut.addRoll(0);
                sut.addRoll(0);
                assertTrue(sut.isOver());
        }

        @Test(expected = IllegalArgumentException.class)
        public void testFailsOnTooMuchPins() throws Exception
        {
                sut.addRoll(9);
                sut.addRoll(5);
        }

        @Test
        public void testNotYetOver() throws Exception
        {
                sut.addRoll(0);
                assertFalse(sut.isOver());
        }

        @Test
        public void testStrike() throws Exception
        {
                sut.addRoll(10);
                assertTrue(sut.isStrike() && sut.isOver());
        }

        @Test
        public void testSpare() throws Exception
        {
                sut.addRoll(5);
                sut.addRoll(5);
                assertTrue(sut.isSpare() && sut.isOver());
        }
}