package com.demo.democrud.repository

import com.demo.democrud.model.StudentDetails
import org.springframework.data.repository.CrudRepository

interface StudentExamRespository:CrudRepository<ExamResult,Long> {
}