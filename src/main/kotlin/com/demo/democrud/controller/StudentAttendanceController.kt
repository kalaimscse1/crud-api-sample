package com.demo.democrud.controller

import com.demo.democrud.model.StudentDetails
import com.demo.democrud.repository.StudentDetailRespository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    fun updateStudentAttendanceByRegno(@PathVariable(value = "regno") regno:Long,
           @RequestBody newAttendance: Attendance): ResponseEntity<Attendance> {
        return attendancerespository.findById(regno).map { attendance ->
            var updatedAttendance = attendance.copy(
                regno = newAttendance.regno, name = newAttendance.name, date = newAttendance.date,
                present = newAttendance.present
            )
            ResponseEntity.ok().body(attendancerespository.save(updatedAttendance))
        }.orElse(ResponseEntity.notFound().build() )
    }

    @DeleteMapping("/students/attendance/{regno}")
    fun deleteStudentAttendanceByRegno(@PathVariable(value = "regno")regno: Long):ResponseEntity<Void>{
        return attendancerespository.findById(regno).map{
            attendance -> attendancerespository.delete(attendance)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}