package gym.management.Sessions;

/**
 * The {@code ForumType} enum represents the types of forums available for gym sessions.
 * It is used to categorize sessions based on the target participant group.
 */
public enum ForumType {
    /**
     * Represents a session open to all participants, regardless of demographics.
     */
    All,

    /**
     * Represents a session exclusively for female participants.
     */
    Female,

    /**
     * Represents a session specifically for seniors (65+).
     */
    Seniors,

    /**
     * Represents a session exclusively for male participants.
     */
    Male
}