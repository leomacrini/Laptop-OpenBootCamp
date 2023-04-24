package com.example.ejercicio1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${propiedad.que.cambia}")
    public String mensaje ;
@GetMapping("/hello")
public String Hello(){
    return  ("Hola mundo desde el perfil " + mensaje) ;
}
}
