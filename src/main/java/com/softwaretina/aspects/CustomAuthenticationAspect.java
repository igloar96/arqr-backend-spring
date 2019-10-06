package com.softwaretina.aspects;

import com.softwaretina.models.entities.Account;
import com.softwaretina.models.exception.NoAutorizadoException;
import com.softwaretina.services.auth.LogginService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class CustomAuthenticationAspect {

    @Autowired
    private LogginService logginService;

    @Around(" @annotation(com.softwaretina.aspects.WithGroupRole)")
    public void validateAspect(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        Account account  = this.logginService.getLoggedAccount();

        //Formo el rol a comparar Ej: ROLE_ADMIN_{id} -> ROLE_ADMIN_58
        String requestRole = method.getAnnotation(WithGroupRole.class)
                .role().replace("{id}",account.getGrupo().getId().toString());

        if (account.getRoles().contains(requestRole))
            pjp.proceed();
        else
            throw new NoAutorizadoException("No tiene el permiso requerido");
    }
}
