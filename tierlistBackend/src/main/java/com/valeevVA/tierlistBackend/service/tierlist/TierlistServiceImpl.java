package com.valeevVA.tierlistBackend.service.tierlist;

import com.valeevVA.tierlistBackend.dto.TierlistDTO;
import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import com.valeevVA.tierlistBackend.repository.tierlist.TierlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TierlistServiceImpl implements TierlistService {
    @Autowired
    private TierlistRepository tierlistRepository;
    public List<TierlistDTO> getUserTierlists(int userId) {
        return tierlistRepository.findByUserId(userId).stream()
                .map(t -> new TierlistDTO(
                        t.getId_tierlist(),
                        t.getName(),
                        t.getPublicity()))
                .toList();
    }
    @Override
    public Tierlist saveTierlist(Tierlist tierlist) {
        return tierlistRepository.save(tierlist);
    }

    @Override
    public List<Tierlist> getTierlist() {
        return tierlistRepository.findAll();
    }
    @Override
    public Page<TierlistDTO> getPublicTierlists(Pageable pageable) {
        Page<Tierlist> tierlists = tierlistRepository.findByPublicityTrue(pageable);
        List<TierlistDTO> dtos = tierlists.getContent().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, tierlists.getTotalElements());
    }

    private TierlistDTO convertToDto(Tierlist tierlist) {
        return new TierlistDTO(
                tierlist.getId_tierlist(),
                tierlist.getName(),
                tierlist.getPublicity()
        );
    }
}
