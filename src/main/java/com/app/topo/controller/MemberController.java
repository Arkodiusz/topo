package com.app.topo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.app.topo.TopoApplication.root;

@RestController
@RequestMapping(root)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {
}
