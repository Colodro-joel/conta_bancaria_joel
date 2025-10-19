package com.senai.conta_bancaria_joel.interface_ui.Exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;

import java.net.URI;

public class ProblemDetailUtils {

    public static ProblemDetail buildProblem(HttpStatus status, String title, String detail, String path) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setDetail(detail);
        problem.setTitle(title);
        problem.setInstance(URI.create(path));
        return problem;
    }
}
