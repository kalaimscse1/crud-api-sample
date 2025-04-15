package com.demo.democrud.repository

import com.demo.democrud.model.StudentDetails
import org.springframework.data.repository.CrudRepository

interface StudentDetailRespository:CrudRepository<StudentDetails,Long> {
}

interface AttendanceRespository:CrudRepository<Attendance,Long> {
}