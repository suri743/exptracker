package com.suri.exptracker.controller;

import com.suri.exptracker.dto.GroupDto;
import com.suri.exptracker.dto.requestdtos.GroupRequestDto;
import com.suri.exptracker.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/exptracker")
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/group")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupRequestDto groupRequestDto) {
        GroupDto group = groupService.createGroup(groupRequestDto);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable("id") int id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }
}
