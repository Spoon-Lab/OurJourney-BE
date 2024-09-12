package pudding.toy.ourJourney.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pudding.toy.ourJourney.dto.content.ListContentDto;
import pudding.toy.ourJourney.entity.Contents;

import java.util.List;
import java.util.Optional;

import static pudding.toy.ourJourney.entity.QContents.contents;

@Repository
@RequiredArgsConstructor
public class ContentsQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public PageImpl<ListContentDto> findAll(
            Pageable pageable,
            Optional<Long> categoryId,
            Optional<String> title,
            Optional<List<Long>> tagIds
    ) {
        List<Contents> list = baseQuery(categoryId, title, tagIds)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .select(contents)
                .fetch();
        List<ListContentDto> listDto = list.stream()
                .map(content -> new ListContentDto(content))
                .toList();

        Long count = baseQuery(categoryId, title, tagIds)
                .select(contents.count())
                .fetchFirst();

        return new PageImpl<>(listDto, pageable, count);
    }

    private JPAQuery<?> baseQuery(
            Optional<Long> categoryId,
            Optional<String> title,
            Optional<List<Long>> tagIds
    ) {
        JPAQuery<?> query = jpaQueryFactory.from(contents);

        categoryId.ifPresent(it -> query.where(contents.category.id.eq(it)));
        title.ifPresent(it -> query.where(contents.title.contains(it)));
        tagIds.ifPresent(it -> query.where(contents.contentTags.any().id.in(it)));

        return query;
    }
}
