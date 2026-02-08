package entity;

@Entity
@Table(
    name = "idempotency_keys",
    uniqueConstraints = @UniqueConstraint(columnNames = {"key"})
)
public class IdempotencyKey {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String key;

    private boolean completed = false;

    protected IdempotencyKey() {}

    public IdempotencyKey(String key) {
        this.key = key;
    }
}

