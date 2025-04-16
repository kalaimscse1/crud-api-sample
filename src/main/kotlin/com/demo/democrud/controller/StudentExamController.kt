package com.demo.democrud.controller

import com.demo.democrud.model.StudentDetails
import com.demo.democrud.repository.StudentDetailRespository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("http://localhost:8081"))
@RestController
@RequestMapping("/api")
class ExamController(private var studentexamrespository:StudentExamRespository){

    @GetMapping("/students/result")
    fun getAllStudentsResult(): MutableIterable<ExamResult> = studentexamrespository.findAll()

    
}