package naderdeghaili.u5w3d5wp.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import naderdeghaili.u5w3d5wp.entities.Persona;
import naderdeghaili.u5w3d5wp.exceptions.UnauthorizedException;
import naderdeghaili.u5w3d5wp.services.PersoneService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final PersoneService personeService;

    public JWTCheckerFilter(JWTTools jwtTools, PersoneService personeService) {
        this.jwtTools = jwtTools;
        this.personeService = personeService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getMethod());


        // AUTENTICAZIONE
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("token non valido");

        String accessToken = authHeader.substring(7);

        jwtTools.verifyToken(accessToken);

        //AUTORIZZAZIONE
        UUID personaId = jwtTools.getIdFromToken(accessToken);
        Persona authPersona = this.personeService.findById(personaId);


        Authentication authentication = new UsernamePasswordAuthenticationToken(authPersona, null, authPersona.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
