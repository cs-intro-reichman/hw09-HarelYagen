/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
    
        Node tempfirst = first; 
        first = new Node(new CharData(chr), tempfirst);
        size++;
        
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
         String str = "(";
         ListIterator iterator = listIterator(0);
         while (iterator.hasNext()) {
            str = str + iterator.current.cp.toString() + " ";
            iterator.next();
         }
         str = str.substring(0, str.length() - 1);
         str = str + ")";
         return str;
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    
    public int indexOf(char chr) {
        int counter = 0;
        ListIterator iterator = listIterator(0);
        if (size > 0) {
        while (iterator.hasNext()) {
            if (iterator.current.cp.chr == chr) {
                return counter;
            }
            iterator.next();
            counter++;  
        }
    }
      return -1;     
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */

    public void update(char chr) {
        int i = indexOf(chr);
        if ( i > -1 ) { 
            ListIterator iterator = listIterator(i);
            Node temp = iterator.current;
            temp.cp.count++;
        } else {
            addFirst(chr);
        }
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        if (indexOf(chr) == -1) {
            return false;
        }else{
            ListIterator iterator = listIterator(indexOf(chr) - 1);
            Node temp = iterator.current;
            if (temp == first) {
                first = temp.next;
            }else {
                iterator.next();
                if (iterator.hasNext()) {
                    temp.next = iterator.current.next;
                }
                else {
                    temp.next = null;
                }
            }
        }
            size--;
            return true;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if (index >= size || index < 0){
        throw new IndexOutOfBoundsException();
        } else {
            int counter = 0;
            Node current = first;
            while(counter < index){ 
                current = current.next;
                counter++;
            }
            return current.cp;
        
        }
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }

    public void main (String args[]) {
            String test = "committee ";
            for (int i = test.length() - 1; i >= 0; i--) {
                update(test.charAt(i));
            }
            System.out.println(this);
            System.out.println(indexOf('c'));
            remove('e');
            // System.out.println(this);
            // System.out.println(get(2));
    }
}
