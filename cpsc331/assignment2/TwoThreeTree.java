/*
Martha Ibarra 30003129
Sharjeel Junaid 30008424
Dheeraj Kumar 30011439
*/
package cpsc331.assignment2;

import cpsc331.collections.ElementFoundException;

import java.util.NoSuchElementException;

/**
 * Provides a 2-3 Tree storing Values from an Ordered Type E.
 */
// 2-3 Tree Invariant: A rooted tree T is represented, so that the
// following 2-3 Tree Properties are satisfied:
//
// a) Each leaf in T stores an element of type E, and the elements
//    stored at the leaves are distinct.
// b) Each internal node in T has either (exactly) two or three
//    children - which are either leaves or internal nodes of T.
// c) If an internal node x of T has exactly two children - a first
//    child and a second child, then every element of E stored at a
//    leaf in the subtree whose root is the first child is less than
//    every element of E stored at a leaf in the subtree whose root
//    is the second child.
// d) If an internal node x of T has exactly three children - a first
//    child, second child and third child, then every element of E
//    stored at a leaf in the subtree whose root is the first child
//    is less than every element of E stored at a leaf in the subtree
//    whose root is the second child, and every element of E stored at
//    a leaf in the subtree whose root is the second child is less than
//    every element of E stored at a leaf in the subtree whose root
//    is the third child.
// e) If an internal node x has exactly two children then the largest
//    elements stored in each of the subtrees whose roots are its
//    children are also stored at x (and are called firstMax
//    and secondMax).
// f) If an internal node x has exactly three children then the largest
//    elements stored in each of the subtrees whose roots are its
//    children are also stored at x (and are called firstMax, secondMax
//    and thirdMax).
// g) Every leaf in T is at the same level, that is, has the same
//    distance from the root of T.
// h) Each node in T is the root of a 2-3 tree as well. That is, the
//    subtree of T with root x also satisfies properties (a)-(g).
public class TwoThreeTree<E extends Comparable<E>>
{
    // Class to create a node in the 2-3 Tree
    class TwoThreeNode
    {

        // Data Fields

        int numberChildren;         // Number of children of this node; an
        // integer between 0 and 4

        E element;                  // Element stored at this node; null
        // if this is not a leaf

        TwoThreeNode firstChild;    // First child
        E firstMax;                 // Largest element stored in first subtree
        // Both are null if this node is a leaf

        TwoThreeNode secondChild;   // Second child
        E secondMax;                // Largest element stored in second subtree
        // Both are null if this node has at most
        // one child

        TwoThreeNode thirdChild;    // Third child
        E thirdMax;                 // Largest element stored in third subtree
        // Both are null if this node has at most
        // two children

        TwoThreeNode fourthChild;   // Fourth child
        E fourthMax;                // Largest element stored in fourth subtree
        // Both are null if this node has at most
        // three children

        TwoThreeNode parent;        // Parent; null if this node is the root
        // of this tree


        // Constructor; constructs a TwoTreeNode with no children or parent,
        // storing null
        TwoThreeNode()
        {
            numberChildren = 0;

            element = null;

            firstChild = null;
            firstMax = null;

            secondChild = null;
            secondMax = null;

            thirdChild = null;
            thirdMax = null;

            fourthChild = null;
            fourthMax = null;

            parent = null;
        }

        // Returns the number of children of this node

        int numberChildren()
        {
            return numberChildren;
        }

        // Returns the element stored at this node if it is a leaf; returns
        // null otherwise.

        E element()
        {
            return element;
        }

        // Returns the first child of this node if it is not a leaf; returns
        // null otherwise

        TwoThreeNode firstChild()
        {
            return firstChild;
        }

        // Returns the largest value stored at the first subtree of this
        // node if it is not a leaf; returns null otherwise

        E firstMax()
        {
            return firstMax;
        }

        // Returns the second child of this node if it has at least two
        // children; returns null otherwise

        TwoThreeNode secondChild()
        {
            return secondChild;
        }

        // Returns the largest value stored at the first subtree of this
        // node if it has at least two children; returns null otherwise

        E secondMax()
        {
            return secondMax;
        }

        // Returns the third child of this node if it has at least three
        // children; returns null otherwise

        TwoThreeNode thirdChild()
        {
            return thirdChild;
        }

        // Returns the largest value stored at the third subtree of this
        // node if it has at least four children; returns null otherwise

        E thirdMax()
        {
            return thirdMax;
        }

        // Returns the fourth child of this node if it has four children;
        // returns null otherwise

        TwoThreeNode fourthChild()
        {
            return fourthChild;
        }

        // Returns the largest value stored at the fourth subtree of this
        // node if it has four chilren; returns null otherwise

        E fourthMax()
        {
            return fourthMax;
        }

        // Returns the parent of this node

        TwoThreeNode parent()
        {
            return parent;
        }
    }


    // Data Fields
    private TwoThreeNode root;

    /**
     * Constructs an empty 2-3 Tree.
     */
    // Precondition: None
    // Postcondition: An empty 2-3 Tree (satisfying the above
    //                2-3 Tree Invariant) has been created.
    public TwoThreeTree()
    {
        root = null;
    }

