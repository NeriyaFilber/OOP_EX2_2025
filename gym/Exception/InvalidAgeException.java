/**
 * This package contains custom exception classes for the gym application.
 * It includes exceptions to handle specific error scenarios related to gym operations.
 */
package gym.Exception;

/**
 * Exception thrown when a client is not of the required age to register.
 * This exception is used when an operation attempts to register a client who is younger than the minimum required age.
 * <p>
 * This class implements the Singleton design pattern, ensuring that only one instance
 * of the exception is created and reused throughout the application.
 * </p>
 */
public class InvalidAgeException extends Throwable {

    // Static instance for the Singleton design pattern
    private static InvalidAgeException _instance;

    /**
     * Returns the detail message for this exception.
     *
     * @return the detail message explaining the reason for the exception
     */
    @Override
    public String getMessage() {
        return "Error: Client must be at least 18 years old to register";
    }

    /**
     * Provides access to the singleton instance of the {@code InvalidAgeException}.
     * If the instance does not exist, it is created; otherwise, the existing instance is returned.
     *
     * @return the singleton instance of the InvalidAgeException
     */
    public static InvalidAgeException getInstance(){
        if(_instance == null){
            _instance = new InvalidAgeException();
        }
        return _instance;
    }
}
