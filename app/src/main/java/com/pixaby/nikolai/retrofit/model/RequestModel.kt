package com.pixaby.nikolai.retrofit.model

data class RequestModel(
val total:Int,
val totalHits:Int,
val hits:List<ImageModel>
)