    // *****************************************************************
    //
    //   Searching in a 2-3 Tree
    //
    // *****************************************************************

    /**
     * Returns a TwoThreeNode with a given key<br>
     *
     * @param key the element to be searched for
     *
     * @return the TwoThreeNode in this 2-3 tree storing the input key
     *
     * @throws NoSuchElementException if the key is not in this tree
     */
    // Precondition::
    // a) This 2-3 Tree satisfies the above 2-3 Tree Properties.
    // b) A non-null key with type E is given as input.
    //
    // Postcondition:
    // a) If the key is stored in this 2-3 tree then the node storing it is
    //    returned as output. A NoSuchElementException is thrown, otherwise.
    // b) This 2-3 Tree has not been changed, so it still satisfies
    //    the 2-3 Tree Properties.
    public TwoThreeNode search(E key) throws NoSuchElementException
    {
        // Checks to see if the root of the tree is null
        if (root() == null)
        {
            // Throws an error if the tree is null
            throw new NoSuchElementException();
        } else
        {
            // Searches for the provided key and returns the node the key is stored at
            return get(key, root());
        }
    }

    //
    // Searches for a given key in the subtree with a given node as root
    //
    // Precondition:
    // a) This 2-3 tree satisfies the above 2-3 Tree Properties.
    // b) key is a non-null input with type E.
    // c) x is a non-null TwoThreeNode in this 2-3 Tree, that is
    //    also given as input.
    //
    // Postcondition:
    // a) If the key is stored in the subtree with root x, then the node
    //    storing the key is returned as output. A NoSuchElementException
    //    is thrown otherwise.
    // b) This 2-3 Tree has not been changed, so it still satisfies
    //    the 2-3 Tree Properties.
    private TwoThreeNode get(E key, TwoThreeNode x) throws NoSuchElementException
    {
        // Checks to see if provided node is a leaf
        if (x.numberChildren() == 0)
        {
            // Checks to see if the leaf contains the key
            if (key.equals(x.element()))
            {
                // Returns the node if it does contain the provided key
                return x;
            } else
            {
                // Throws an error if the leaf did not contain the key
                throw new NoSuchElementException();
            }
        }
        // Checks to see if there are exactly two children under the provided node
        else if (x.numberChildren() == 2)
        {
            // Checks the key against the firstMax to see if key <= x.firstMax
            if (key.compareTo(x.firstMax()) <= 0)
            {
                // Searches the subtree under the 1st child since key <= x.firstMax so it might be under it
                return get(key, x.firstChild());
            } else
            {
                // Searches the subtree under the 2nd child since key > x.firstMax so it might be under it
                return get(key, x.secondChild());
            }
        }
        // Code called if the provided node has 1 / 3 children
        else
        {
            // Checks the key against the firstMax to see if key <= x.firstMax
            if (key.compareTo(x.firstMax()) <= 0)
            {
                // Searches the subtree under the 1st child since key <= x.firstMax so it might be under it
                return get(key, x.firstChild());
            }
            // Checks the key against the secondMax to see if key <= x.secondMax
            else if (key.compareTo(x.secondMax()) <= 0)
            {
                // Searches the subtree under the 2nd child since key <= x.secondMax so it might be under it
                return get(key, x.secondChild());
            } else
            {
                // Searches the subtree under the 3rd child since key > x.secondMax so it might be under it
                return get(key, x.thirdChild());
            }
        }
    }

    // ********************************************************************
    //
    //   Insertions in a 2-3 Tree
    //
    // ********************************************************************

    // The following "Modified Tree" properties are satisfied at the
    // beginning or end of private methods called by public ones provided
    // by this 2-3 tree.
    //
    // a) This tree, T, satisfies 2-3 Tree properties (a), (c), (d), (e),
    //    (f), and (g) - but not, necessarily, 2-3 Tree properties (b)
    //    or (h).
    // b) Every internal node of T has (exactly) either one, two, three
    //    or four children - which are each either leaves or internal nodes
    //    of T. There is at most one internal node of T that has (exactly)
    //    either one or four children - all other internal nodes of T have
    //    either exactly two or three children.
    // c) If an internal node x of T has exactly one child, then this is
    //    called a first child, which is the root of a first subtree of the
    //    subtree with root x. In this case, the largest element of E stored
    //    in a leaf of the first subtree is stored at x, as the value of
    //    x.firstMax.
    // d) If an internal node x of T has four children - a first child, second
    //    child, third child and fourth child, which are the roots of the
    //    first subtree, second subtree, third subtree and fourth subtree of
    //    the subtree of T with root x, respectively - then each of the values
    //    stored at leaves of the first subtree is less than each of the values
    //    stored at leaves of the second subtree, each of the values stored at
    //    leaves of the second subtree is less than each of the values stored
    //    at leaves of the third subtree, and each of the values stored at
    //    leaves of the third subtree is less than values stored at leaves of
    //    the fourth subtree.
    // e) If an internal node x of T has exactly four children then the
    //    largest values stored at the leaves of the first, second, third and
    //    fourth subtrees of the subtree with root x are stored at x - and are
    //    called firstMax, secondMax, thirdMax and fourthMax, respectively.
    // f) Each node x of T is the root of a rooted tree satisfying these
    //    properties as well. That is, the subtree with root x also satisfies
    //    the above properties (a)-(e), for eery node x in T.

