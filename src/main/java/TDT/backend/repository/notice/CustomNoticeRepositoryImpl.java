package TDT.backend.repository.notice;

import TDT.backend.dto.notice.NoticeResponseDto;
import TDT.backend.entity.Member;

import java.util.List;

public class CustomNoticeRepositoryImpl implements CustomNoticeRepository{
    @Override
    public List<NoticeResponseDto> findAllNoticeByMember(Member member) {
        return null;
    }

    @Override
    public List<NoticeResponseDto> findAllByMemberIdAndNoticeCategory(Long memberId, String noticeCategory) {



        return null;
    }
}
