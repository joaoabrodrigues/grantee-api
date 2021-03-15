package br.com.itau.granteeapi.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
internal class GranteeControllerIntegrationTest {

    @Autowired
    lateinit var request: MockMvc

    @Test
    fun `should send request and create new grantee`() {
        sendRequestToCreateGrantee()
            .andExpect(status().isCreated)
    }

    @Test
    fun `should find created grantee`() {
        sendRequestToCreateGrantee()

        request.perform(
            get("/grantee/1/grantees")
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(
                """
                    [
                        {
                            "user_id": 1,
                            "cpf_cnpj": "12345678901",
                            "name": "Joao Rodrigues",
                            "nickname": null,
                            "email": null,
                            "phone_number": null,
                            "grantee_account": {
                                "grantee_account_id": 1,
                                "pix_key": "123",
                                "bank": null,
                                "branch": null,
                                "account": null,
                                "digit": null,
                                "account_type": null
                            }
                        }
                    ]
                """.trimIndent()
            ))
    }

    private fun sendRequestToCreateGrantee() = request.perform(
        post("/grantee")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                """
                        {
                            "user_id": "1",
                            "cpf_cnpj": "12345678901",
                            "name": "Joao Rodrigues",
                            "grantee_account": {
                                "pix_key": "123"
                            }
                        }
                    """.trimIndent()
            )
    )
}