    /**
     * Inserts an input key into this 2-3 Tree
     *
     * @param key the key to be inserted into this 2-3 Tree
     *
     * @throws ElementFoundException if the input key is already stored
     *                               in this tree
     */

    // Precondition:
    // a) This 2-3 Tree, T, satisfies the above 2-3 Tree Properties.
    // b) key is a non-null input of type E.
    //
    // Postcondition:
    // a) If the input key is not already included in the subset of E
    //    represented by T then it is added to this subset (with this subset
    //    being otherwise unchanged). An ElementFoundException is thrown and
    //    the set is not changed, if the key already belongs to this subset.
    // b) T satisfies the 2-3 Tree properties given above.
    public void insert(E key) throws ElementFoundException
    {
        // Checks to see if the provided key exists in the tree
        try
        {
            // Searches the tree for the provided key's existence
            if (search(key) != null)
            {
                // Throws an ElementFoundException since the search for the provided key was successful
                throw new ElementFoundException("");
            }
        }
        // Code run if the element does not currently exist in the tree
        catch (NoSuchElementException e)
        {
            // Checks to see if the root of the tree is null
            if (root() == null)
            {
                // Adds the provided key as a node so the tree consists of 1 leaf (itself)
                addFirstElement(key);
            }
            // Checks to see if the root of the tree is a leaf (not null)
            else if (root().numberChildren() == 0)
            {
                // Adds the provided key as a child of the root (now the root is an internal node with a single child)
                addSecondElement(key);
            } else
            {
                // Inserts the provided key as a node in the relevant spot in the entirety of the tree (starting from the root)
                insertIntoSubtree(key, root());

                // Checks to see if the root has 4 children
                if (root().numberChildren() == 4)
                {
                    // Fixes the root by creating two children that will each share 2 of the original children (root will not have 2 children)
                    fixRoot();
                }
            }
        }
    }

    // Inserts an input key into this 2-3 Treee when it is Empty
    //
    // Precondition:
    // a) This 2-3 Tree, T, satisfies the above 2-3 tree properties - and
    //    is empty.
    // b) key is a non-null input with type E.
    //
    // Postcondition:
    // a) The input key has been added to the subset of E represented by T,
    //    which is otherwise unchanged.
    // b) T satisfies the 2-3 Tree properties given above.
    private void addFirstElement(E key)
    {
        // Creates a new node that will be used as the root node
        root = new TwoThreeNode();

        // Stores the passed in key as the value at the root node (now the tree consists of a leaf where the root is the only node)
        root().element = key;
    }

    // Inserts an input key into this 2-3 tree when the root of this tree
    // is a leaf
    //
    // Precondition:
    // a) This 2-3 Tree, T, satisfies the 2-3 Tree properties - and the
    //    root of this tree is a leaf, so that T represents a subset of E
    //    with size one.
    // b) key is a non-null input with type E.
    //
    // Postcondition:
    // a) If the input key does not belong to the subset of E represented
    //    by T then they key is added to this subset - which is otherwise
    //    unchanged. If the key does already belong to this subset and an
    //    ElementFoundException is thrown and T is not changed.
    // b) T satisfies the 2-3 Tree properties given above.
    private void addSecondElement(E key) throws ElementFoundException
    {
        try
        {
            // Searches the tree for the provided key's existence
            if (search(key) != null)
            {
                // Throws an ElementFoundException since the search for the provided key was successful
                throw new ElementFoundException("");
            }
        } catch (NoSuchElementException e)
        {
            // Creates two new nodes that will be used for the left and right node
            root().firstChild = new TwoThreeNode();
            root().secondChild = new TwoThreeNode();

            // Sets the parent node of the left and right subtree to the root node
            root().firstChild().parent = root();
            root().secondChild().parent = root();

            // Updates the number of children for the root node
            root().numberChildren = 2;

            // Checks to see if the passed in key is greater than the current root value (root().element < key) and will make the key the right subtree if it is
            if (root().element().compareTo(key) < 0)
            {
                // Stores the old root value in the left subtree and the passed in key value in the right subtree
                root().firstChild().element = root().element();
                root().secondChild().element = key;

                // Stores the respective first and second max values
                root().firstMax = root().element();
                root().secondMax = key;
            }
            // The passed in key was smaller than the current root value (root().element > key) and makes the key the left subtree
            else
            {
                // Stores the old root value in the right subtree and the passed in key value in the left subtree
                root().firstChild().element = key;
                root().secondChild().element = root().element();

                // Stores the respective first and second max values
                root().firstMax = key;
                root().secondMax = root().element();
            }

            // Sets the root node to null as it is no longer a leaf node (has 2 children)
            root().element = null;
        }
    }

