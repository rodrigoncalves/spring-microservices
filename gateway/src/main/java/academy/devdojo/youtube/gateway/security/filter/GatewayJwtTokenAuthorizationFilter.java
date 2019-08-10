package academy.devdojo.youtube.gateway.security.filter;

import academy.devdojo.youtube.core.config.JwtConfiguration;
import academy.devdojo.youtube.security.filter.JwtTokenAuthorizationFilter;
import academy.devdojo.youtube.security.token.converter.TokenConverter;
import academy.devdojo.youtube.security.util.SecurityContextUtil;
import com.netflix.zuul.context.RequestContext;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GatewayJwtTokenAuthorizationFilter extends JwtTokenAuthorizationFilter {

    public GatewayJwtTokenAuthorizationFilter(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration, tokenConverter);
    }

    @SuppressWarnings("Duplicates")
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfiguration.getHeader().getName());

        String prefix = jwtConfiguration.getHeader().getPrefix();
        if (header == null || !header.startsWith(prefix)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(prefix, "").trim();

        String signedToken = tokenConverter.decryptToken(token);
        tokenConverter.validateTokenSignature(signedToken);

        SecurityContextUtil.setSecurityContext(SignedJWT.parse(signedToken));

        if (jwtConfiguration.getType().equalsIgnoreCase("signed")) {
            RequestContext.getCurrentContext().addZuulRequestHeader("Authorization", prefix + signedToken);
        }

        chain.doFilter(request, response);
    }
}
