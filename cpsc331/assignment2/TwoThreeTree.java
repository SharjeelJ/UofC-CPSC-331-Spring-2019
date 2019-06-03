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
    // TODO
    public TwoThreeNode search(E key) throws NoSuchElementException
    {
        if (root() == null) throw new NoSuchElementException();
        else return get(key, root());
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

    // TODO
    private TwoThreeNode get(E key, TwoThreeNode x) throws NoSuchElementException
    {
        if (x.numberChildren() == 0)
        {
            if (key.equals(x.element())) return x;
            else throw new NoSuchElementException();
        } else if (x.numberChildren() == 2)
        {
            key.compareTo(x.firstMax());
            if (key.compareTo(x.firstMax()) <= 0) return get(key, x.firstChild());
            else return get(key, x.secondChild());
        } else
        {
            if (key.compareTo(x.firstMax()) <= 0) return get(key, x.firstChild());
            else if (key.compareTo(x.secondMax()) <= 0) return get(key, x.secondChild());
            else return get(key, x.thirdChild());
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
    // TODO
    public void insert(E key) throws ElementFoundException
    {
        // Checks to see if the current element exists in the tree
        try
        {
            // Searches the tree for the current element's existence and throws an ElementFoundException if the search is successful
            if (search(key) != null) throw new ElementFoundException("");
        }
        // Code run if the element does not currently exist in the tree
        catch (NoSuchElementException e)
        {
            if (root() == null) addFirstElement(key);
            else if (root().numberChildren() == 0) addSecondElement(key);
            else
            {
                insertIntoSubtree(key, root());
                if (root().numberChildren() == 4) fixRoot();
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

        // Stores the passed in key as the value at the root node (currently a leaf where the tree only has a single node)
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
    // a) Id the input key does not belong to the subset of E represented
    //    by T then they key is added to this subset - which is otherwise
    //    unchanged. If the key does already belong to this subset and an
    //    ElementFoundException is thrown and T is not changed.
    // b) T satisfies the 2-3 Tree properties given above.

    private void addSecondElement(E key) throws ElementFoundException
    {
        // Creates two new nodes that will be used for the left and right subtree
        root().firstChild = new TwoThreeNode();
        root().secondChild = new TwoThreeNode();

        // Sets the parent node of the left and right subtree to the root node
        root().firstChild().parent = root();
        root().secondChild().parent = root();

        // Updates the number of children for the root node
        root().numberChildren = 2;

        // Checks to see if the passed in key is less than the current root value and will make the key the left subtree if it is
        if (root().element().compareTo(key) < 0)
        {
            // Stores the old root value in the left subtree and the passed in key value in the right subtree
            root().firstChild().element = root().element();
            root().secondChild().element = key;

            // Stores the respective first and second max values
            root().firstMax = root().element();
            root().secondMax = key;
        }
        // The passed in key was greater than the current root value and makes the key the right subtree
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

    // TODO
    private void insertIntoSubtree(E key, TwoThreeNode x) throws ElementFoundException, NoSuchElementException
    {
        if (x.numberChildren() == 0)
        {
            E e = x.element();
            if (e.equals(key)) throw new ElementFoundException("");
            else throw new NoSuchElementException();
        } else
        {
            try
            {
                if (x.numberChildren() == 2)
                {
                    if (key.compareTo(x.firstMax()) <= 0) insertIntoSubtree(key, x.firstChild());
                    else insertIntoSubtree(key, x.secondChild());
                } else
                {
                    if (key.compareTo(x.firstMax()) <= 0) insertIntoSubtree(key, x.firstChild());
                    else if (key.compareTo(x.secondMax()) <= 0) insertIntoSubtree(key, x.secondChild());
                    else insertIntoSubtree(key, x.thirdChild());
                }
                raiseSurplus(x);
            } catch (NoSuchElementException ex)
            {
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

        // TODO
        // FOR YOU REPLACE

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

    // TODO
    private void addLeaf(E key, TwoThreeNode x)
    {
        // Checks to see if the passed in node has 2 children and adds the passed in key as a 3rd node
        if (x.numberChildren() == 2)
        {
            // Creates a new node that will be the 3rd child of x
            x.thirdChild = new TwoThreeNode();

            // Sets the parent node of the new node
            x.thirdChild().parent = root();

            // Updates the number of children for x
            root().numberChildren = 3;

            // Stores the passed in key in the new child node
            x.thirdChild().element = key;

            // Stores the new 3rd max in x
            x.thirdMax = key;

        }
        // Checks to see if the passed in node has 3 children and adds the passed in key as a 4th node
        else if (x.numberChildren() == 3)
        {
            // Creates a new node that will be the 4th child of x
            x.fourthChild = new TwoThreeNode();

            // Sets the parent node of the new node
            x.fourthChild().parent = root();

            // Updates the number of children for x
            root().numberChildren = 4;

            // Stores the passed in key in the new child node
            x.fourthChild().element = key;

            // Stores the new 4th max in x
            x.fourthMax = key;
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

    // TODO
    private void fixRoot()
    {
        // Checks to see if the 4th child's value is less than the first max
        if (root().firstMax().compareTo(root().fourthChild().element) < 0)
        {
            // Checks to see if no children exist under the subtree and moves the root's 4th node to be a child under the subtree
            if (root().firstChild().numberChildren() == 0)
            {
                // Copies over the root's 4th child to the subtree as a child
                root().firstChild().firstChild = root().fourthChild();

                // Updates the parent node of the moved node
                root().firstChild().firstChild().parent = root().thirdChild();

                // Updates the number of children for x
                root().numberChildren = 3;

                // Removes the 4th max from the root node
                root().fourthMax = null;

                // Removes the 4th child from the root node
                root().fourthChild = null;
            }
        }
        // Checks to see if the 4th child's value is less than the second max
        else if (root().secondMax().compareTo(root().fourthChild().element) < 0)
        {
            // Checks to see if no children exist under the subtree and moves the root's 4th node to be a child under the subtree
            if (root().secondChild().numberChildren() == 0)
            {
                // Copies over the root's 4th child to the subtree as a child
                root().secondChild().firstChild = root().fourthChild();

                // Updates the parent node of the moved node
                root().secondChild().firstChild().parent = root().thirdChild();

                // Updates the number of children for x
                root().numberChildren = 3;

                // Removes the 4th max from the root node
                root().fourthMax = null;

                // Removes the 4th child from the root node
                root().fourthChild = null;
            }
        }
        // Checks to see if the 4th child's value is less than the third max
        else if (root().thirdMax().compareTo(root().fourthChild().element) < 0)
        {
            // Checks to see if no children exist under the subtree and moves the root's 4th node to be a child under the subtree
            if (root().thirdChild().numberChildren() == 0)
            {
                // Copies over the root's 4th child to the subtree as a child
                root().thirdChild().firstChild = root().fourthChild();

                // Updates the parent node of the moved node
                root().thirdChild().firstChild().parent = root().thirdChild();

                // Updates the number of children for x
                root().numberChildren = 3;

                // Removes the 4th max from the root node
                root().fourthMax = null;

                // Removes the 4th child from the root node
                root().fourthChild = null;
            }
        }
        // Code run if the 4th child's value was greater than the third max
        else
        {

        }
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
    // b) T satisfies the 2-3 Tree oroperties given above.

    // TODO
    public void delete(E key) throws NoSuchElementException
    {

        // FOR YOU TO SUPPLY

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
