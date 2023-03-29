package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.MembershipDto;
import nl.klev.eleasebackend.dtos.MembershipInputDto;
import nl.klev.eleasebackend.models.Membership;
import nl.klev.eleasebackend.repositories.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembershipServiceTest {

    @Mock
    MembershipRepository membershipRepository;

    @InjectMocks
    MembershipService membershipService;

    Membership membership1;
    Membership membership2;
    MembershipInputDto membershipDto1;

    @BeforeEach
    void setUp() {
        membership1 = new Membership();

        membership1.setMembershipId(1L);
        membership1.setName("one year membership");
        membership1.setType("one year");
        membership1.setMembershipStartDate(LocalDate.of(2023, 8, 25));
        membership1.setMembershipEndDate(LocalDate.of(2024, 8, 25));
        membership1.setCosts(587.99);
        membership1.setParkingIncluded(false);


        membership2 = new Membership();
        membership2.setMembershipId(2L);

        membershipDto1 = new MembershipInputDto();

        membershipDto1.setType("one month");
        membershipDto1.setMembershipStartDate(LocalDate.of(2023, 6, 9));
    }

    @Test
    void gettingAllMemberships() {
        when(membershipRepository.findAll()).thenReturn(List.of(membership1, membership2));
        List<MembershipDto> memberships = membershipService.getMemberships();

        assertEquals(membership1.getMembershipId(), memberships.get(0).getId());
        assertEquals(membership2.getMembershipId(), memberships.get(1).getId());
    }


    @Test
    void membershipExistsCheck() {
        when(membershipRepository.existsById(anyLong())).thenReturn(true);
        Boolean ifExists = membershipService.membershipExists(1L);

        assertEquals(true, ifExists);
    }

    @Test
    void shouldReturnMembershipById() {
        when(membershipRepository.existsById(1L)).thenReturn(true);
        when(membershipRepository.findById(anyLong())).thenReturn(Optional.of(membership1));

        MembershipDto membershipDto = membershipService.getMembershipById(1L);

        assertEquals(membership1.getMembershipId(), membershipDto.getId());
    }

    @Test
    void shouldDeleteMembership() {
        when(membershipRepository.existsById(1L)).thenReturn(true);
        membershipService.deleteMembershipById(1L);
        verify(membershipRepository).deleteById(1L);
    }

    @Test
    void shouldUpdateMembership() {
        when(membershipRepository.existsById(1L)).thenReturn(true);
        when(membershipRepository.findById(1L)).thenReturn(Optional.of(membership1));

        membership1.setType(membershipDto1.getType());
        membership1.setMembershipStartDate(membershipDto1.getMembershipStartDate());

        membershipRepository.save(membership1);
        membershipService.updateMembership(1L, membershipDto1);

        assertEquals(1, membership1.getMembershipId());
        assertEquals("one month", membership1.getType());
        assertEquals("2023-06-09", membership1.getMembershipStartDate().toString());
    }
}