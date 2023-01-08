package TDT.backend.common.utils;


import TDT.backend.entity.Category;
import TDT.backend.entity.NoticeCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class CategoryClassifier {


    public static NoticeCategory classifier(String category) {

        String ct = "";
        // "programming"
        for (Category c : Category.values()) {
            if (c.getType().equals("스터디")) {
//                ct = "study";
                return NoticeCategory.study;
            } else{
                ct = "post";
            }
            return NoticeCategory.post;

        }
        ct = "comment";
        log.info(ct);
        return NoticeCategory.comment;

    }
}
