package com.softwaretina;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.entities.Grupo;
import com.softwaretina.models.entities.Proceso;
import com.softwaretina.models.entities.Tag;
import com.softwaretina.repository.AccountRepository;
import com.softwaretina.repository.GrupoRepository;
import com.softwaretina.repository.ProcesoRepository;
import com.softwaretina.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.annotation.PostConstruct;
import java.util.Arrays;

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
    private TagRepository tagRepository;

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

        /*
         * Grupo
         * */
        Grupo grupo = new Grupo();
        grupo.setNombre("ROOT");

        this.grupoRepository.save(grupo);

        /*
        * Account
        * */
        Account accountAdmin = new Account();
        accountAdmin.setUsername("admin");
        accountAdmin.setPassword("admin");
        accountAdmin.setGrupo(grupo);
        accountAdmin.AddRole(Account.ROLES.ROLE_ADMIN);

        this.accountRepository.save(accountAdmin);

        /*
         * Tags
         * */
        Tag tag1 = new Tag();
        tag1.setGrupo(grupo);
        tag1.setName("Software_Development");
        tag1.setCategoria(Tag.CATEGORIA.SERVICIO);
        Tag tag2 = new Tag();
        tag2.setName("Oracle");
        tag2.setGrupo(grupo);
        tag2.setCategoria(Tag.CATEGORIA.CLIENTE);


        this.tagRepository.save(tag1);
        this.tagRepository.save(tag2);

        /*
         * Proceso
         * */
        Proceso proceso = new Proceso();
        proceso.setDescripcion("Proceso de instalacion ARQR finalizado.");
        proceso.setEstado(Proceso.ESTADO.FINALIZADO);
        proceso.setNombre("ARQR INSTALLATION");
        proceso.setGrupo(grupo);
        proceso.setModifingAccount(accountAdmin);
        proceso.setTags(Arrays.asList(tag1,tag2));

        this.procesoRepository.save(proceso);

    }
}

