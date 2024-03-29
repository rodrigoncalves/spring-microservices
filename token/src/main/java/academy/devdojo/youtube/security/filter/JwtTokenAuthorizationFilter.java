package academy.devdojo.youtube.security.filter;

import academy.devdojo.youtube.core.config.JwtConfiguration;
import academy.devdojo.youtube.security.token.converter.TokenConverter;
import academy.devdojo.youtube.security.util.SecurityContextUtil;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    protected final JwtConfiguration jwtConfiguration;
    protected final TokenConverter tokenConverter;

    @SuppressWarnings("Duplicates")
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfiguration.getHeader().getName());

        String prefix = jwtConfiguration.getHeader().getPrefix();
        if (header == null || !header.startsWith(prefix)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(prefix, "").trim();
        boolean isSigned = equalsIgnoreCase("signed", jwtConfiguration.getType());
        SignedJWT signedJWT = isSigned ? validate(token) : decryptAndValidate(token);
        SecurityContextUtil.setSecurityContext(signedJWT);

        chain.doFilter(request, response);
    }

    private SignedJWT decryptAndValidate(String encryptedToken) {
        String signedToken = tokenConverter.decryptToken(encryptedToken);
        return validate(signedToken);
    }

    @SneakyThrows
    private SignedJWT validate(String signedToken) {
        tokenConverter.validateTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }
}
