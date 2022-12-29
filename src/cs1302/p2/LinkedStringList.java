package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.Node;
import cs1302.adt.FancyStringList;

/**
 * Is one of the child classes of BaseStringList, and therefore also implements
 * the StringList interface.
 */
public class LinkedStringList extends BaseStringList implements StringList, FancyStringList {
    private Node head;
    private Node next;

    /**
     * Constructs a {@code LinkedStringList} object and initializes the size
     * to be 0 from the parent constructor and the head to be null.
     */
    public LinkedStringList() {
        // calls parent constructor
        super();
        head = null;
    } // LinkedStringList constructor

    /**
     * Constructs an {@code LinkedStringList} object that is a copy of the other
     * string list.
     *
     * @param other the list that is getting copied
     */
    public LinkedStringList(StringList other) throws NullPointerException {
        super();
        if (other == null) {
            throw new NullPointerException("other cannot be null.");
        } // if

        // initial size and element values of new list are copied from other list
        head = null;
        for (int i = 0; i < other.size(); i++) {
            this.append(other.get(i));
        } // for
    } // LinkedStringList copy constructor

    /**
     * Adds an item at the specified index position.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, String item) throws NullPointerException,
        IllegalArgumentException, IndexOutOfBoundsException {
        if (item == null) {
            throw new NullPointerException("the item cannot be null.");
        } // if
        if (item.equals("")) {
            throw new IllegalArgumentException("the item cannot be empty");
        } // if
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("the index is out of range.");
        } // if
        int count = 0;
        Node node = new Node(item);
        // reassigns head to a new node pointing to head and containing the item
        // if desired index to add to is 0
        if (index == 0) {
            head = new Node(item, head);
        } else {
            Node temp = head;
            Node currentNode = head;
            // temp Node is always one node in front of currentNode after each iteration
            while (count < index) {
                currentNode = temp;
                temp = temp.getNext();
                count++;
            } // while
            // currentNode sets next value to the node that the user wants to add and then
            // sets next on the new node to the temp node
            currentNode.setNext(new Node(item));
            currentNode.getNext().setNext(temp);
        } // if
        size++;
        return true;
    } // add

    /**
     * Removes all of the items from the string list.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        //sets the size to 0
        size = 0;
    } // clear

    /**
     * Returns the item at the specified index position.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("the index is out of range.");
        } // if
        Node node = head;
        int count = 0;
        // iterates until it gets to the node at the desired index
        while (count < index) {
            node = node.getNext();
            count++;
        } // while
        // returns the item from the desired node
        String returnItem =  node.getItem();
        return returnItem;
    } // get

    /**
     * Removes  an item at the specified index position.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("the index is out of range.");
        } // if
        Node currentNode = head;
        String returnItem;
        // returns item in node that head points to if removing index is 0 and then reassigns
        // the node that head points to to the next in line
        if (index == 0) {
            returnItem = head.getItem();
            head = head.getNext();
        } else {
            // iterates until currentNode is set to node before the desired node to remove
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            } // for
            returnItem = currentNode.getNext().getItem();
            // sets next value for currentNode to equal the node two spots ahead of it
            currentNode.setNext(currentNode.getNext().getNext());
        } // if
        size--;
        return returnItem;
    } // remove

    /**
     * Returns a string containing the items from the start index to the stop index.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public StringList slice(int start, int stop) throws IndexOutOfBoundsException {
        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException("the index you inputted is illegal.");
        } // if
        StringList slicedList = new LinkedStringList();
        Node currentNode = head;
        Node temp = head;
        int count = 0;

        // temp node is one node ahead of currentNode after each iteration until temp
        // points to the index of the node at start
        for (int i = 0; i < start; i++) {
            currentNode = temp;
            temp = temp.getNext();
        } // for
        // adds the items from currentNode traversing through list to the
        // new LinkedStringList object
        for (int i = start; i < stop - 1; i++) {
            currentNode = temp;
            temp = temp.getNext();
            slicedList.add(count, currentNode.getItem());
            count++;
        } // for
        // adds one more item to the LinkedStringList object from the next node after
        // currentNode if the start index doesn't equal the stop index
        if (start != stop) {
            currentNode = currentNode.getNext();
            slicedList.add(count, currentNode.getItem());
        } // if
        return slicedList;
    } // slice

    /**
     * Returns a fancy string list that has items from start index to
     * the stop index, while also accounting for the interval (step).
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public FancyStringList slice(int start, int stop, int step) throws IndexOutOfBoundsException {
        if (start < 0 || stop > size() || start > stop || step < 1) {
            throw new IndexOutOfBoundsException("illegal endpoint index or step value.");
        }
        FancyStringList slicedList = new LinkedStringList();
        Node currentNode = head;
        Node temp = head;
        int count = 0;
        // creates number for the amount of times the currentNode must traverse through list
        double doubleVersion = Math.ceil((stop - start) / step);
        int num = (int)doubleVersion;

        // temp node is one node ahead of currentNode after each iteration until temp
        // points to the index of the node at start
        for (int i = 0; i < start; i++) {
            currentNode = temp;
            temp = temp.getNext();
        } // for
        // adds the items from currentNode traversing through list to the
        // new LinkedStringList object
        for (int i = 0; i < num; i++) {
            currentNode = temp;
            slicedList.add(count, currentNode.getItem());
            for (int j = 0; j < step; j++) {
                temp = temp.getNext();
            } // for
            count++;
        } // for
        return slicedList;
    } // slice

    /**
     * Returns a fancy string list containing the items in reverse order.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public FancyStringList reverse() {
        FancyStringList reversedList = new LinkedStringList();
        Node currentNode = head;
        Node temp = head;
        Node temp2 = head.getNext();
        int counter = 0;
        if (size() > 2) {
            // temp2 will point at last node and temp will point at second to last node
            for (int i = 0; i < size() - 2; i++) {
                temp2 = temp2.getNext();
                temp = temp.getNext();
            } // for
            int count = size() - 3;
            // adds item of node that temp2 points to and sets next node to the node before
            // it, which is temp
            for (int j = 0; j < size() - 2; j++) {
                reversedList.add(counter, temp2.getItem());
                temp2.setNext(temp);
                temp2 = temp;
                for (int k = 0; k < count; k++) {
                    currentNode = currentNode.getNext();
                    temp = currentNode;
                } // for
                currentNode = head;
                count--;
                counter++;
            } // for
            // adds second to last node to list, sets the next node to be the head and then sets
            // the next node of the head to equal null
            reversedList.add(counter, temp2.getItem());
            temp.setNext(currentNode);
            currentNode.setNext(null);
            counter++;
            reversedList.add(counter, currentNode.getItem());
        } else if (size() == 2) {
            // points temp2 to temp and then adds temp2 and temp items to reversedList respectively.
            temp2.setNext(temp);
            reversedList.add(counter, temp2.getItem());
            counter++;
            reversedList.add(counter, temp.getItem());
        } else {
            // if there is only one node, the reversedList will equal the initial list.
            reversedList.add(counter, temp.getItem());
        } // if
        return reversedList;
    } // reverse
} // LinkedStringList
