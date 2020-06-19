/*
Martha Ibarra 30003129
Sharjeel Junaid 30008424
Dheeraj Kumar 30011439
*/
package cpsc331.assignment3;

import java.util.NoSuchElementException;

public class ArrayUtils<T extends Comparable<T>>
{
    // Initialize a private global array to store a copy of the array based representation of a heap being sorted
    private Array<T> heapArray;

    // Initialize a private global integer to keep track of the heap size for the array based representation of a heap
    private int heapSize = 0;

    // Reference Lecture 18, Slide 4
    /**
     * Method to sort the passed in array in place based representation of a heap using heap sort
     *
     * @param A Input bounded array of elements of type T
     */
    public void sort(Array<T> A)
    {
        // Stores the passed in array to the global variable
        heapArray = A;

        // Sets the starting heap size
        heapSize = 1;

        // Loops through the entire array and clones the pre-existing values at the end of the array
        int i = 1;
        while (i < A.length())
        {
            insert(A.get(i));
            i++;
        }

        // Loops through the entire array and sorts it
        i = A.length() - 1;
        while (i > 0)
        {
            T largest = deleteMax();
            A.set(i, largest);
            i--;
        }
    }

    // Reference Lecture 17, Slide 38
    /**
     * Method to insert in the passed in element to the global array
     *
     * @param key Input element of type T
     *
     * @throws StackOverflowError
     */
    private void insert(T key)
    {
        if (heapSize < heapArray.length())
        {
            int x = heapSize;
            heapArray.set(x, key);
            heapSize = heapSize + 1;
            bubbleUp(x);
        }
        // Stack overflow error is thrown instead of the HeapOverflow error as the latter was an external (non default) java error type
        else throw new StackOverflowError();
    }

    // Reference Lecture 17, Slide 58
    /**
     * Method to delete the max value from the global array and returns it
     *
     * @return Removed max element from the array as type T
     *
     * @throws NoSuchElementException
     */
    private T deleteMax()
    {
        if (heapSize == 0) throw new NoSuchElementException();
        else
        {
            T v = heapArray.get(heapSize - 1);
            heapSize = heapSize - 1;

            if (heapSize == 0) return v;
            else
            {
                T key = heapArray.get(0);
                heapArray.set(0, v);
                bubbleDown(0);
                return key;
            }
        }
    }

    // Reference Lecture 17, Slide 34
    /**
     * Method to bubble up (move up) the passed in index within the global array
     *
     * @param x Input element of type int
     */
    private void bubbleUp(int x)
    {
        if (!isRoot(x) && (value(x).compareTo(value(parent(x))) > 0))
        {
            T tmp = value(x);
            heapArray.set(x, value(parent(x)));
            heapArray.set(parent(x), tmp);
            bubbleUp(parent(x));
        }
    }

    // Reference Lecture 17, Slide 50-51
    /**
     * Method to bubble down (move down) the passed in index within the global array
     *
     * @param x Input element of type int
     */
    private void bubbleDown(int x)
    {
        if (hasRight(x))
        {
            if (value(left(x)).compareTo(value(right(x))) >= 0)
            {
                if (value(left(x)).compareTo(value(x)) > 0)
                {
                    T tmp = value(left(x));
                    heapArray.set(left(x), value(x));
                    heapArray.set(x, tmp);
                    bubbleDown(left(x));
                }
            } else if (value(right(x)).compareTo(value(x)) > 0)
            {
                T tmp = value(right(x));
                heapArray.set(right(x), value(x));
                heapArray.set(x, tmp);
                bubbleDown(right(x));
            }
        } else if (hasLeft(x))
        {
            if (value(left(x)).compareTo(value(x)) > 0)
            {
                T tmp = value(left(x));
                heapArray.set(left(x), value(x));
                heapArray.set(x, tmp);
                bubbleDown(left(x));
            }
        }
    }

    // Reference Lecture 17, Slide 33
    /**
     * Method to check if the passed in array index is the root and returns the result
     *
     * @param x Input array index to check
     *
     * @return Boolean corresponding to if the passed in array index was the root
     */
    private Boolean isRoot(int x)
    {
        return x == 0;
    }

    // Reference Lecture 17, Slide 33
    /**
     * Method to check if the passed in array index is the parent and returns the result
     *
     * @param x Input array index to check
     *
     * @return Boolean corresponding to if the passed in array index was the parent
     *
     * @throws NoSuchElementException
     */
    private int parent(int x)
    {
        if (isRoot(x)) throw new NoSuchElementException();
        else return (int) Math.floor((x - 1) / 2);
    }

    // Reference Lecture 17, Slide 33
    /**
     * Method to get the value of the passed in array index and returns the value
     *
     * @param x Input array index to get
     *
     * @return Value of type T corresponding to the passed in array index
     */
    private T value(int x)
    {
        return heapArray.get(x);
    }

    // Reference Lecture 17, Slide 48
    /**
     * Method to check if the passed in array index has a left child and returns the result
     *
     * @param x Input array index to check
     *
     * @return Boolean corresponding to if the passed in array index has a left child
     *
     * @throws NoSuchElementException
     */
    private boolean hasLeft(int x)
    {
        return 2 * x + 1 < heapSize;
    }

    // Reference Lecture 17, Slide 48
    /**
     * Method to get the left child's index for the passed in array index and returns the index
     *
     * @param x Input array index to get the left child from
     *
     * @return Array index of type int for the left child of the passed in array index
     *
     * @throws NoSuchElementException
     */
    private int left(int x)
    {
        if (hasLeft(x)) return 2 * x + 1;
        else throw new NoSuchElementException();
    }

    // Reference Lecture 17, Slide 49
    /**
     * Method to check if the passed in array index has a right child and returns the result
     *
     * @param x Input array index to check
     *
     * @return Boolean corresponding to if the passed in array index has a right child
     *
     * @throws NoSuchElementException
     */
    private boolean hasRight(int x)
    {
        return 2 * x + 2 < heapSize;
    }

    // Reference Lecture 17, Slide 49
    /**
     * Method to get the right child's index for the passed in array index and returns the index
     *
     * @param x Input array index to get the right child from
     *
     * @return Array index of type int for the right child of the passed in array index
     *
     * @throws NoSuchElementException
     */
    private int right(int x)
    {
        if (hasRight(x)) return 2 * x + 2;
        else throw new NoSuchElementException();
    }
}
