using System.Linq;

namespace KataBowling.Tests
{
    public class Game
    {
        private Frame[] _frames;
        private int _rollIdx;
        private int _frameIdx;

        public Game()
        {
            _frames = new Frame[10];
            _frameIdx = 0;
        }

        public int TotalScore()
        {
            return _frames.Where(f => f != null).Sum(f => f.Score);
        }

        public bool Over()
        {
            return false;
        }

        public Frame[] Frames()
        {
            return _frames;
        }

        public void AddRoll(int pins)
        {
            if (_rollIdx == 1)
            {
                _frameIdx++;
            }
            if (_frames[_frameIdx] == null)
            {
                _frames[_frameIdx] = new Frame();
                _rollIdx = 0;


                if ((_frameIdx > 0) && (_frames[_frameIdx - 1].Score == 10))
                {
                    _frames[_frameIdx - 1].Score += pins;
                }
            }
            else
            {
                _rollIdx++;
            }
            _frames[_frameIdx].PinsRolled[_rollIdx] = pins;
            _frames[_frameIdx].Score += pins;


        }
    }
}