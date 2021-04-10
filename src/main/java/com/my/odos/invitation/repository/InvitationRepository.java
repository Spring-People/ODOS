package com.my.odos.invitation.repository;

import com.my.odos.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {

    List<Invitation> findByToId(int toId);
    Invitation findByToIdAndFromId(int toId, int fromId);
}
