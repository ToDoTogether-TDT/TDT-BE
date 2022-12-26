package TDT.backend.dto.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MessageRequestDto {

    private String senderNickname;
    private String contents;

    //STUDY INFO Embed
    private String studyTitle;


}
