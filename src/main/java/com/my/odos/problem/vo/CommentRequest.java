package com.my.odos.problem.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private int problem_id;
    private String text;
}
