package com.picpay.desafio.android.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiError(
    val statusCode: Int,
    val message: String
): Parcelable