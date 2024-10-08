package pro.edwx.demo_hexagonal.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticación JWT que verifica la validez del token en cada solicitud.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    /**
     * Realiza la lógica de filtrado para autenticar las solicitudes mediante tokens JWT.
     *
     * @param request     La solicitud HTTP entrante.
     * @param response    La respuesta HTTP saliente.
     * @param filterChain La cadena de filtros para invocar después de la autenticación.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            if (isAuthRequest(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = getTokenFromRequest(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String username = jwtTokenProvider.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = getUserDetails(username);
                if (jwtTokenProvider.isTokenValid(token, userDetails)) {
                    setAuthentication(request, userDetails);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handleErrorToken(response, "Malformed JWT: " + e.getMessage());
        }
    }

    /**
     * Verifica si la solicitud es una solicitud de autenticación.
     *
     * @param request La solicitud HTTP entrante.
     * @return true si la solicitud es para autenticación, false en caso contrario.
     */
    private boolean isAuthRequest(HttpServletRequest request) {
        return request.getServletPath().contains("/api/auth/login");
    }

    /**
     * Obtiene el token JWT de la solicitud HTTP.
     *
     * @param request La solicitud HTTP entrante.
     * @return El token JWT si está presente en la solicitud, null si no lo está.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {
            return header.replace("Bearer ", "");
        }

        return null;
    }

    /**
     * Obtiene los detalles del usuario a partir del nombre de usuario.
     *
     * @param username El nombre de usuario del usuario.
     * @return Los detalles del usuario.
     */
    private UserDetails getUserDetails(String username) {
        return customUserDetailsService.loadUserByUsername(username);
    }

    /**
     * Establece la autenticación en el contexto de seguridad de Spring Security.
     *
     * @param request     La solicitud HTTP entrante.
     * @param userDetails Los detalles del usuario autenticado.
     */
    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /**
     * Maneja un error relacionado con el token JWT y responde con un mensaje de error.
     *
     * @param response La respuesta HTTP saliente.
     * @param error    El mensaje de error a incluir en la respuesta.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void handleErrorToken(HttpServletResponse response, String error) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + error + "\"}");
    }
}
