package Service;

public interface IdempotencyService {

    void validateOrThrow(String key);

    void markCompleted(String key);
}

