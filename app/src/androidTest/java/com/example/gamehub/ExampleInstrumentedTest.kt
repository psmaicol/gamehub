package com.example.gamehub

import com.example.gamehub.viewmodel.AppViewModel
import com.example.gamehub.repository.GameRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class AppViewModelTest {

    /**
     * Test 1:
     * Si el email NO tiene formato válido, el ViewModel debe devolver "Email inválido"
     */
    @Test
    fun emailInvalido_retornaError() = runTest {
        val repo = GameRepository.getInstance()
        val vm = AppViewModel(repo)

        var error: String? = null

        vm.login(
            email = "correo_malo",
            password = "1234",
            onSuccess = {},
            onError = { msg -> error = msg }
        )

        assertEquals("Email inválido", error)
    }

    /**
     * Test 2:
     * Si la contraseña tiene menos de 4 caracteres,
     * el ViewModel debe devolver el error correspondiente
     */
    @Test
    fun passwordCorta_retornaError() = runTest {
        val repo = GameRepository.getInstance()
        val vm = AppViewModel(repo)

        var error: String? = null

        vm.login(
            email = "test@gmail.com",
            password = "12",
            onSuccess = {},
            onError = { msg -> error = msg }
        )

        assertEquals("La contraseña debe tener al menos 4 caracteres", error)
    }
}

    /**
     * Test 3:
     * Si email y password son válidos, no debe devolver error
     * y debe ejecutar onSuccess
     */
