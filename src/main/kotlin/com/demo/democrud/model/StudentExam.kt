package com.demo.democrud.model

import net.bytebuddy.dynamic.loading.InjectionClassLoader
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ExamResult(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var regno:Long=0,
    var name:String="",
    var tamil:Long=0,
    var english:Long=0,
    var maths:Long=0,
    var science:Long=0,
    var social:Long=0,
    var total:Long=0,
    var percentage:String="",
){}