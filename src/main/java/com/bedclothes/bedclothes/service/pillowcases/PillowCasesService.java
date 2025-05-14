package com.bedclothes.bedclothes.service.pillowcases;

import com.bedclothes.bedclothes.dto.PillowsDto;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.model.Pillowcases;
import com.bedclothes.bedclothes.request.CreatePillowRequest;
import com.bedclothes.bedclothes.request.UpdatePillowsRequest;

import java.util.List;

public interface PillowCasesService {

    Pillowcases createPillowCases(CreatePillowRequest req, Clothes clothes);

    Pillowcases updatePillowCases(UpdatePillowsRequest pillowcases, Long pillowcasesId);

    List<Pillowcases> getAllPillowCases();

    void deletePillowCases(Long pillowcasesId);

    PillowsDto convertPillowCasesToDto(Pillowcases pillowCases);

    List<PillowsDto> convertPillowCasesToDto(List<Pillowcases> pillowCasesList);
}
