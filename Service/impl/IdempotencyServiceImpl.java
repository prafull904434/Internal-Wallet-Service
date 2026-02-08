package Service.impl;

@Service
public class IdempotencyServiceImpl implements IdempotencyService {

    private final IdempotencyRepository repository;

    public IdempotencyServiceImpl(IdempotencyRepository repository) {
        this.repository = repository;
    }

    public void validateOrThrow(String key) {
        if (repository.existsByKey(key)) {
            throw new DuplicateTransactionException();
        }
        repository.save(new IdempotencyKey(key));
    }

    public void markCompleted(String key) {
        repository.markCompleted(key);
    }
}

