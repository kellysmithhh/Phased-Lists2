package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * Is one of the child classes of BaseStringList, and therefore also implements
 * the StringList interface.
 */
public class ArrayStringList extends BaseStringList implements StringList, FancyStringList {
    private String[] items;

    /**
     * Constructs an {@code ArrayStringList} object and initializes
     * the size to be 0 from the parent constructor and the items to
     * be 20.
     */
    public ArrayStringList() {
        // calls parent constructor
        super();
        items = new String[20];
    } // ArrayStringList constructor


    /**
     * Constructs an {@code ArrayStringList} object that is a copy of the other
     * string list.
     *
     * @param other the list that is getting copied
     */
    public ArrayStringList(StringList other) throws NullPointerException {
        super();
        if (other == null) {
            throw new NullPointerException("other cannot be null.");
        } // if

        // initial size and element values of new list are copied from other list
        this.items = new String[other.size()];
        for (int i = 0; i < other.size(); i++) {
            this.append(other.get(i));
        } // for
    } // ArrayStringList copy constructor

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

        // creates a new larger array and copies the contents in items array over
        // when the list is aout to exceed the size of its array
        if (items.length - 1 == size) {
            String[] temp = new String[items.length + 10];
            for (int j = 0; j < size; j++) {
                temp[j] = items[j];
            } // for
            items = temp;
        } // if

        // inserts an item at the index position and shifts subsequent items to the right
        // if an item was already at that index
        for (int i = size - 1; i >= index; i--) {

            items[i + 1] = items[i];
        } // for
        items[index] = item;
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
        //makes string list empty by setting size to zero
        for (int i = 0; i < size; i++) {
            items[i] = null;
        } // for
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
        return items[index];
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
        String removedItem = items[index];
        // if index to remove is the last item in the list it sets the value to null
        // else it removes the item and shifts the items in the string list that were
        // after it to the left
        if (index == size() - 1) {
            items[index] = null;

        } else {
            for (int i = index; i < size - 1; i++) {
                items[i] = items[i + 1];
            } // for
        } // if
        size--;
        return removedItem;
    } // remove

    /**
     * Returns a string list containing the items from the start index to the stop index.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public StringList slice(int start, int stop) throws IndexOutOfBoundsException {
        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException("the index you inputted is illegal.");
        } // if
        StringList slicedList = new ArrayStringList();
        String[] newArray = new String[stop - start];
        int count = 0;
        // fills a new array with the items in the list starting at start
        // and ending right before stop
        for (int i = start; i < stop; i++) {
            newArray[count] = items[i];
            // adds the items from the newArray array to the new ArrayStringList object
            // at the appropriate indices
            slicedList.add(count, newArray[count]);
            count++;
        } // for
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
        } // if
        FancyStringList slicedList = new ArrayStringList();
        // creates number of elements needed for new array
        double doubleVersion = Math.ceil((stop - start) / step);
        int num = (int)doubleVersion;

        String[] newArray = new String[num];
        int count = 0;
        int startVar = start;
        // newArray index starts at 0 but the items starts at the desired start index.
        for (int i = 0; i < num; i++) {
            newArray[count] = items[startVar];
            startVar += step;
            // each iteration adds a new item to the new fancy string list, which will be returned.
            slicedList.add(count, newArray[count]);
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
        FancyStringList reversedList = new ArrayStringList();
        String[] newArray = new String[size()];
        int count = size() - 1;
        // newArray gets filled with last item in items array and then second to last
        // and so on until newArray is the complete reversed version of items.
        for (int i = 0; i < size(); i++) {
            newArray[i] = items[count];
            // each iteration adds a new item to the new fancy string list, which will be returned.
            reversedList.add(i, newArray[i]);
            count--;
        } // for
        return reversedList;
    } // reverse

} // ArrayStringList
