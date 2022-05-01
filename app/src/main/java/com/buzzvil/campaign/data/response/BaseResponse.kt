package com.buzzvil.campaign.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message_code") val messageCode: String,
    @SerializedName("message") val message: String
)