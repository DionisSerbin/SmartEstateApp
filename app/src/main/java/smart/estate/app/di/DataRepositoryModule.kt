package smart.estate.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import smart.estate.app.data.local.test.TestService
import smart.estate.app.data.repository.DataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataRepositoryModule {

    @Provides
    @Singleton
    fun provideDataRepository(testService: TestService): DataRepository {
        return DataRepository(testService)
    }
}