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


    public static String classifier(String category) {

        String ct = "";
        // "programming"
        for (Category c : Category.values()) {
            System.out.println("c:"+c);
            log.info(category);
            if (c.equals(category) && c.getType() == "스터디") {
                ct = "study";
            } else{
                ct = "post";
            }
            return ct;

        }
        ct = "comment";
        return ct;

    }
}
