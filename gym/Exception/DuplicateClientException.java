/**
 * This package contains custom exception classes for the gym application.
 * It includes exceptions to handle specific error scenarios related to gym operations.
 */
package gym.Exception;

/**
 * Exception thrown when a duplicate client is detected in the system.
 * This is a custom exception used when an operation attempts to add a client
 * that already exists in the system.
 */
public class DuplicateClientException extends Exception {

    /**
     * Constructs a new DuplicateClientException with the specified detail message.
     *
     * @param str the detail message explaining the reason for the exception
     */
    public DuplicateClientException(String str){
        super(str);
    }
}
