package org.swk.bowlingkata;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest
{

        @Test
        public void testAddRoll(){
                final Game sut = new Game();
                sut.addRoll(1);
                assertEquals(1, sut.getFrames().get(0).getScore());
        }

        @Test
        public void testAddSecondRoll(){
                final Game sut = new Game();
                sut.addRoll(1);
                sut.addRoll(5);
                assertEquals(6, sut.getFrames().get(0).getScore());
        }

        @Test
        public void testSpareAndRoll(){
                final Game sut = new Game();
                sut.addRoll(5);
                sut.addRoll(5);
                sut.addRoll(4);
                assertEquals(18, sut.getTotalScore());
        }

}