package br.com.itau.granteeapi.controller

import br.com.itau.granteeapi.dto.GranteeDTO
import br.com.itau.granteeapi.service.GranteeService
import javax.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grantee")
class GranteeController(private val service: GranteeService) {

    @PostMapping
    fun createGrantee(@RequestBody granteeDTO: GranteeDTO) : ResponseEntity<Void> {
        service.createGrantee(granteeDTO)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping(value = ["/{userId}/grantees"])
    fun getGranteesByUser(@PathVariable userId: Long) : ResponseEntity<List<GranteeDTO>> {
        return ResponseEntity.ok(service.findGranteesByUser(userId))
    }
}