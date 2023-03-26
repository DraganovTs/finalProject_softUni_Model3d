package com.homecode.library.service.impl;

import com.homecode.library.model.CommentEntity;
import com.homecode.library.model.ModelEntity;
import com.homecode.library.model.UserEntity;
import com.homecode.library.model.dto.CommentCreatedDTO;
import com.homecode.library.model.view.CommentDisplayView;
import com.homecode.library.repository.CommentRepository;
import com.homecode.library.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelServiceImpl modelService;

    private final CustomerUserServiceImpl customerUserService;


    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelServiceImpl modelService, CustomerUserServiceImpl customerUserService) {
        this.commentRepository = commentRepository;
        this.modelService = modelService;
        this.customerUserService = customerUserService;
    }

    @Override
    public List<CommentDisplayView> getAllCommentsForModel(Long modelId) {
        ModelEntity model = this.modelService.findById(modelId);

        List<CommentEntity> comments = this.commentRepository.findAllByModel(model);

        return comments.stream().map(c -> new CommentDisplayView().setId(c.getId()).setFirstName(c.getAuthor().getFirstName()).setMessage(c.getText())).collect(Collectors.toList());
    }

    @Override
    public CommentDisplayView createComment(CommentCreatedDTO commentCreatedDTO) {
        UserEntity user = this.customerUserService.findUserByUsername(commentCreatedDTO.getUsername());

        CommentEntity comment = new CommentEntity()
                .setCreated(LocalDateTime.now())
                .setModel(this.modelService.findById(commentCreatedDTO.getModelId()))
                .setAuthor(user)
                .setApproved(true)
                .setText(commentCreatedDTO.getMessage());

        this.commentRepository.save(comment);

        return new CommentDisplayView()
                .setId(comment.getId())
                .setFirstName(user.getFirstName())
                .setMessage(comment.getText());
    }
}
