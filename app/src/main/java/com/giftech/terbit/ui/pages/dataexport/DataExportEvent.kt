package com.giftech.terbit.ui.pages.dataexport

sealed class DataExportEvent {
    
    data class NextPage(
        val currentPage: Int,
    ) : DataExportEvent()
    
    data class PreviousPage(
        val currentPage: Int,
    ) : DataExportEvent()
    
    data object StartExtractingToBitmap
        : DataExportEvent()
    
    data object FinishExtractingToBitmap
        : DataExportEvent()
    
    data object StartSavingToStorage
        : DataExportEvent()
    
    data object FinishSavingToStorage
        : DataExportEvent()
    
}