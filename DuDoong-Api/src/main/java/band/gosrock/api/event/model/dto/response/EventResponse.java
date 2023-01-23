package band.gosrock.api.event.model.dto.response;


import band.gosrock.domain.domains.event.domain.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventResponse {
    @Schema(description = "공연 이름")
    private String name;

    @Schema(description = "공연 시작 시간")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "Asia/Seoul")
    private LocalDateTime startAt;

    // 분 단위입니다
    private Long runTime;

    private Double latitude;

    private Double longitude;

    private String posterImage;

    private String url;

    private String placeName;

    private String placeAddress;

    private String content;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "Asia/Seoul")
    private LocalDateTime ticketingStartAt;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "Asia/Seoul")
    private LocalDateTime ticketingEndAt;

    public static EventResponse of(Event event) {
        return EventResponse.builder()
                .name(event.getName())
                .startAt(event.getStartAt())
                .runTime(event.getRunTime())
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .posterImage(event.getPosterImage())
                .url(event.getUrl())
                .placeName(event.getPlaceName())
                .placeAddress(event.getPlaceAddress())
                .content(event.getContent())
                .ticketingStartAt(event.getTicketingStartAt())
                .ticketingEndAt(event.getTicketingEndAt())
                .build();
    }
}