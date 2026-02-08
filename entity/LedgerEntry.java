package entity;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Wallet wallet;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private String entryType;
     // CREDIT / DEBIT
     
    @Column(nullable = false)
    private String transactionType;

    private String reference;

    private Instant createdAt = Instant.now();

    protected LedgerEntry() {}

    public static LedgerEntry debit(
            Wallet wallet, Long amount, String type, String ref
    ) {
        return new LedgerEntry(wallet, amount, "DEBIT", type, ref);
    }

    public static LedgerEntry credit(
            Wallet wallet, Long amount, String type, String ref
    ) {
        return new LedgerEntry(wallet, amount, "CREDIT", type, ref);
    }
}

