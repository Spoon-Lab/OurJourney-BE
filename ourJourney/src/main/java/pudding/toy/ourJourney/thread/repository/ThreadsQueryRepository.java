package pudding.toy.ourJourney.thread.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ThreadsQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
}
