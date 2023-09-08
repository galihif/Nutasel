package com.giftech.terbit.domain.enums

enum class ProgramTag(val tagId: String) {
    PRE_TEST("pre_test"),
    WEEKLY_PROGRAM("weekly_program"),
    POST_TEST("post_test");
    
    companion object {
        
        fun fromTagId(tagId: String): ProgramTag {
            return values().firstOrNull { it.tagId == tagId }
                ?: WEEKLY_PROGRAM
        }
        
    }
    
}