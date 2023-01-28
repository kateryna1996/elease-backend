package nl.klev.eleasebackend.controllers;

import nl.klev.eleasebackend.dtos.IdInputDto;
import nl.klev.eleasebackend.dtos.MembershipDto;
import nl.klev.eleasebackend.dtos.MembershipInputDto;
import nl.klev.eleasebackend.services.MembershipService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createMembership(@Valid @RequestBody MembershipInputDto inputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            MembershipDto membershipDto = membershipService.createMembership(inputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/membership/" + membershipDto.getId()).toUriString());

            return ResponseEntity.created(uri).body(membershipDto);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<MembershipDto>> getMembershipList() {
        List<MembershipDto> membershipDtoList = membershipService.getMemberships();

        return ResponseEntity.ok().body(membershipDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipDto> getMembershipById(@PathVariable("id") Long id) {
        MembershipDto membershipDto = membershipService.getMembershipById(id);
        return ResponseEntity.ok().body(membershipDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMembershipById(@PathVariable("id") Long id, @Valid @RequestBody MembershipInputDto membershipInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            MembershipDto membershipDto = membershipService.updateMembership(id, membershipInputDto);
            return ResponseEntity.noContent().build();
        }
    }

//    cannot be done with validation
//    @PatchMapping("/{id}")


    @DeleteMapping("/{id}")
    public ResponseEntity deleteMembershipById(@PathVariable("id") Long id) {
        membershipService.deleteMembershipById(id);
        return ResponseEntity.noContent().build();
    }

}
