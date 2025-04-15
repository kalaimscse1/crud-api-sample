 package com.demo.democrud.controller

import com.demo.democrud.model.StudentDetails
import com.demo.democrud.repository.StudentDetailRespository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("http://localhost:8081"))
@RestController
@RequestMapping("/api")
class StudentDetailsController(private var studentdetailsrespository:StudentDetailRespository){

    @GetMapping("/students")
    fun getAllStudentsdetails(): MutableIterable<StudentDetails> = studentdetailsrespository.findAll()

    @PostMapping("/students")
    fun createStudentdetail(@RequestBody studentDetails: StudentDetails):StudentDetails=
        studentdetailsrespository.save(studentDetails)

    @GetMapping("/students/{regno}")
    fun getstudentByRegno(@PathVariable(value = "regno") regno:Long):ResponseEntity<StudentDetails>{
        return studentdetailsrespository.findById(regno).map { studentDetails ->
            ResponseEntity.ok(studentDetails) 
        }.orElse(ResponseEntity.notFound().build())

    }

    @PutMapping("/students/{regno}")
    fun updateStudentByRegno(@PathVariable(value="regno") regno:Long,
           @RequestBody newStudentDetails: StudentDetails): ResponseEntity<StudentDetails> {
        return studentdetailsrespository.findById(regno).map { studentDetails ->
            var updatedStudentDetails = studentDetails.copy(
                regno = newStudentDetails.regno, name = newStudentDetails.name, address = newStudentDetails.address,
                email = newStudentDetails.email, mobno = newStudentDetails.mobno
            )
            ResponseEntity.ok().body(studentdetailsrespository.save(updatedStudentDetails))
        }.orElse(ResponseEntity.notFound().build() )
    }

    @DeleteMapping("/students/{regno}")
    fun deleteStudentByRegno(@PathVariable(value="regno")regno: Long):ResponseEntity<Void>{
        return studentdetailsrespository.findById(regno).map{
            studentDetails->studentdetailsrespository.delete(studentDetails)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}