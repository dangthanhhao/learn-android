package com.aszqsc.myapplication

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[MagicBox::class])
interface IMagicBox{
fun getInfo():Info
}