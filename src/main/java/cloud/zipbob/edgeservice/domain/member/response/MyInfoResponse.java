package cloud.zipbob.edgeservice.domain.member.response;

import cloud.zipbob.edgeservice.domain.member.Member;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyInfoResponse implements Serializable {
    private Long id;
    private String email;
    private String nickname;

    public static MyInfoResponse of(Member member) {
        return new MyInfoResponse(member.getId(), member.getEmail(), member.getNickname());
    }
}
