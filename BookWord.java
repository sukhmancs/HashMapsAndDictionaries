/**
 * A class to represent a word in a book and its count.
 *
 * The class is case insensitive, so "The" and "the" are considered the same word.
 *
 * The class overrides the equals and hashCode methods to allow for easy comparison and use in a HashSet.
 *
 * The class uses the FNV-1a hash algorithm to generate the hash code for the word.
 *
 * @author Sukhmanjeet Singh, Student ID 000838215
 */
public class BookWord {
    private String word;
    private int count;
    /**
     * Constructor for the BookWord class.
     *
     * @param word The word to be stored.
     */
    public BookWord(String word) {
        this.word = word.toLowerCase();
        this.count = 1;
    }

    /**
     * Get the word stored in the BookWord object.
     * @return The word stored in the BookWord object.
     */
    public String getWord() {
        return word;
    }

    /**
     * Get the count of the word stored in the BookWord object.
     * @return The count of the word stored in the BookWord object.
     */
    public int getCount() {
        return count;
    }

    /**
     * Increment the count of the word stored in the BookWord object.
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Set the count of the word stored in the BookWord object.
     * @param count The count to be set.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Compare two BookWord objects.
     *
     * @param obj The BookWord object to compare to.
     * @return True if the two BookWord objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookWord other = (BookWord) obj;
        if ((this.word == null) ? (other.word != null) : !this.word.equals(other.word)) {
            return false;
        }
        return true;
    }

    /**
     * Generate a hash code for the BookWord object.
     *
     * @return The hash code for the BookWord object.
     */
    @Override
    public int hashCode() {
        // Fnv1a
        // http://www.isthe.com/chongo/tech/comp/fnv/
        // The FNV-1a hash algorithm is as follows:
        // hash = FNV_offset_basis
        // for each octet_of_data to be hashed
        //     hash = hash xor octet_of_data
        //     hash = hash * FNV_prime
        // return hash
        final int FNV_offset_basis = 0x811c9dc5; // 2166136261
        final int FNV_prime = 0x01000193;        // 16777619
        int hash = FNV_offset_basis;             // represents the initial basis for the hash
        for (int i = 0; i < word.length(); i++) { // for each octet_of_data to be hashed .i.e. each character in the word
            hash ^= word.charAt(i);         // hash = hash xor octet_of_data eg. 1011 xor 1100 = 0111 .i.e. the hash is XORed with the character
            hash *= FNV_prime;              // hash = hash * FNV_prime eg. 0111 * 16777619 = 1865603 .i.e. the hash is multiplied by the prime number
        }
        return hash; // return the hash code
    }

    /**
     * Get a string representation of the BookWord object.
     *
     * @return A string representation of the BookWord object.
     */
    @Override
    public String toString() {
        return "Word: " + word + ", count: " + count;
    }
}
