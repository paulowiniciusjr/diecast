package com.pjrminis.diecast.controller;


import com.pjrminis.diecast.service.AssistantService;
import com.pjrminis.diecast.service.SimpleChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ChatController {

    //@Autowired
    //private AssistantService assistantService;

    //@Autowired
    private final SimpleChatService simpleChatService;

    //public ChatController(AssistantService assistantService) {
    //    this.assistantService = assistantService;
    //}

    //@GetMapping
    //public String ask(@RequestParam String question) {
   //     return assistantService.answer(question);
   // }

    public ChatController(SimpleChatService simpleChatService) {
        this.simpleChatService = simpleChatService;
    }

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        return new ChatResponse(this.simpleChatService.chat(request.message()));
    }





}
