package com.valeevVA.tierlistBackend.repository.tierlist;

import com.valeevVA.tierlistBackend.model.tierlist.Tierlist;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TierlistRepository extends JpaRepository<Tierlist,Integer> {
    @Query("SELECT t FROM Tierlist t WHERE t.user.id_user = :userId")
    List<Tierlist> findByUserId(@Param("userId") Integer userId);
    Page<Tierlist> findByPublicityTrue(Pageable pageable); // Изменено для поддержки пагинации
}
