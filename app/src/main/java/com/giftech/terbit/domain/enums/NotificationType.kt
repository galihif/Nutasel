package com.giftech.terbit.domain.enums

// Can be used to identify the frequency of a reminder
enum class NotificationType(val typeId: String, val channelName: String) {
    PROGRAM_REMINDER("program_reminder", "Program Reminder"),
    DAILY_TIPS("daily_tips", "Daily Tips");
    
    companion object {
        
        fun fromTypeId(typeId: String): NotificationType {
            return values().firstOrNull { it.typeId == typeId }
                ?: PROGRAM_REMINDER
        }
        
    }
    
}