    // Inserts a given key into the subtree of T with a given node x
    // as root if x is an internal node; throws an exception to aid the
    // inclusion of the input key if x is a leaf
    //
    // Precondition:
    // a) This 2-3 tree, T, satisfies the 2-3 Tree properties given above.
    // b) key is a non-null input with type E.
    // c) x is a non-null node in T.
    // d) Either key is not stored at any leaf in T or it is stored in a
    //    leaf of the subtree of T with root x.
    //
    // Postcondition:
    // a) If the input key already belongs to the subset of E stored at
    //    the leaves in the subtree of T with root x, then an
    //    ElementFoundException is thrown and T is not changed.
    // b) If x is a leaf that stores an element of E that is not equal
    //    to the input key then a NoSuchElementException is thrown and
    //    T is not changed.
    // c) If x is an internal node and the input key does not (initially)
    //    belong to the subset of E stored at the leaves in the subtree
    //    of T with root x, then
    //    - the input key is added to the subset of E stored at the leaves
    //      of T - which is otherwise unchanged;
    //    - either T satisfies the 2-3 Tree properties, given above, or
    //      T satisfies the "Modified Tree" properties, given above, and
    //      x is now an internal node with four children.
    private void insertIntoSubtree(E key, TwoThreeNode x) throws ElementFoundException, NoSuchElementException
    {
        // Checks to see if the passed in node is a leaf
        if (x.numberChildren() == 0)
        {
            // Stores the passed in node's element
            E e = x.element();

            // Checks to see if the passed in node's element is equal to the passed in key
            if (e.equals(key))
            {
                // Throws an error since the key already exists in the current leaf
                throw new ElementFoundException("");
            } else
            {
                // Throws an error since the key does not already exist under the current node
                throw new NoSuchElementException();
            }
        } else
        {
            try
            {
                // Checks to see if the passed in node has 2 children
                if (x.numberChildren() == 2)
                {
                    // Checks if the passed in key is less than or equal to the passed in node's firstMax (key <= x.firstMax)
                    if (key.compareTo(x.firstMax()) <= 0)
                    {
                        // Recurse deeper into the first child's subtree
                        insertIntoSubtree(key, x.firstChild());
                    } else
                    {
                        // Recurse deeper into the second child's subtree since key > x.firstMax
                        insertIntoSubtree(key, x.secondChild());
                    }
                } else
                {
                    // Checks if the passed in key is less than or equal to the passed in node's firstMax (key <= x.firstMax)
                    if (key.compareTo(x.firstMax()) <= 0)
                    {
                        // Recurse deeper into the first child's subtree
                        insertIntoSubtree(key, x.firstChild());
                    }
                    // Checks if the passed in key is less than or equal to the passed in node's secondMax (key <= x.secondMax)
                    else if (key.compareTo(x.secondMax()) <= 0)
                    {
                        // Recurse deeper into the second child's subtree
                        insertIntoSubtree(key, x.secondChild());
                    } else
                    {
                        // Recurse deeper into the third child's subtree since key > x.secondMax
                        insertIntoSubtree(key, x.thirdChild());
                    }
                }
                // Calls the method to raise the surplus and handle nodes with 4 children that are not the root (moves them closer to the root)
                raiseSurplus(x);
            } catch (NoSuchElementException ex)
            {
                // Adds the passed in key as a leaf of the passed in node since it does not already exist
                addLeaf(key, x);
            }
        }
    }

