/**
 * This package contains custom exception classes for the gym application.
 * It includes exceptions to handle specific error scenarios related to gym operations.
 */
package gym.Exception;

/**
 * Exception thrown when a client is not registered in the system.
 * This is a custom exception to indicate an error scenario where an operation
 * requires a registered client, but the client is not found.
 */
public class ClientNotRegisteredException extends Exception {

    /**
     * Constructs a new ClientNotRegisteredException with the specified detail message.
     *
     * @param str the detail message explaining the reason for the exception
     */
    public ClientNotRegisteredException(String str) {
        super(str);
    }
}
