package com.valeevVA.tierlistBackend.service.tierlist;

import com.valeevVA.tierlistBackend.dto.TierlistDTO;
import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TierlistService {
    public List<TierlistDTO> getUserTierlists(int userId);
    public Tierlist saveTierlist(Tierlist tierlist);
    public List<Tierlist> getTierlist();
    Page<TierlistDTO> getPublicTierlists(Pageable pageable); // Изменено на возврат Page
}
