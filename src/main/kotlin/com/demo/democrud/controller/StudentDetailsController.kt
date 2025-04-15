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

@CrossOrigin(origins = arrayOf("http://localhost:8081"))
@RestController
@RequestMapping("/api")
class AttendanceController(private var attendancerespository:AttendanceRespository){

    @GetMapping("/students/attendance")
    fun getAllStudentsattendance(): MutableIterable<Attendance> = attendancerespository.findAll()

    @PostMapping("/students/attendance")
    fun createStudentAttendance(@RequestBody attendance:Attendance):Attendance =
        attendancerespository.save(attendance)
    
    @GetMapping("/students/attendance/{regno}")
    fun getstudentAttendanceByRegno(@PathVariable(value = "regno") regno:Long):ResponseEntity<Attendance>{
        return attendancerespository.findById(regno).map { attendance ->
            ResponseEntity.ok(attendance) 
        }.orElse(ResponseEntity.notFound().build())

    }

    @PostMapping("/students/attendance/{regno}")
    fun updateStudentAttendanceByRegno(@PathVariable(value="regno") regno:Long,
           @RequestBody newAttendance: Attendance): ResponseEntity<Attendance> {
        return attendancerespository.findById(regno).map { attendance ->
            var updatedAttendance = attendance.copy(
                regno = newAttendance.regno, name = newAttendance.name, address = newAttendance.address,
                email = newAttendance.email, mobno = newAttendance.mobno
            )
            ResponseEntity.ok().body(attendancerespository.save(updatedAttendance))
        }.orElse(ResponseEntity.notFound().build() )
    }

    @DeleteMapping("/students/attendance/{regno}")
    fun deleteStudentAttendanceByRegno(@PathVariable(value="regno")regno: Long):ResponseEntity<Void>{
        return attendancerespository.findById(regno).map{
            attendance->attendancerespository.delete(attendance)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}
