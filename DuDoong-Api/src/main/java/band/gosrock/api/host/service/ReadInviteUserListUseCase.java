package band.gosrock.api.host.service;


import band.gosrock.api.common.UserUtils;
import band.gosrock.api.host.model.mapper.HostMapper;
import band.gosrock.common.annotation.UseCase;
import band.gosrock.domain.common.vo.UserProfileVo;
import band.gosrock.domain.domains.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadInviteUserListUseCase {
    private final UserUtils userUtils;
    private final HostMapper hostMapper;

    public UserProfileVo execute(Long hostId, String email) {
        final User user = userUtils.getCurrentUser();
        final Long userId = user.getId();
        return hostMapper.toHostInviteUserList(hostId, email);
    }
}