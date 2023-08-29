package com.giftech.terbit.di

import com.giftech.terbit.data.repository.AsaqRepository
import com.giftech.terbit.data.repository.FfqFoodCategoryRepository
import com.giftech.terbit.data.repository.FfqQuestionRepository
import com.giftech.terbit.data.repository.ProgramRepository
import com.giftech.terbit.data.repository.UserRepository
import com.giftech.terbit.domain.repository.IAsaqRepository
import com.giftech.terbit.domain.repository.IFfqFoodCategoryRepository
import com.giftech.terbit.domain.repository.IFfqQuestionRepository
import com.giftech.terbit.domain.repository.IProgramRepository
import com.giftech.terbit.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    abstract fun provideFfqFoodCategoryRepository(
        ffqFoodCategoryRepository: FfqFoodCategoryRepository,
    ): IFfqFoodCategoryRepository
    
    @Binds
    abstract fun provideFfqQuestionRepository(
        ffqQuestionRepository: FfqQuestionRepository,
    ): IFfqQuestionRepository
    
    @Binds
    abstract fun provideProgramRepository(
        programRepository: ProgramRepository,
    ): IProgramRepository

    @Binds
    abstract fun provideUserRepository(
        userRepository: UserRepository,
    ): IUserRepository

    @Binds
    abstract fun provideAsaqRepository(
        asaqRepository: AsaqRepository,
    ): IAsaqRepository
    
}