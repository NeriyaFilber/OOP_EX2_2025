/**
 * This package contains custom exception classes for the gym application.
 * It includes exceptions to handle specific error scenarios related to gym operations.
 */
package gym.Exception;

/**
 * Exception thrown when an instructor is not qualified to conduct a specific session type.
 * This is a custom exception used when an operation requires a qualified instructor,
 * but the instructor is not qualified for the session type.
 * <p>
 * This class implements the Singleton design pattern, ensuring that only one instance
 * of the exception is created and reused throughout the application.
 * </p>
 */
public class InstructorNotQualifiedException extends Exception {

    // Static instance for the Singleton design pattern
    private static InstructorNotQualifiedException _instance;

    /**
     * Returns the detail message for this exception.
     *
     * @return the detail message explaining the reason for the exception
     */
    @Override
    public String getMessage() {
        return "Error: Instructor is not qualified to conduct this session type.";
    }

    /**
     * Provides access to the singleton instance of the {@code InstructorNotQualifiedException}.
     * If the instance does not exist, it is created; otherwise, the existing instance is returned.
     *
     * @return the singleton instance of the InstructorNotQualifiedException
     */
    public static InstructorNotQualifiedException getInstance(){
        if(_instance == null){
            _instance = new InstructorNotQualifiedException();
        }
        return _instance;
    }
}
