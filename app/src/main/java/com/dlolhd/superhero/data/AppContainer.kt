package com.dlolhd.superhero.data

import com.dlolhd.superhero.model.HeroRepository
import com.dlolhd.superhero.model.HeroesRepositoryLocal

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val heroRepository: HeroRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer: AppContainer {
    override val heroRepository: HeroRepository by lazy {
        HeroesRepositoryLocal()
    }

}