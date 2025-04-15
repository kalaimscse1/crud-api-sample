package com.demo.democrud.model

import net.bytebuddy.dynamic.loading.InjectionClassLoader
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class StudentDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var regno:Long=0,
    var name:String="",
    var address:String="",
    var email:String="",
    var mobno:Long=0,
){}

@Entity
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var regno:Long=0,
    var name:String="",
    var date:String="",
    var present:String="",
){}