    // Brings a node with four children closer to the root, if one exists
    // in this modified tree
    //
    // Precondition:
    // a) This tree, T, satisfies the "Modified Tree" properties given above.
    // b) x is an internal node of T whose children are also internal nodes
    //    in T.
    // c) Either T is a 2-3 tree (that is, it satisfies the above "2-3 Tree"
    //    properties), or one of the children of x has four children.
    //
    // Postcondition:
    // a) The subset of E stored at the leaves of T has not been changed.
    // b) Either T is a 2-3 tree (that is, it satisfies the above "2-3 Tree"
    //    properties), or T satisfies the "Modified Tree" properties and x
    //    has four children.
    private void raiseSurplus(TwoThreeNode x)
    {
        // Loops through all the children of the provided node
        for (int counter = 1; counter <= x.numberChildren(); counter++)
        {
            // Calls different code blocks depending on which child is being examined
            switch (counter)
            {
                // Child 1
                case 1:
                    if (x.firstChild().numberChildren() == 4)
                    {
                        // Creates a new node that will be modified and later reflected onto the live node
                        TwoThreeNode newNode = new TwoThreeNode();

                        // Stores a fresh nodes at the 1st and 2nd child and the original children of x at the 3rd and 4th child
                        newNode.firstChild = new TwoThreeNode();
                        newNode.secondChild = new TwoThreeNode();
                        newNode.thirdChild = x.secondChild();
                        newNode.fourthChild = x.thirdChild();

                        // Stores the old 4 children of x in their new spots
                        newNode.firstChild().firstChild = x.firstChild().firstChild();
                        newNode.firstChild().secondChild = x.firstChild().secondChild();
                        newNode.secondChild().firstChild = x.firstChild().thirdChild();
                        newNode.secondChild().secondChild = x.firstChild().fourthChild();

                        // Sets the parent nodes of the left and right subtrees
                        newNode.parent = x.parent();
                        newNode.firstChild().parent = newNode;
                        newNode.secondChild().parent = newNode;
                        newNode.firstChild().firstChild().parent = newNode.firstChild();
                        newNode.firstChild().secondChild().parent = newNode.firstChild();
                        newNode.secondChild().firstChild().parent = newNode.secondChild();
                        newNode.secondChild().secondChild().parent = newNode.secondChild();

                        // Updates the new node's max values to reflect the new children
                        newNode.firstMax = x.firstChild.secondMax();
                        newNode.secondMax = x.firstChild().fourthMax();
                        newNode.thirdMax = x.secondMax();
                        newNode.fourthMax = x.thirdMax();

                        // Updates the new node's children max values to reflect the new children
                        newNode.firstChild().firstMax = x.firstChild().firstMax();
                        newNode.firstChild().secondMax = x.firstChild().secondMax();
                        newNode.secondChild().firstMax = x.firstChild().thirdMax();
                        newNode.secondChild().secondMax = x.firstChild().fourthMax();

                        // Updates the number of children under the new node and new children of the node
                        newNode.numberChildren = x.numberChildren() + 1;
                        newNode.firstChild().numberChildren = 2;
                        newNode.secondChild().numberChildren = 2;


                        // Syncs over the changes to the children of the live node x
                        x.firstChild = newNode.firstChild();
                        x.secondChild = newNode.secondChild();
                        x.thirdChild = newNode.thirdChild();
                        x.fourthChild = newNode.fourthChild();


                        // Syncs over the updated max values to the live node x
                        x.firstMax = newNode.firstMax();
                        x.secondMax = newNode.secondMax();
                        x.thirdMax = newNode.thirdMax();
                        x.fourthMax = newNode.fourthMax();

                        try
                        {
                            // Syncs over the updated parent values to the live node x and its children
                            x.parent = newNode.parent;
                            x.firstChild().parent = x;
                            x.secondChild().parent = x;
                            x.thirdChild().parent = x;
                            x.fourthChild().parent = x;
                        } catch (NullPointerException e)
                        {

                        }

                        // Syncs over the updated number of children to the live node x
                        x.numberChildren = newNode.numberChildren();
                    }
                    break;
                // Child 2
                case 2:
                    if (x.secondChild().numberChildren() == 4)
                    {
                        // Creates a new node that will be modified and later reflected onto the live node
                        TwoThreeNode newNode = new TwoThreeNode();

                        // Stores fresh nodes at the 2nd and 3rd child and the original children of x at the 1st and 4th child
                        newNode.firstChild = x.firstChild();
                        newNode.secondChild = new TwoThreeNode();
                        newNode.thirdChild = new TwoThreeNode();
                        newNode.fourthChild = x.thirdChild();

                        // Stores the old 4 children of x in their new spots
                        newNode.secondChild().firstChild = x.secondChild().firstChild();
                        newNode.secondChild().secondChild = x.secondChild().secondChild();
                        newNode.thirdChild().firstChild = x.secondChild().thirdChild();
                        newNode.thirdChild().secondChild = x.secondChild().fourthChild();

                        // Sets the parent nodes of the left and right subtrees
                        newNode.parent = x.parent();
                        newNode.secondChild().parent = newNode;
                        newNode.thirdChild().parent = newNode;
                        newNode.firstChild().firstChild().parent = newNode.firstChild();
                        newNode.firstChild().secondChild().parent = newNode.firstChild();
                        newNode.secondChild().firstChild().parent = newNode.secondChild();
                        newNode.secondChild().secondChild().parent = newNode.secondChild();

                        // Updates the new node's max values to reflect the new children
                        newNode.firstMax = x.firstMax();
                        newNode.secondMax = x.secondChild().secondMax();
                        newNode.thirdMax = x.secondChild().fourthMax();
                        newNode.fourthMax = x.thirdMax();

                        // Updates the new node's children max values to reflect the new children
                        newNode.secondChild().firstMax = x.secondChild().firstMax();
                        newNode.secondChild().secondMax = x.secondChild().secondMax();
                        newNode.thirdChild().firstMax = x.secondChild().thirdMax();
                        newNode.thirdChild().secondMax = x.secondChild().fourthMax();

                        // Updates the number of children under the new node and new children of the node
                        newNode.numberChildren = x.numberChildren() + 1;
                        newNode.secondChild().numberChildren = 2;
                        newNode.thirdChild().numberChildren = 2;

                        // Syncs over the changes to the children of the live node x
                        x.firstChild = newNode.firstChild();
                        x.secondChild = newNode.secondChild();
                        x.thirdChild = newNode.thirdChild();
                        x.fourthChild = newNode.fourthChild();


                        // Syncs over the updated max values to the live node x
                        x.firstMax = newNode.firstMax();
                        x.secondMax = newNode.secondMax();
                        x.thirdMax = newNode.thirdMax();
                        x.fourthMax = newNode.fourthMax();

                        try
                        {
                            // Syncs over the updated parent values to the live node x and its children
                            x.parent = newNode.parent;
                            x.firstChild().parent = x;
                            x.secondChild().parent = x;
                            x.thirdChild().parent = x;
                            x.fourthChild().parent = x;
                        } catch (NullPointerException e)
                        {

                        }

                        // Syncs over the updated number of children to the live node x
                        x.numberChildren = newNode.numberChildren();
                    }
                    break;
                // Child 3
                case 3:
                    if (x.thirdChild().numberChildren() == 4)
                    {
                        // Creates a new node that will be modified and later reflected onto the live node
                        TwoThreeNode newNode = new TwoThreeNode();

                        // Stores a fresh nodes at the 3rd and 4th child and the original children of x at the 1st and 2nd child
                        newNode.firstChild = x.firstChild();
                        newNode.secondChild = x.secondChild();
                        newNode.thirdChild = new TwoThreeNode();
                        newNode.fourthChild = new TwoThreeNode();

                        // Stores the old 4 children of x in their new spots
                        newNode.thirdChild().firstChild = x.thirdChild().firstChild();
                        newNode.thirdChild().secondChild = x.thirdChild().secondChild();
                        newNode.fourthChild().firstChild = x.thirdChild().thirdChild();
                        newNode.fourthChild().secondChild = x.thirdChild().fourthChild();

                        // Sets the parent nodes of the left and right subtrees
                        newNode.parent = x.parent();
                        newNode.thirdChild().parent = newNode;
                        newNode.fourthChild().parent = newNode;
                        newNode.thirdChild().firstChild().parent = newNode.thirdChild();
                        newNode.thirdChild().secondChild().parent = newNode.thirdChild();
                        newNode.fourthChild().firstChild().parent = newNode.fourthChild();
                        newNode.fourthChild().secondChild().parent = newNode.fourthChild();

                        // Updates the new node's max values to reflect the new children
                        newNode.firstMax = x.firstMax();
                        newNode.secondMax = x.secondMax();
                        newNode.thirdMax = x.thirdChild().secondMax();
                        newNode.fourthMax = x.thirdChild().fourthMax();

                        // Updates the new node's children max values to reflect the new children
                        newNode.thirdChild().firstMax = x.thirdChild().firstMax();
                        newNode.thirdChild().secondMax = x.thirdChild().secondMax();
                        newNode.fourthChild().firstMax = x.thirdChild().thirdMax();
                        newNode.fourthChild().secondMax = x.thirdChild().fourthMax();

                        // Updates the number of children under the new node and new children of the node
                        newNode.numberChildren = x.numberChildren() + 1;
                        newNode.thirdChild().numberChildren = 2;
                        newNode.fourthChild().numberChildren = 2;

                        // Syncs over the changes to the children of the live node x
                        x.firstChild = newNode.firstChild();
                        x.secondChild = newNode.secondChild();
                        x.thirdChild = newNode.thirdChild();
                        x.fourthChild = newNode.fourthChild();


                        // Syncs over the updated max values to the live node x
                        x.firstMax = newNode.firstMax();
                        x.secondMax = newNode.secondMax();
                        x.thirdMax = newNode.thirdMax();
                        x.fourthMax = newNode.fourthMax();

                        try
                        {
                            // Syncs over the updated parent values to the live node x and its children
                            x.parent = newNode.parent;
                            x.firstChild().parent = x;
                            x.secondChild().parent = x;
                            x.thirdChild().parent = x;
                            x.fourthChild().parent = x;
                        } catch (NullPointerException e)
                        {

                        }

                        // Syncs over the updated number of children to the live node x
                        x.numberChildren = newNode.numberChildren();
                    }
                    break;
            }
        }
    }

