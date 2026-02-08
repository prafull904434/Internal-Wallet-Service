package entity;

@Entity
@Table(
    name = "transactions",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"idempotency_key"}
    )
)
public class TransactionRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String idempotencyKey;

    @Column(nullable = false)
    private String type; 
// TOPUP, BONUS, SPEND
    private Instant createdAt = Instant.now();
}

