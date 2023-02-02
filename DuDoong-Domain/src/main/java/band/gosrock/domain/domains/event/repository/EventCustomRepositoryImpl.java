package band.gosrock.domain.domains.event.repository;

import static band.gosrock.domain.domains.event.domain.QEvent.event;

import band.gosrock.domain.common.util.QueryDslUtil;
import band.gosrock.domain.domains.event.domain.Event;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class EventCustomRepositoryImpl implements EventCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Event> querySliceEventsByHostIdIn(List<Long> hostId, Pageable pageable) {
        OrderSpecifier[] orders = QueryDslUtil.getOrderSpecifiers(Event.class, pageable);
        List<Event> events =
                queryFactory
                        .selectFrom(event)
                        .where(event.hostId.in(hostId))
                        .orderBy(orders)
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();
        return checkLastPage(events, pageable);
    }

    private Slice<Event> checkLastPage(List<Event> events, Pageable pageable) {
        boolean hasNext = false;
        if (events.size() > pageable.getPageSize()) {
            hasNext = true;
            events.remove(pageable.getPageSize());
        }
        return new SliceImpl<>(events, pageable, hasNext);
    }
}
