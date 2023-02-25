package nl.klev.eleasebackend.services;


import nl.klev.eleasebackend.dtos.MembershipDto;
import nl.klev.eleasebackend.dtos.MembershipInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.models.Membership;
import nl.klev.eleasebackend.repositories.MembershipRepository;
import nl.klev.eleasebackend.utilities.MembershipTransform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;


    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public MembershipDto createMembership(MembershipInputDto inputDto) {
        MembershipDto createdMembershipDto = new MembershipDto();
        Membership createdMembership = MembershipTransform.toMembership(inputDto);

        membershipRepository.save(createdMembership);
        createdMembershipDto = MembershipTransform.toMembershipDto(createdMembership);
        return createdMembershipDto;
    }

    public List<MembershipDto> getMemberships() {
        List<MembershipDto> membershipDtoList = new ArrayList<>();
        List<Membership> membershipList = membershipRepository.findAll();
        for (Membership m : membershipList) {
            membershipDtoList.add(MembershipTransform.toMembershipDto(m));
        }
        if (membershipList.isEmpty()) {
            throw new RecordNotFoundException("The list is empty!");
        }
        return membershipDtoList;
    }

    public MembershipDto getMembershipById(Long id) {
        MembershipDto membershipDto = new MembershipDto();
        if (membershipExists(id)) {
            Membership foundMembership = membershipRepository.findById(id).get();
            membershipDto = MembershipTransform.toMembershipDto(foundMembership);
        } else {
            throw new RecordNotFoundException("The membership with the id " + id + " cannot be found!");
        }
        return membershipDto;
    }

    public void deleteMembershipById(Long id) {
        if (membershipExists(id)) {
            membershipRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("The membership could not be deleted as it was not found");
        }
    }

    public void updateMembership(Long id, MembershipInputDto membershipInputDto) {
        if (membershipExists(id)) {
            Membership updatedMembership = membershipRepository.findById(id).get();
            Membership membershipToSet = MembershipTransform.toMembership(membershipInputDto);
            membershipToSet.setMembershipId(updatedMembership.getMembershipId());
            membershipRepository.save(membershipToSet);
        } else {
            throw new RecordNotFoundException("The membership could not be updated as it was not found");
        }
    }

    public boolean membershipExists(Long id) {
        return  membershipRepository.existsById(id);
    }
}
