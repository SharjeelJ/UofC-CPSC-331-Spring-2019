/*
Martha Ibarra 30003129
Sharjeel Junaid 30008424
Dheeraj Kumar 30011439
*/
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

    /* Loop Invariant (A):
	(1)  n is an input integer such n >= 4
	(2) An integer array G has a length of n+1
	(3) i is an integer variable such that n +1<= i >= 4
	(4) G[k] = Gk for every integer k such that 0<= k < i
	Refer to the solution for Q7 for the loop invariant proof

    Bound Function
	The function f(n, i) = n-i is a bound function for the fGrin algorithm.
    Precondition states that n>=0.

    Upper bound for the function is (refer to the solution for Q12):
    n = 0 -> 2
    n = 1 -> 3
    n = 2 -> 4
    n = 3 -> 5
    n >= 4 -> 5n*/
    /**
     * Method to compute the n-th Grindelwald number when n >= 0
     *
     * @param n Input integer
     *
     * @return n-th Grindelwald number
     */
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

            /* f(n, i) = n − i is the bound function for the while loop
            Loop test will fail if i > n. If i > n, the value of f(n, i) ≤ 0 since f(n, i) = n-i
            Refer to the solution for Q9 for the while loop bound function proof
            Refer to the solution for Q10 for the proof of termination of the while loop*/
            while (i <= n)
            {
                if (i % 2 == 0) G[i] = 2 * G[i - 1] - 2 * G[i - 3] + G[i - 4];
                else G[i] = G[i - 1] + 3 * G[i - 2] - 5 * G[i - 3] + 2 * G[i - 4];
                i++;
            }
            return G[n];
            // post condition states that the n'th Grindelwald number, G(n), is returned as output.
        }
    }
}
