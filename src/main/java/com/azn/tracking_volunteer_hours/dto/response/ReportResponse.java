package com.azn.tracking_volunteer_hours.dto.response;

import com.azn.tracking_volunteer_hours.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {
    Project project;
    Integer hours;
}