    // Adds a leaf storing a given value as a child of a given
    // internal node
    //
    // Precondition:
    // a) This tree, T,is a 2-3 tree (that is,it satisfies the 2-3 Tree
    //    properties given above).
    // b) x is an input internal node in T whose children are leaves.
    // c) key is a non-null input element of E that is not in the set of
    //    elements of E stored at leaves of T.
    // d) It is possible to produce a tree satisfying the "Modified Tree"
    //    properties, given above, by adding a leaf storing the input key
    //    as a child of x.
    //
    // Postcondition:
    // a) The input key has been added to the set of elements stored at the
    //    leaves of T, which is otherwise unchanged.
    // b) Either T is a 2-3 Tree or T satisfies the above "Modified Tree"
    //    properties and x has four children.
    private void addLeaf(E key, TwoThreeNode x)
    {
        // Checks to see if the passed in node has 0 children and adds the passed in key as a 1st child
        if (x.numberChildren() == 0)
        {
            // Creates a new node that will be the 1st child of x
            x.firstChild = new TwoThreeNode();

            // Sets the parent node of the new node
            x.firstChild().parent = root();

            // Updates the number of children for x
            x.numberChildren = 1;

            // Stores the passed in key in the new child node
            x.firstChild().element = key;

            // Stores the new 1st max in x
            x.firstMax = key;
        }
        // Checks to see if the passed in node has 1 child and adds the passed in key
        else if (x.numberChildren() == 1)
        {
            // Checks to see if the current first child is less than the key (x.firstChild < key)
            if (x.firstChild().element().compareTo(key) < 0)
            {
                // Creates a new node that will be the 2nd child of x
                x.secondChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.secondChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 2;

                // Stores the passed in key in the new child node
                x.secondChild().element = key;

                // Stores the new 2nd max in x
                x.secondMax = key;
            }
            // Code run if x.firstChild > key
            else
            {
                // Shifts the relevant existing nodes and max values to the right to accommodate for the new node on the left
                x.secondChild = x.firstChild();
                x.secondMax = x.firstMax();

                // Creates a new node that will be the 1st child of x
                x.firstChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.firstChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 2;

                // Stores the passed in key in the new child node
                x.firstChild().element = key;

                // Stores the new 1st max in x
                x.firstMax = key;
            }
        }
        // Checks to see if the passed in node has 2 children and adds the passed in key
        if (x.numberChildren() == 2)
        {
            // Checks to see if the current second child is less than the key (x.secondChild < key)
            if (x.secondChild().element().compareTo(key) < 0)
            {
                // Creates a new node that will be the 3rd child of x
                x.thirdChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.thirdChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 3;

                // Stores the passed in key in the new child node
                x.thirdChild().element = key;

                // Stores the new 3rd max in x
                x.thirdMax = key;
            }
            // Checks to see if the current first child is less than the key (x.firstChild < key)
            else if (x.firstChild().element().compareTo(key) < 0)
            {
                // Shifts the relevant existing nodes and max values to the right to accommodate for the new node on the left
                x.thirdChild = x.secondChild();
                x.thirdMax = x.secondMax();

                // Creates a new node that will be the 2nd child of x
                x.secondChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.secondChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 3;

                // Stores the passed in key in the new child node
                x.secondChild().element = key;

                // Stores the new 2nd max in x
                x.secondMax = key;
            }
            // Code run if x.firstChild > key
            else
            {
                // Shifts the relevant existing nodes and max values to the right to accommodate for the new node on the left
                x.thirdChild = x.secondChild();
                x.thirdMax = x.secondMax();
                x.secondChild = x.firstChild();
                x.secondMax = x.firstMax();

                // Creates a new node that will be the 1st child of x
                x.firstChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.firstChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 3;

                // Stores the passed in key in the new child node
                x.firstChild().element = key;

                // Stores the new 1st max in x
                x.firstMax = key;
            }
        }
        // Checks to see if the passed in node has 3 children and adds the passed in key
        else if (x.numberChildren() == 3)
        {
            // Checks to see if the current third child is less than the key (x.thirdChild < key)
            if (x.thirdChild().element().compareTo(key) < 0)
            {
                // Creates a new node that will be the 4th child of x
                x.fourthChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.fourthChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 4;

                // Stores the passed in key in the new child node
                x.fourthChild().element = key;

                // Stores the new 4th max in x
                x.fourthMax = key;
            }
            // Checks to see if the current second child is less than the key (x.secondChild < key)
            else if (x.secondChild().element().compareTo(key) < 0)
            {
                // Shifts the relevant existing nodes and max values to the right to accommodate for the new node on the left
                x.fourthChild = x.thirdChild();
                x.fourthMax = x.thirdMax();

                // Creates a new node that will be the 3rd child of x
                x.thirdChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.thirdChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 4;

                // Stores the passed in key in the new child node
                x.thirdChild().element = key;

                // Stores the new 3rd max in x
                x.thirdMax = key;
            }
            // Checks to see if the current first child is less than the key (x.firstChild < key)
            else if (x.firstChild().element().compareTo(key) < 0)
            {
                // Shifts the relevant existing nodes and max values to the right to accommodate for the new node on the left
                x.fourthChild = x.thirdChild();
                x.fourthMax = x.thirdMax();
                x.thirdChild = x.secondChild();
                x.thirdMax = x.secondMax();

                // Creates a new node that will be the 2nd child of x
                x.secondChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.secondChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 4;

                // Stores the passed in key in the new child node
                x.secondChild().element = key;

                // Stores the new 2nd max in x
                x.secondMax = key;
            }
            // Code run if x.firstChild > key
            else
            {
                // Shifts the relevant existing nodes and max values to the right to accommodate for the new node on the left
                x.fourthChild = x.thirdChild();
                x.fourthMax = x.thirdMax();
                x.thirdChild = x.secondChild();
                x.thirdMax = x.secondMax();
                x.secondChild = x.firstChild();
                x.secondMax = x.firstMax();

                // Creates a new node that will be the 1st child of x
                x.firstChild = new TwoThreeNode();

                // Sets the parent node of the new node
                x.firstChild().parent = root();

                // Updates the number of children for x
                x.numberChildren = 4;

                // Stores the passed in key in the new child node
                x.firstChild().element = key;

                // Stores the new 1st max in x
                x.firstMax = key;
            }
        }
    }

