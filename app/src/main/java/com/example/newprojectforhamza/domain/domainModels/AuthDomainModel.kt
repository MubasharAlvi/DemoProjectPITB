package com.example.newprojectforhamza.domain.domainModels

data class AuthDomainModel(
    val success : Boolean?=false,
    val message : String?="",
    val token : String?=""
)
