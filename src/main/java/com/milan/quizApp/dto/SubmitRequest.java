package com.milan.quizApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class SubmitRequest {
    private Integer id;
    private String responses;
}

