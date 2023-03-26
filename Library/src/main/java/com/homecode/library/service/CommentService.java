package com.homecode.library.service;

import com.homecode.library.model.dto.CommentCreatedDTO;
import com.homecode.library.model.view.CommentDisplayView;

import java.util.List;

public interface CommentService {


    List<CommentDisplayView> getAllCommentsForModel(Long modelId);


    public CommentDisplayView createComment(CommentCreatedDTO commentCreatedDTO);
}
