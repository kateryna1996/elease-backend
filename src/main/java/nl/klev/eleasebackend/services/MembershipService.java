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
//adding active accounts search for admin
//    public List<MembershipDto> getActive

    public MembershipDto getMembershipById(Long id) {
        MembershipDto membershipDto = new MembershipDto();
        Optional<Membership> foundMembership = membershipRepository.findById(id);
        if (foundMembership.isPresent()) {
            membershipDto = MembershipTransform.toMembershipDto(foundMembership.get());
        } else {
            throw new RecordNotFoundException("The membership with the id " + id + " cannot be found!");
        }
        return membershipDto;
    }

    public void deleteMembershipById(Long id) {
        membershipRepository.deleteById(id);
    }

    public MembershipDto updateMembership(Long id, MembershipInputDto membershipInputDto) {
        Optional<Membership> membership = membershipRepository.findById(id);

        if (membership.isEmpty()) {
            throw new RecordNotFoundException("The membership could not be updates as it was not found");
        } else {
            Membership updatedMembership = membership.get();
            Membership membershipToSet = MembershipTransform.toMembership(membershipInputDto);
            membershipToSet.setMembershipId(updatedMembership.getMembershipId());
            Membership membershipToSave = membershipRepository.save(membershipToSet);
            return MembershipTransform.toMembershipDto(membershipToSave);
        }
    }
}
