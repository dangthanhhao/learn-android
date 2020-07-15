package com.aszqsc.myapplication

import dagger.Module
import dagger.Provides

@Module
class MagicBox(){
    @Provides
    fun provideInfo(): Info {
        return Info()
    }
}
