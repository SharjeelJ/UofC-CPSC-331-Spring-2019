/*
Martha Ibarra 30003129
Sharjeel Junaid 30008424
Dheeraj Kumar 30011439
*/
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

    /*Bound Function
	The function f(n) = n is a bound function for the sGrin algorithm.
    Precondition states that n>=0.

    Recurrence function (refer to the solution for Q4):
    n = 0 -> 2
    n = 1 -> 3
    n = 2 -> 4
    n = 3 -> 5
    n >= 4 and n is even -> 6 + TsGrin(n-1) + TsGrin(n-3) + TsGrin(n-4)
    n >= 4 and n is even -> 6 + TsGrin(n-1) + TsGrin(n-2) + TsGrin(n-3) + TsGrin(n-4)*/
    /**
     * Method to compute the n-th Grindelwald number when n >= 0
     *
     * @param n Input integer
     *
     * @return n-th Grindelwald number
     */
    protected static Integer sGrin(int n)
    {
        if (n < 0) throw new IllegalArgumentException();
        else if (n == 0) return 1;
        else if (n == 1) return 2;
        else if (n == 2) return 3;
        else if (n == 3) return 4;
        else if ((n % 2) == 0) return 2 * sGrin(n - 1) - 2 * sGrin(n - 3) + sGrin(n - 4);
        else return sGrin(n - 1) + 3 * sGrin(n - 2) - 5 * sGrin(n - 3) + 2 * sGrin(n - 4);
        // post condition states that the n'th Grindelwald number, G(n), is returned as output.
    }
}
