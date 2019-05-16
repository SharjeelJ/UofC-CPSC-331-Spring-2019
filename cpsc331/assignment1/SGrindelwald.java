package cpsc331.assignment1;

public class SGrindelwald
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
                System.out.println(sGrin(n).toString());
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
    protected static Integer sGrin(int n)
    {
        if (n < 0) throw new IllegalArgumentException();
        else if (n == 0) return 1;
        else if (n == 1) return 2;
        else if (n == 2) return 3;
        else if (n == 3) return 4;
        else if ((n % 2) == 0) return 2 * sGrin(n - 1) - 2 * sGrin(n - 3) + sGrin(n - 4);
        else return sGrin(n - 1) + 3 * sGrin(n - 2) - 5 * sGrin(n - 3) + 2 * sGrin(n - 4);
    }
}
