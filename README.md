# Wallet Service

A high-performance, double-entry ledger-based wallet service built with Spring Boot.

## Features

- **Double-Entry Ledger**: All balance changes are recorded as distinct credit/debit entries. There is no mutable "balance" column; balance is derived by summing ledger entries. This ensures complete auditability and consistency.
- **Idempotency**: All mutating operations (`topup`, `spend`, `bonus`) require a unique `idempotencyKey`. This prevents duplicate transactions even in case of network retries.
- **Pessimistic Locking**: Prevents race conditions during concurrent transactions on the same wallet by locking the wallet row in the database (`SELECT ... FOR UPDATE`).
- **Audit Logging**: Separate audit trail for high-level operations.

## Architecture

### Transaction Model
Instead of updating a balance field, we insert immutable records into a `ledger_entries` table.
- **Credit**: Adds to balance (Topup, Bonus, etc.)
- **Debit**: Subtracts from balance (Spend, Withdrawal)

### Concurrency Control
We use specific locking strategies to handle high concurrency:
1.  **Lock Wallet**: `LockService` acquires a pessimistic write lock on the `Wallet` entity.
2.  **Verify State**: Checks partial balance or other invariants.
3.  **Insert Ledger Entry**: Appends the new transaction.
4.  **Release Lock**: Transaction commit releases the lock.

## API Usage

### 1. Get Balance
`GET /wallets/{walletId}/balance`

### 2. Topup Wallet
`POST /wallet/topup`
```json
{
  "userId": 1,
  "assetType": "USD",
  "amount": 1000,
  "idempotencyKey": "unique-uuid-1234"
}
```

### 3. Spend Funds
`POST /wallet/spend`
```json
{
  "userId": 1,
  "assetType": "USD",
  "amount": 500,
  "itemId": "item-001",
  "idempotencyKey": "unique-uuid-5678"
}
```

## Running the Application

1.  **Build**: `mvn clean install`
2.  **Run**: `mvn spring-boot:run`
3.  **Console**: Access H2 Console at `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:walletdb`
    - User: `sa`
    - Password: `password`

## Seeding
On startup, the application seeds:
- Users: John Doe, Jane Smith
- Assets: USD, EUR
- Initial Topups
