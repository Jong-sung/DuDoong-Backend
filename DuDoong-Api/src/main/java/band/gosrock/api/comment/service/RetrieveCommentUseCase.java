package band.gosrock.api.comment.service;


import band.gosrock.api.comment.mapper.CommentMapper;
import band.gosrock.api.comment.model.response.RetrieveCommentListResponse;
import band.gosrock.api.common.UserUtils;
import band.gosrock.common.annotation.UseCase;
import band.gosrock.domain.domains.comment.dto.condition.CommentCondition;
import band.gosrock.domain.domains.event.adaptor.EventAdaptor;
import band.gosrock.domain.domains.event.domain.Event;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RetrieveCommentUseCase {

    private final CommentMapper commentMapper;

    private final EventAdaptor eventAdaptor;

    private final UserUtils userUtils;

    public RetrieveCommentListResponse execute(Long eventId, Long lastId) {
        Event event = eventAdaptor.findById(eventId);
        Long currentUserId = userUtils.getCurrentUserId();
        CommentCondition commentCondition = new CommentCondition(event.getId(), lastId);
        return commentMapper.toRetrieveCommentListResponse(commentCondition, currentUserId);
    }
}