package entity;

@Entity
@Table(
    name = "wallets",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"user_id", "asset_id"}
    )
)
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private AssetType asset;

    protected Wallet() {}
}

