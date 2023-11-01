package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Rank;
import com.example.be_adm_double_shop.service.RankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/double/rank")
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping("/index")
    public ResponseEntity<Page<Rank>> getRanksByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        if (page < 1) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<Rank> rankPage = rankService.findAll(pageable);
        return ResponseEntity.ok().body(rankPage);
    }

    @GetMapping("/index/next")
    public ResponseEntity<Page<Rank>> getNextRanksPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        page++;
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<Rank> rankPage = rankService.findAll(pageable);
        return ResponseEntity.ok().body(rankPage);
    }

    @GetMapping("/index/previous")
    public ResponseEntity<Page<Rank>> getPreviousRanksPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        page--;
        if (page < 1) {
            page = 1;
        }
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<Rank> rankPage = rankService.findAll(pageable);
        return ResponseEntity.ok().body(rankPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rank> getRankById(@PathVariable("id") Long id) {
        Rank rank = rankService.findById(id);
        return ResponseEntity.ok().body(rank);
    }

    @PostMapping("/add")
    public ResponseEntity<Rank> addRank(@RequestBody @Valid Rank rank) {
        Rank savedRank = rankService.save(rank);
        return ResponseEntity.ok().body(savedRank);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Rank> updateRank(
            @PathVariable("id") Long id,
            @RequestBody @Valid Rank rank) {
        Rank updatedRank = rankService.updateRank(id, rank);
        return ResponseEntity.ok().body(updatedRank);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Rank>> searchRanks(@RequestParam("keyword") String keyword) {
        List<Rank> rankList = rankService.searchRanks(keyword);
        return ResponseEntity.ok().body(rankList);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Rank>> getSortedRankList() {
        List<Rank> sortedRankList = rankService.sortRanks();
        return ResponseEntity.ok().body(sortedRankList);
    }
}
