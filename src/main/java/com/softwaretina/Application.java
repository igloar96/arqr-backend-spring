package com.softwaretina;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.entities.Grupo;
import com.softwaretina.models.entities.Proceso;
import com.softwaretina.repository.AccountRepository;
import com.softwaretina.repository.GrupoRepository;
import com.softwaretina.repository.ProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.annotation.PostConstruct;

@EnableWebSecurity
@SpringBootApplication
public class Application  extends SpringBootServletInitializer {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ProcesoRepository procesoRepository;

    @Autowired
    private Environment env;

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void seeds() {
        System.out.println("   _____                              __  .__               \n" +
                "  /  _  \\_______  ____   ____   _____/  |_|__| ____ _____   \n" +
                " /  /_\\  \\_  __ \\/ ___\\_/ __ \\ /    \\   __\\  |/    \\\\__  \\  \n" +
                "/    |    \\  | \\/ /_/  >  ___/|   |  \\  | |  |   |  \\/ __ \\_\n" +
                "\\____|__  /__|  \\___  / \\___  >___|  /__| |__|___|  (____  /\n" +
                "        \\/     /_____/      \\/     \\/             \\/     \\/ ");

        Grupo grupo = new Grupo();
        grupo.setNombre("ROOT");

        this.grupoRepository.save(grupo);

        Account accountAdmin = new Account();
        accountAdmin.setUsername("admin");
        accountAdmin.setPassword("admin");
        accountAdmin.setGrupo(grupo);
        accountAdmin.AddRole(Account.ROLES.ROLE_ADMIN);

        this.accountRepository.save(accountAdmin);

        Proceso proceso = new Proceso();
        proceso.setDescripcion("Proceso de instalacion ARQR finalizado.");
        proceso.setEstado(Proceso.ESTADO.FINALIZADO);
        proceso.setNombre("ARQR INSTALLATION");
        proceso.setGrupo(grupo);
        proceso.setAccount(accountAdmin);

        this.procesoRepository.save(proceso);

    }
}