    // Completes the restoration of a 2-3 tree after the
    // "insertIntoSubtree" method has applied and the root has four
    // children
    //
    // Precondition:
    // a) T is a rooted tree, satisfying the above "Modified Tree"
    //    properties, whose root is an internal node with four children.
    //
    // Postcondition:
    // a) The subset of E represented by T has not been changed.
    // b) T now satisfies the "2-3 Tree" properties given above.
    private void fixRoot()
    {
        // Creates a new node that will be used for the root node
        TwoThreeNode newRootNode = new TwoThreeNode();

        // Creates two new nodes that will be used for the left and right node
        newRootNode.firstChild = new TwoThreeNode();
        newRootNode.secondChild = new TwoThreeNode();

        // Stores the old 4 children of root in their new spots
        newRootNode.firstChild().firstChild = root().firstChild();
        newRootNode.firstChild().secondChild = root().secondChild();
        newRootNode.secondChild().firstChild = root().thirdChild();
        newRootNode.secondChild().secondChild = root().fourthChild();

        // Sets the parent nodes of the left and right subtrees
        newRootNode.firstChild().parent = newRootNode;
        newRootNode.secondChild().parent = newRootNode;
        newRootNode.firstChild().firstChild().parent = newRootNode.firstChild();
        newRootNode.firstChild().secondChild().parent = newRootNode.firstChild();
        newRootNode.secondChild().firstChild().parent = newRootNode.secondChild();
        newRootNode.secondChild().secondChild().parent = newRootNode.secondChild();

        // Updates the root's max values to reflect the new children
        newRootNode.firstMax = root().secondMax();
        newRootNode.secondMax = root().fourthMax();

        // Updates the new internal nodes (children of root) max values to reflect the moved original children
        newRootNode.firstChild().firstMax = root().firstMax();
        newRootNode.firstChild().secondMax = root().secondMax();
        newRootNode.secondChild().firstMax = root().thirdMax();
        newRootNode.secondChild().secondMax = root().fourthMax();

        // Updates the number of children under the root and new children of root
        newRootNode.numberChildren = 2;
        newRootNode.firstChild().numberChildren = 2;
        newRootNode.secondChild().numberChildren = 2;

        // Updates the root node to reflect the changes made
        root = newRootNode;
    }

