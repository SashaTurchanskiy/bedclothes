package com.bedclothes.bedclothes.controller;

import com.bedclothes.bedclothes.dto.PillowsDto;
import com.bedclothes.bedclothes.model.Clothes;
import com.bedclothes.bedclothes.model.Pillowcases;
import com.bedclothes.bedclothes.request.CreatePillowRequest;
import com.bedclothes.bedclothes.request.UpdatePillowsRequest;
import com.bedclothes.bedclothes.service.pillowcases.PillowCasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pillowcases")
@RequiredArgsConstructor
public class PillowCasesController {

    private final PillowCasesService pillowCasesService;

    @PostMapping("/create")
    public ResponseEntity<PillowsDto> createPillowCases(@RequestBody CreatePillowRequest req, Clothes clothes){
        Pillowcases pillowcases = pillowCasesService.createPillowCases(req, clothes);
        PillowsDto plsdto = pillowCasesService.convertPillowCasesToDto(pillowcases);
        return ResponseEntity.ok(plsdto);
    }
    @PutMapping("/update/{pillowcasesId}")
    public ResponseEntity<PillowsDto> updatePillowCases(@RequestBody UpdatePillowsRequest req, @PathVariable Long pillowcasesId){
        Pillowcases pillowcases = pillowCasesService.updatePillowCases(req, pillowcasesId);
        PillowsDto plsdto = pillowCasesService.convertPillowCasesToDto(pillowcases);
        return ResponseEntity.ok(plsdto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PillowsDto>> getAllPillowCases(){
        List<Pillowcases> pillowcases = pillowCasesService.getAllPillowCases();
        List<PillowsDto> dtos = pillowCasesService.convertPillowCasesToDto(pillowcases);
        return ResponseEntity.ok(dtos);
    }
    @DeleteMapping("/delete/{pillowcasesId}")
    public ResponseEntity<String> deletePillowCases(@PathVariable Long pillowcasesId){
        try {
            pillowCasesService.deletePillowCases(pillowcasesId);
            return ResponseEntity.ok("PillowCases deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.ok("PillowCases" + pillowcasesId + "not found");
        }
    }
}
