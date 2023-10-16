package com.giftech.terbit.presentation.ui.pages.graph

sealed class GraphEvent {
    
    data class FilterWeeklyAsaqChartByWeek(
        val week: Int,
    ) : GraphEvent()
    
    data class FilterWeeklyAsaqChartByDayOfWeek(
        val dayOfWeek: Int,
    ) : GraphEvent()
    
    data class FilterPreTestFfqCategoryChart(
        val foodCategoryId: Int,
    ) : GraphEvent()
    
    data class FilterPostTestFfqCategoryChart(
        val foodCategoryId: Int,
    ) : GraphEvent()
    
    data object ShowWeeklyAsaqOptionsWeekDialog
        : GraphEvent()
    
    data object ShowWeeklyAsaqOptionsDayOfWeekDialog
        : GraphEvent()
    
    data object ShowPreTestFfqCategoryOptionsCategoryDialog
        : GraphEvent()
    
    data object ShowPostTestFfqCategoryOptionsCategoryDialog
        : GraphEvent()
    
    data object DismissDialog
        : GraphEvent()
    
}