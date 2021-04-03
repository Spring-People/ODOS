package com.my.odos.invitation.repository;

import com.my.odos.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {

    Invitation findByToId(int toId);
    Invitation findByToIdAndFromId(int toId, int fromId);
}
