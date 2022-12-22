package TDT.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamSchedule is a Querydsl query type for TeamSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeamSchedule extends EntityPathBase<TeamSchedule> {

    private static final long serialVersionUID = 1691607803L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTeamSchedule teamSchedule = new QTeamSchedule("teamSchedule");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSchedule schedule;

    public final QTeam team;

    public QTeamSchedule(String variable) {
        this(TeamSchedule.class, forVariable(variable), INITS);
    }

    public QTeamSchedule(Path<? extends TeamSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTeamSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTeamSchedule(PathMetadata metadata, PathInits inits) {
        this(TeamSchedule.class, metadata, inits);
    }

    public QTeamSchedule(Class<? extends TeamSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
        this.team = inits.isInitialized("team") ? new QTeam(forProperty("team")) : null;
    }

}

