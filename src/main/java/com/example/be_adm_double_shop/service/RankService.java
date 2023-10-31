package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Rank;
import com.example.be_adm_double_shop.repository.RankRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RankService {

    @Autowired
    private RankRepository rankRepository;

    public List<Rank> getAll() {
        return rankRepository.findAll();
    }

    public Page<Rank> findAll(Pageable pageable) {
        return rankRepository.findAll(pageable);
    }

    public Rank save(Rank rank) {
        return rankRepository.save(rank);
    }

    public Rank findById(Long id) {
        Optional<Rank> optionalRank = rankRepository.findById(id);
        return optionalRank.orElse(null); // Returns null if rank is not found
    }

    public Rank updateRank(Long id, Rank rank) {
        Optional<Rank> optionalRank = rankRepository.findById(id);
        if (optionalRank.isPresent()) {
            Rank existingRank = optionalRank.get();
            existingRank.setName(rank.getName());
            existingRank.setDescription(rank.getDescription());
            existingRank.setSince(rank.getSince());
            existingRank.setUntil(rank.getUntil());
            existingRank.setCreatedBy(rank.getCreatedBy());
            existingRank.setUpdated_by(rank.getUpdated_by());
            existingRank.setCreatedTime(rank.getCreatedTime());
            existingRank.setUpdatedTime(LocalDateTime.now());
            // Set other properties of Rank as needed

            return rankRepository.save(existingRank);
        }
        // Handle not found case if needed, such as throwing an exception
        return null;
    }


    public List<Rank> searchRanks(String keyword) {
        return rankRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Rank> sortRanks() {
        return rankRepository.findAllByOrderByName();
    }
}
