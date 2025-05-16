package com.example.playground.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.playground.contract.Loading
import com.example.playground.contract.LoadingType

@Composable
fun ExpensesLoadingScreen(loadingType: LoadingType, modifier: Modifier = Modifier) {
    when (loadingType) {
        is LoadingType.WithTitle -> Loading(
            title = stringResource(id = loadingType.title),
            modifier = modifier
        )
        is LoadingType.WithTitleAndSubTitle -> Loading(
            title = stringResource(id = loadingType.title),
            subTitle = stringResource(id = loadingType.subTitle),
            modifier = modifier
        )
    }
}
