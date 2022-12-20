package TDT.backend.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private String url;
    private Integer size;

    @Builder
    public Image(String url) {
        this.url = url;
    }


}
