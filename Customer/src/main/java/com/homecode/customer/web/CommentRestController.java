package com.homecode.customer.web;

import com.homecode.library.model.CommentEntity;
import com.homecode.library.model.dto.CommentCreatedDTO;
import com.homecode.library.model.dto.CommentMessageDto;
import com.homecode.library.model.view.CommentDisplayView;
import com.homecode.library.service.impl.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class CommentRestController {

    private final CommentServiceImpl commentService;

    public CommentRestController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/api/{modelId}/comments")
    public ResponseEntity<List<CommentDisplayView>> getCommentsForModel(@PathVariable(value = "modelId") Long modelId) {

        return ResponseEntity.ok(commentService.getAllCommentsForModel(modelId));
    }

    @PostMapping(value = "/api/{modelId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommentDisplayView> createComment(@PathVariable("modelId") Long modelId,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody CommentMessageDto commentDto) {
        CommentCreatedDTO commentCreationDto = new CommentCreatedDTO().setUsername(userDetails.getUsername()).setModelId(modelId).setMessage(commentDto.getMessage());

        CommentDisplayView comment = commentService.createComment(commentCreationDto);

        return ResponseEntity
                .created(URI.create(String.format("/api/%d/comments/%d", modelId, comment.getId())))
                .body(comment);
    }

}
