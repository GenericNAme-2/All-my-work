/**
 * Classroom creates a scenario with a student entering a classroom.
 *
 * Ms.Krasteva
 * ICS4U0
 *
 * @author Daniel Voznyy
 * @version 24.04.19
 */
public class ClassRoom {
    String name;
    String teacher;

    /**
     * Constructor for the classroom
     *
     * @param name    the name of the class
     * @param teacher the class' teacher
     */
    public ClassRoom(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    /**
     * If the student entering the class doesn't have a locker, they get sent to the office, if they have a jacket,
     * they get sent to their locker to put it back and are not let in, lastly it checks if the student has the right book
     * for this class.
     *
     * @param student the student entering the classroom
     * @return whether the student was let inside
     */
    boolean enter(Student student) {
        System.out.println("Student " + student + " came into class " + name + " taught by " + teacher);
        if (student.myLocker == null) {
            student.sendToOffice("Get a locker");
            return false;
        }
        if (student.myJacket != null) {
            student.myLocker.putJacket(student.myJacket); //put jacket back in locker
            return false;
        }
        for (Book book : student.books)
            if (book != null && book.course.equals(name)) //avoid null pointer exception and check the book's course against this class' course
                return true;
        student.sendToOffice("Class " + name + " requires its appropriate books");
        return false;
    }
}