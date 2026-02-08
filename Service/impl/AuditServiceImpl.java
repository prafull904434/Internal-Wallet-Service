package Service.impl;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void record(String action, Long userId, Long amount) {
        auditRepository.save(
                new AuditLog(action, userId, amount)
        );
    }
}