    // *****************************************************************
    //
    //   Deletions from a 2-3 Tree
    //
    // *****************************************************************

    /**
     * Removes an input key from this 2-3 Tree
     *
     * @param key the key to be removed from this 2-3 Tree
     *
     * @throws NoSuchElementException if the input key is not already
     *                                stored in this tree
     */

    // Precondition:
    // a) This 2-3 Tree, T, satisfies the above 2-3 Tree Properties.
    // b) key is a non-null input of type E.
    //
    // Postcondition:
    // a) If the input key is included in the subset of E represented
    //    by T then it is removed from this subset (with this subset
    //    being otherwise unchanged). A NoSuchElementException  is thrown
    //    and the set is not changed, if the key already belongs to
    //    this subset.
    // b) T satisfies the 2-3 Tree properties given above.
    public void delete(E key) throws NoSuchElementException
    {
        // Performs a search of the tree to see if the provided key exists (will throw NoSuchElementException if it doesnt exist) and stores the node
        TwoThreeNode nodeToDelete = search(key);
        //        System.out.println(nodeToDelete.element());
        //        nodeToDelete.element = null;
        //        nodeToDelete.parent().numberChildren--;
        //        System.out.println(nodeToDelete.element());
        removeLeaf(key, nodeToDelete);
    }

    private void removeLeaf(E key, TwoThreeNode x)
    {
        if (x.parent().numberChildren() == 1)
        {
            x.element = null;
            x.parent = null;
        } else if (x.parent().numberChildren() == 2)
        {
            if (x.parent().firstChild().element().equals(key))
            {
                x.parent().firstChild = x.parent().secondChild();
                x.parent().firstMax = x.parent().secondMax();
                x.parent().secondChild = null;
                x.parent().secondMax = null;
                x.parent().numberChildren = 1;
            } else
            {
                x.parent().secondChild = null;
                x.parent().secondMax = null;
                x.parent().numberChildren = 1;
            }
        } else if (x.parent().numberChildren() == 3)
        {
            if (x.parent().firstChild().element().equals(key))
            {
                x.parent().firstChild = x.parent().secondChild();
                x.parent().secondChild = x.parent().thirdChild();
                x.firstMax = x.parent().secondMax();
                x.secondMax = x.parent().thirdMax();
                x.parent().thirdMax = null;
                x.parent().thirdChild = null;
                x.parent().numberChildren = 2;
            }
        }
    }

    // *****************************************************************
    //
    //   Additional Code for Testing
    //
    // *****************************************************************

    // Returns a reference to the root of this 2-3 Tree
    TwoThreeNode root()
    {
        return root;
    }

}
