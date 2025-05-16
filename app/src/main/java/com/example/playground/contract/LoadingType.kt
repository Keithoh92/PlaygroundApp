package com.example.playground.contract

import androidx.annotation.StringRes
import com.example.playground.R

sealed interface LoadingType {

    data class WithTitle(
        @StringRes val title: Int = R.string.please_wait
    ) : LoadingType

    data class WithTitleAndSubTitle(
        @StringRes val title: Int = R.string.please_wait,
        @StringRes val subTitle: Int
    ) : LoadingType
}
