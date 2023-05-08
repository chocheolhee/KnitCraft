package Toy.KnitCraft.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SessionResponse {

    private String accessToken;

    public SessionResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
