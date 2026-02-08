package SecurityLayer;

public class AuthenticatedUser {

    private final Long userId;

    public AuthenticatedUser(Long userId) {
        this.userId = userId;
    }

    public Long userId() {
        return userId;
    }
}

