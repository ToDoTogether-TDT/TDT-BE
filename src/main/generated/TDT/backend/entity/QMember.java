package TDT.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -246555967L;

    public static final QMember member = new QMember("member1");

    public final EnumPath<Category> category = createEnum("category", Category.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath picture = createString("picture");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final ListPath<TeamMember, QTeamMember> teamMembers = this.<TeamMember, QTeamMember>createList("teamMembers", TeamMember.class, QTeamMember.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

