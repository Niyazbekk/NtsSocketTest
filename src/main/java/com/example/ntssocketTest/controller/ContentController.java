package com.example.ntssocketTest.controller;

import com.example.ntssocketTest.model.Command;
import com.example.ntssocketTest.model.ContentEntity;
import com.example.ntssocketTest.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
public class ContentController {

    @Autowired
    ContentRepository contentRepository;

    @MessageMapping("/hello")
    @SendTo("/topic/logs")
    public String commandListen(Command command) throws Exception {
        Thread.sleep(1000); // simulated delay
        if(command.getCommand().equals("addLog")){
            contentRepository.save(new ContentEntity(
                    command.getContent()));
        }
        else{
            List<ContentEntity> contentRepositoryList = contentRepository.getContentEntitiesBy();
            StringBuffer s = new StringBuffer();
            for(ContentEntity content : contentRepositoryList){
                s.append(content.getContent()).append(" ");
            }
            System.out.println(s);
            return (HtmlUtils.htmlEscape(String.valueOf(s)));
        }
        return null;
    }
}
