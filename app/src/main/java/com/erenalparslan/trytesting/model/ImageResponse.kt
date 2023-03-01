package com.erenalparslan.trytesting.model

data class ImageResponse(

    var total :Int,
    var totalHits :Int,
    var hits : List<ImageResult>
)
