package com.example.newprojectforhamza.data.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.newprojectforhamza.data.local.crypto.UserDatabasePassphrase
import com.example.newprojectforhamza.data.local.dao.PopularMovieDao
import com.example.newprojectforhamza.data.local.dao.TopRatedMovieDao
import com.example.newprojectforhamza.data.local.movieDB.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE topRatedMovies ADD COLUMN originalTitle TEXT")
        }
    }

    @Provides
    @Singleton
    fun provideUserDatabasePassphrase(@ApplicationContext context: Context) =
        UserDatabasePassphrase(context)

    @Provides
    @Singleton
    fun provideSupportFactory(userDatabasePassphrase: UserDatabasePassphrase) = SupportFactory(userDatabasePassphrase.getPassphrase())

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context, supportFactory: SupportFactory): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "movies.db")
            .addMigrations(MIGRATION_1_2)
           // .openHelperFactory(supportFactory)
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    fun providePopularDao(db: AppDatabase): PopularMovieDao = db.popularMovieDao()

    @Provides
    fun provideTopRatedDao(db: AppDatabase): TopRatedMovieDao = db.topRatedMovieDao()
}