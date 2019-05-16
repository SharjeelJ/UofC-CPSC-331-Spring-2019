package cpsc331.assignment1;

public class FGrindelwald
{
    public static void main(String[] args)
    {
        // Checks to see if no parameter or more than one parameter has been passed in
        if (args.length == 0 || args.length > 1) System.out.println("Gadzooks! One integer input is required.");
            // Code run if exactly one parameter has been passed in
        else
        {
            // Attempts to parse in the provided parameter as an integer
            try
            {
                int n = Integer.parseInt(args[0]);
                System.out.println(fGrin(n).toString());
            }
            // Error message printed out if the input parameter is not an integer
            catch (NumberFormatException e)
            {
                System.out.println("Gadzooks! One integer input is required.");
            }
            // Error message printed out if the input parameter is a negative integer
            catch (IllegalArgumentException e)
            {
                System.out.println("Gadzooks! The integer input cannot be negative.");
            }
        }
    }

    // Method to compute the n-th Grindelwald number when n >= 0
    protected static Integer fGrin(int n)
    {
        if (n < 0) throw new IllegalArgumentException();
        else if (n == 0) return 1;
        else if (n == 1) return 2;
        else if (n == 2) return 3;
        else if (n == 3) return 4;
        else
        {
            int[] G = new int[n + 1];
            G[0] = 1;
            G[1] = 2;
            G[2] = 3;
            G[3] = 4;
            int i = 4;

            while (i <= n)
            {
                if (i % 2 == 0) G[i] = 2 * G[i - 1] - 2 * G[i - 3] + G[i - 4];
                else G[i] = G[i - 1] + 3 * G[i - 2] - 5 * G[i - 3] + 2 * G[i - 4];
                i++;
            }
            return G[n];
        }
    }
}
