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

    @PostMapping("/students/result")
    fun createStudentResult(@RequestBody examresult:ExamResult):ExamResult =
        studentexamrespository.save(examresult) 

    @GetMapping("/students/result/{regno}")
    fun getstudentResultByRegno(@PathVariable(value = "regno") regno:Long):ResponseEntity<ExamResult>{
        return studentexamrespository.findById(regno).map { examresult ->
            ResponseEntity.ok(examresult) 
        }.orElse(ResponseEntity.notFound().build())
    }

    @PostMapping("/students/result/{regno}")
    fun updateStudentResultByRegno(@PathVariable(value = "regno") regno:Long,
           @RequestBody newExamResult: ExamResult): ResponseEntity<ExamResult> {
           return studentexamrespository.findById(regno).map { examresult ->
            var updatedNewExamResult = examresult.copy(
                regno = newExamResult.regno, name = newExamResult.name, tamil = newExamResult.tamil,
                english = newExamResult.english, maths = newExamResult.maths, science = newExamResult.science,
                social = newExamResult.social, total = newExamResult.total, percentage = newExamResult.percentage
            )
            ResponseEntity.ok().body(studentexamrespository.save(updatedNewExamResult))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/students/result/{regno}")
    fun deleteStudentResultByRegno(@PathVariable(value = "regno")regno: Long):ResponseEntity<Void>{
        return studentexamrespository.findById(regno).map{
            examresult -> studentexamrespository.delete(examresult)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}