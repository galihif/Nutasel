package com.giftech.terbit.presentation.ui.pages.ffq.result

sealed class FfqResultEvent {

    data class Init(
        val programId: Int,
    ) : FfqResultEvent()
    
}