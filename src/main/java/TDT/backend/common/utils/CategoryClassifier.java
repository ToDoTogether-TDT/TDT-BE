package TDT.backend.common.utils;


import TDT.backend.entity.Category;
import TDT.backend.entity.NoticeCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryClassifier {


    public static String classifier(String category) {

        String ct = "";
        for (Category c : Category.values()) {
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
