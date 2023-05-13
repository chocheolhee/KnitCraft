package Toy.KnitCraft.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Signup {

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름를 입력해주세요.")
    private String username;

    @Builder
    public Signup(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
