/**
 * This package contains custom exception classes for the gym application.
 * It includes exceptions to handle specific error scenarios related to gym operations.
 */
package gym.Exception;

/**
 * Exception thrown when an instructor is not qualified to conduct a specific session type.
 * This is a custom exception used when an operation requires a qualified instructor,
 * but the instructor is not qualified for the session type.
 *
 */
public class InstructorNotQualifiedException extends Exception {

    /**
     * Constructs a new DuplicateClientException with the specified detail message.
     *
     * @param str the detail message explaining the reason for the exception
     */
    public InstructorNotQualifiedException(String str){
        super(str);
    }
}
