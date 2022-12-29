package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * Abstract class that implements a subset of abstract methods in StringList
 * interface. It has two child classes called ArrayStringList and LinkedStringList.
 */
public abstract class BaseStringList implements StringList, FancyStringList {
    protected int size;

    /**
     * Constructs a {@code BaseStringList} object and initializes
     * the size to be 0.
     */
    public BaseStringList() {
        size = 0;
    } // BaseStringList constructor

    /**
     * Inserts an item at index size() to the string list.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean append(String item) throws NullPointerException, IllegalArgumentException {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        } // if
        if (item.isEmpty()) {
            throw new IllegalArgumentException("item cannot be empty");
        } // if
        this.add(size(), item);
        return true;
    } // append

    /**
     * Returns true if list is empty, and false if it has items.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        } // if
    } // isEmpty

    /**
     * Returns a string version of the list that begins with start, ends with
     * end, and is separated by sep.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String makeString(String start, String sep, String end) {
        // initializes completeString to be empty
        String completeString = "";
        // adds String sep in between each item on the list
        if (this.size() > 0) {
            for (int i = 0; i < this.size() - 1; i++) {
                completeString += this.get(i) + sep;
            } // for
            completeString += this.get(this.size() - 1);
        } // if
        completeString = start + completeString + end;
        return completeString;
    } // makeString

    /**
     * Inserts an item at index 0.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(String item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        } // if
        if (item.isEmpty()) {
            throw new IllegalArgumentException("item cannot be empty");
        } // if
        this.add(0, item);
        return true;
    } // prepend

    /**
     * Returns the number of items in the list.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    } // size

    /**
     * Returns the String with brackets surrounding it and commas separating
     * each of the items.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return makeString("[", ", ", "]");
    } // toString

    /**
     * Adds items into the string list at the specified index.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, StringList items) throws NullPointerException,
        IndexOutOfBoundsException {
        if (items == null) {
            throw new NullPointerException("items cannot be null");
        } // if
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        } // if
        int count = 0;
        int listSize = items.size();
        int indexNum = index;
        // if this object is the same as the Stringlist that is getting added to it
        if (this.equals(items)) {
            for (int i = 0; i < listSize; i++) {
                // Starting at desired insertion index, every item
                // from StringList items is added to this string list.
                this.add(indexNum, items.get(count));
                count = count + 2;
                indexNum++;
            } // for
        } else {
            for (int i = 0; i < listSize; i++) {
                this.add(indexNum, items.get(count));
                count++;
                indexNum++;
            } // for
        } // if
        return !items.isEmpty();
    } // add

    /**
     * Adds items into the string list at the index corresponding to size().
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean append(StringList items) throws NullPointerException {
        if (items == null) {
            throw new NullPointerException("items cannot be null.");
        } // if
        this.add(this.size(), items);
        return !items.isEmpty();
    } // append

    /**
     * Returns true only if there is an item at or after start index that matches
     * the target String.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean contains(int start, String target) {
        if (start >= 0) {
            for (int i = start; i < size(); i++) {
                // steps through list until it reaches target string
                if (this.get(i).equals(target)) {
                    return true;
                } // if
            } // for
        } // if
        // returns false if target string is not present
        return false;
    } // contains

    /**
     * Returns the index value of the first instance of the target String at or
     * after the start index or 0 (whichever is larger).
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public int indexOf(int start, String target) {
        if (this.contains(start, target) == true) {
            for (int i = start; i < size(); i++) {
                // checks each value on list to see if it is equal to target
                if (this.get(i).equals(target)) {
                    return i;
                } // if
            } // for
        } // if
        return -1;
    } // indexOf

    /**
     * Prepends items to this string list.
     *
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(StringList items) throws NullPointerException {
        if (items == null) {
            throw new NullPointerException("items cannot be null.");
        } // if
        this.add(0, items);
        return !items.isEmpty();
    } // prepend

} // BaseStringList
