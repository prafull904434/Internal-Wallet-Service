package Service;

public interface AuditService {

    void record(String action, Long userId, Long amount);
}

