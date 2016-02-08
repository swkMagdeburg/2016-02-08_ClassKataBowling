using System;
using System.Runtime.Remoting.Messaging;
using System.Runtime.Versioning;
using System.Security.Policy;
using System.Text.RegularExpressions;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace KataBowling.Tests
{
    [TestClass]
    public class GameTests
    {
        [TestMethod]
        public void Game_InitialValues()
        {
            // Arrange

            // Act
            var sut = new Game();

            // Assert
            Assert.AreEqual(0, sut.TotalScore(), "TotalScore should be 0");
            Assert.IsFalse(sut.Over());
            Assert.AreEqual(10, sut.Frames().Length, "There should be 10 Frames");
        }

        [TestMethod]
        public void AddRole_1()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);

            // Assert
            AssertGame(sut, "([1],1)", 1, false);
        }

        [TestMethod]
        public void AddRole_1_4()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);
            sut.AddRoll(4);

            // Assert
            AssertGame(sut, "([1,4],5)", 5, false);
        }

        [TestMethod]
        public void AddRole_1_4_4()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);
            sut.AddRoll(4);
            sut.AddRoll(4);

            // Assert
            AssertGame(sut, "([1,4],5), ([4],4)", 9, false);
        }

        [TestMethod]
        public void AddRole_1_4_4_5()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);
            sut.AddRoll(4);
            sut.AddRoll(4);
            sut.AddRoll(5);

            // Assert
            AssertGame(sut, "([1,4],5), ([4,5],9)", 14, false);
        }

        [TestMethod]
        public void AddRole_1_4_4_5_6()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);
            sut.AddRoll(4);
            sut.AddRoll(4);
            sut.AddRoll(5);
            sut.AddRoll(6);

            // Assert
            AssertGame(sut, "([1,4],5), ([4,5],9), ([6],6)", 20, false);
        }

        [TestMethod]
        public void AddRole_1_4_4_5_6_4()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);
            sut.AddRoll(4);
            sut.AddRoll(4);
            sut.AddRoll(5);
            sut.AddRoll(6);
            sut.AddRoll(4);

            // Assert
            AssertGame(sut, "([1,4],5), ([4,5],9), ([6,4],10)", 24, false);
        }

        [TestMethod]
        public void AddRole_1_4_4_5_6_4_5()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);
            sut.AddRoll(4);
            sut.AddRoll(4);
            sut.AddRoll(5);
            sut.AddRoll(6);
            sut.AddRoll(4);
            sut.AddRoll(5);

            // Assert
            AssertGame(sut, "([1,4],5), ([4,5],9), ([6,4],15), ([5],5)", 34, false);
        }

        [TestMethod]
        public void AddRole_1_4_4_5_6_4_5_5()
        {
            // Arrange
            var sut = new Game();

            // Act
            sut.AddRoll(1);
            sut.AddRoll(4);
            sut.AddRoll(4);
            sut.AddRoll(5);
            sut.AddRoll(6);
            sut.AddRoll(4);
            sut.AddRoll(5);
            sut.AddRoll(5);

            // Assert
            AssertGame(sut, "([1,4],5), ([4,5],9), ([6,4],15), ([5],5)", 34, false);
        }

        private void AssertGame(Game actualGame, string expectedGame, int expectedTotalScore, bool expectedOver)
        {
            var frames = Regex.Matches(expectedGame, @"\(([^)]*)\)");
            for (var frameIdx = 0; frameIdx < frames.Count; frameIdx++)
            {
                var frame = frames[frameIdx].Groups[0].Value;

                var pinsAndScore = Regex.Matches(frame, @"\d+");
                for (int pinIdx = 0; pinIdx < pinsAndScore.Count - 1; pinIdx++)
                {
                    Assert.AreEqual(pinsAndScore[pinIdx].Value, actualGame.Frames()[frameIdx].PinsRolled[pinIdx].ToString(), $"Frame: {frameIdx} PinsRolled: {pinIdx}");
                }

                Assert.AreEqual(pinsAndScore[pinsAndScore.Count - 1].Value, actualGame.Frames()[frameIdx].Score.ToString(), $"Score in Frame: {frameIdx}");
            }

            Assert.AreEqual(expectedTotalScore, actualGame.TotalScore(), "TotalScore");
            Assert.AreEqual(expectedOver, actualGame.Over(), "Over");
        }
    }
}
