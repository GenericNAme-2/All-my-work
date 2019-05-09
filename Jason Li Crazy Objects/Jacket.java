/**
 * Jacket has a Student owner
 *
 * Ms.Krasteva
 * ICS4U0
 *
 * @author Daniel Voznyy
 * @version 24.04.19
 */
public class Jacket {
    Student owner;

    /**
     * Jacket constructor
     *
     * @param me the student who owns the jacket
     */
    public Jacket(Student me) {
        this.owner = me;
    }

    @Override
    public String toString() {
        return owner + " owns this";
    }
}