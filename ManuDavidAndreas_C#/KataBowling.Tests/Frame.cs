namespace KataBowling.Tests
{
    public class Frame
    {
        public Frame()
        {
            PinsRolled = new int[3];
        }
        public int[] PinsRolled { get; set; }
        public int Score { get; set; }
    }
}