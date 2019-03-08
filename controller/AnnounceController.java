package com.k2data.kbc.announce.controller;

import com.k2data.kbc.announce.model.Announce;
import com.k2data.kbc.announce.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnnounceController {

    @Autowired
    AnnounceService announceService;

    @RequestMapping(value = "announces", method = RequestMethod.GET)
    public List<Announce> list() {
        return announceService.list();

    }

    @RequestMapping(value = "announces", method = RequestMethod.POST)
    public String insert() {
        announceService.insert(new Announce());
        return "success";
    }
}
