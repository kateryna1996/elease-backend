package nl.klev.eleasebackend.utilities;

import nl.klev.eleasebackend.dtos.MembershipDto;
import nl.klev.eleasebackend.dtos.MembershipInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.models.Membership;


import java.time.LocalDate;

public class MembershipTransform {

    public static Membership toMembership(MembershipInputDto membershipInputDto) {

        var membership = new Membership();
        String memType = membershipInputDto.getType();
        LocalDate startDate = membershipInputDto.getMembershipStartDate();
        double costs;
        LocalDate endDate = null;

        membership.setType(memType);
        membership.setName(memType + " membership");
        membership.setMembershipStartDate(membershipInputDto.getMembershipStartDate());

        switch (memType) {
            case "one day":
                costs = 12.99;
                endDate = startDate.plusDays(1);
                break;
            case "one month":
                costs = 59.99;
                endDate = startDate.plusMonths(1);
                break;
            case "one year":
                costs = 559.99;
                endDate = startDate.plusYears(1);
                break;

            default:
                throw new RecordNotFoundException("Please choose one of the offered options: one month, one year or one day membership !");
        }
        membership.setMembershipEndDate(endDate);
        membership.setCosts(costs);

        return membership;
    }

    public static MembershipDto toMembershipDto(Membership membership) {
        var membershipDto = new MembershipDto();

        membershipDto.setId(membership.getMembershipId());
        membershipDto.setName(membership.getName());
        membershipDto.setType(membership.getType());
        membershipDto.setMembershipStartDate(membership.getMembershipStartDate());
        membershipDto.setMembershipEndDate(membership.getMembershipEndDate());
        membershipDto.setCosts(membership.getCosts());

        return membershipDto;
    }
}
